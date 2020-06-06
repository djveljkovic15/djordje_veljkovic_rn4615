package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

sealed class DeleteNoteState {

    object Success: DeleteNoteState()

    data class Error(val message: String): DeleteNoteState()

}