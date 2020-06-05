package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

sealed class SaveNoteState {

    object Success: SaveNoteState()

    data class Error(val message: String): SaveNoteState()

}