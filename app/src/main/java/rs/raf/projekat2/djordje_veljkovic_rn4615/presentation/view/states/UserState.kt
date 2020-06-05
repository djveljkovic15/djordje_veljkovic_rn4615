package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User

sealed class UserState {

    object Loading: UserState()

    object DataFetched: UserState()

    data class Success(val user: User) : UserState()

    data class Error(val message: String) : UserState()

}