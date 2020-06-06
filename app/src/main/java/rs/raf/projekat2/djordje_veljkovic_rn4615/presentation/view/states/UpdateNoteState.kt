package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

sealed class UpdateNoteState {

    object Success: UpdateNoteState()

    data class Error(val message: String): UpdateNoteState()

}