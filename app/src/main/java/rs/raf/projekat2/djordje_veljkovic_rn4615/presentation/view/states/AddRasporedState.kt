package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

sealed class AddRasporedState {

    object Success: AddRasporedState()

    data class Error(val message: String) : AddRasporedState()

}