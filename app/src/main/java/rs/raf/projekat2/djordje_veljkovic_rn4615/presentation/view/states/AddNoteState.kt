package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

sealed class AddNoteState {

    object Success: AddNoteState()

    data class Error(val message: String): AddNoteState()

}