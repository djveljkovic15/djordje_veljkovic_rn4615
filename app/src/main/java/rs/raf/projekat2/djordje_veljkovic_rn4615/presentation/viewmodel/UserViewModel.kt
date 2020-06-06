package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user.UserRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.UserContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.AddUserState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.UserState
import timber.log.Timber

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel(), UserContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val userState: MutableLiveData<UserState> = MutableLiveData()

    override val addDone: MutableLiveData<AddUserState> = MutableLiveData()

    override val logged: MutableLiveData<UserState> = MutableLiveData()

    override fun verifyUser(username: String, pin: String){
        val subscription = userRepository
            .verifyUser(username, pin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("porukica iz UVM")
                    logged.value = UserState.Logged
                },
                {

                    Timber.e("porukica iz UVM kad je error")
                    logged.value = UserState.Error("Error happened while checking user")
                }
            )
        subscriptions.add(subscription)

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