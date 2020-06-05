package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note

interface NoteRepository {

    fun save(note: Note): Completable

    fun findById(id: Long): Single<Note>

}