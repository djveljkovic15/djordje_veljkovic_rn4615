package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

sealed class AddUserState {

    object Success: AddUserState()

    data class Error(val message: String) : AddUserState()

}