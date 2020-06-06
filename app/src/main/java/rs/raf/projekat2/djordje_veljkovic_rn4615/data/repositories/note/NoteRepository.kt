package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note

interface NoteRepository {

    fun save(note: Note): Completable

    fun findById(id: Long): Single<Note>

    fun findAll(): Observable<List<Note>>

    fun findAllArchived(): Observable<List<Note>>

    fun findAllNotArchived(): Observable<List<Note>>

    fun filterNotes(filter: String, archived: Boolean): Observable<List<Note>>
//    fun filterNotes(title: String, content: String, archived: Boolean): Observable<List<Note>>

    fun deleteById(id: Long): Completable

    fun update(existingId: Long, note: Note): Completable

    fun saveTestNotes(): Completable

}