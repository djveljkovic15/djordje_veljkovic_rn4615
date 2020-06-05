package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.AddUserState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.UserState


interface UserContract {

    interface ViewModel {

        val addDone: LiveData<AddUserState>
        val logged: LiveData<UserState>
        val userState: LiveData<UserState>
        fun verifyUser(username:String, pin:String)
        fun addUser(user: User)

    }

}