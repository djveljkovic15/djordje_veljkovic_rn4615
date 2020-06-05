package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.SaveNoteState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.FindNoteState

interface NoteContract {

    interface ViewModel {

        val saveNoteState: LiveData<SaveNoteState>

        val findNoteState: LiveData<FindNoteState>

        fun save(note: Note)

        fun findById(id: Long)

    }

}