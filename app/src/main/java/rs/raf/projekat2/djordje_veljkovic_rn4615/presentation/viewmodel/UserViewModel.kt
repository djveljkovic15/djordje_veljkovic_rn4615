package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user.UserRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.UserContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.AddUserState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.UserState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel(), UserContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val userState: MutableLiveData<UserState> = MutableLiveData()

    override val addDone: MutableLiveData<AddUserState> = MutableLiveData()


    private val publishSubject: PublishSubject<User> = PublishSubject.create()

    init{
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                Timber.e("RVM switch mapa user $it")
                userRepository
                    .verifyUser(it.username, it.pin)
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
                    userState.value = UserState.Success(it)
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }



    override fun verifyUser(username: String, pin: String){ // lose napisana logika
        Timber.e("RVM verify user")
        //return User(username,pin)
        publishSubject.onNext(
            User(
                username,
                pin
            )
        )
    }

    override fun addUser(user: User) {
        val subscription = userRepository
            .addUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddUserState.Success
                },
                {
                    addDone.value = AddUserState.Error("Error happened while adding user")
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}