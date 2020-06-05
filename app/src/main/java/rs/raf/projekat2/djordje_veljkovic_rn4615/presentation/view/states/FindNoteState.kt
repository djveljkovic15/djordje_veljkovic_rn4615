package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note

sealed class FindNoteState {

    data class FindOneSuccess(val note: Note): FindNoteState()

    data class FindAllSuccess(val notes: List<Note>): FindNoteState()

    data class Error(val message: String): FindNoteState()

}