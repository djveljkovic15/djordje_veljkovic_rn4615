package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Resource
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.RasporedRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.MainContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.RasporedState
import timber.log.Timber

class RasporedViewModel(
    private val rasporedRepository: RasporedRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val rasporedState: MutableLiveData<RasporedState> = MutableLiveData()

    override fun getRaspored() {
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

}