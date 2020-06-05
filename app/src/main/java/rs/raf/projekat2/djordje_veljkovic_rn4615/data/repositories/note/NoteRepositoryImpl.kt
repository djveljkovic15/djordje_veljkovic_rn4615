package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note

import io.reactivex.Completable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.NoteDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteEntity

class NoteRepositoryImpl(
    private val noteDao : NoteDao
) : NoteRepository {

    override fun save(note: Note): Completable {
        val noteEntity = NoteEntity(note.userCreatorUsername, note.content, note.archived)
        return noteDao.save(noteEntity)
    }

    override fun findById(id: Long): Single<Note> {
        return noteDao.findById(id)
            .map {
                Note(it.id, it.noteOwner, it.content, it.archived)
            }
    }
}