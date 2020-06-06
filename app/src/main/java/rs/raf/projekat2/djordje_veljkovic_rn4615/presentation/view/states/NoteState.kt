package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note

sealed class NoteState {

    data class Success(val note: Note): NoteState()

    data class AllSuccess(val notes: List<Note>): NoteState()

    data class Error(val message: String): NoteState()

}