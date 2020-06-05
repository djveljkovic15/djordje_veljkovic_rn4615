package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Resource
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.RasporedFilter
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.raspored.RasporedRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.RasporedContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.RasporedState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RasporedViewModel(
    private val rasporedRepository: RasporedRepository
) : ViewModel(), RasporedContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val rasporedState: MutableLiveData<RasporedState> = MutableLiveData()

    private val publishSubject: PublishSubject<RasporedFilter> = PublishSubject.create()

    init{
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                Timber.e("RVM switch mapa raspored $it")
                rasporedRepository
                    .filterRaspored(it.filter, it.grupa, it.dan)
//                    .filterRaspored("Dragan", "", "")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    Timber.e("RVM success raspored $it")
                    rasporedState.value = RasporedState.Success(it)
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun fetchRaspored() {
        val errorMessage = "Error while fetching raspored from server!"

        val subscription = rasporedRepository
            .fetchRaspored()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> rasporedState.value = RasporedState.Loading
                        is Resource.Success -> rasporedState.value = RasporedState.DataFetched
                        is Resource.Error -> rasporedState.value = RasporedState.Error(errorMessage)
                    }
                },
                {
                    rasporedState.value = RasporedState.Error(errorMessage)
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun getRaspored() {
        val subscription = rasporedRepository
            .getRaspored()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    rasporedState.value = RasporedState.Success(it)
                }, {
                    rasporedState.value = RasporedState.Error("Error while getting data from database!")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun filterRaspored(filter: String, grupa: String, dan: String){
        Timber.e("RVM filter raspored")
        publishSubject.onNext(
            RasporedFilter(
                filter,
                grupa,
                dan
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}