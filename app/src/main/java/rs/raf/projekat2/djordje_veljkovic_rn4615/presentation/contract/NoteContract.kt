package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.AddNoteState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.NoteState

interface NoteContract {

    interface ViewModel {

        val addNoteState: LiveData<AddNoteState>

        val noteState: LiveData<NoteState>

        fun save(note: Note)

        fun findById(id: Long)

        fun filterNote(title: String, content: String, archived: Boolean)

        fun getAll()

    }

}