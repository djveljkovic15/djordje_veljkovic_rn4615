package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.NoteDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteEntity

class NoteRepositoryImpl(
    private val noteDao : NoteDao
) : NoteRepository {

    override fun save(note: Note): Completable {
        val noteEntity = NoteEntity(note.userCreatorUsername,note.title, note.content, note.archived)
        return noteDao.save(noteEntity)
    }

    override fun findById(id: Long): Single<Note> {
        return noteDao.findById(id)
            .map {
                Note(it.id, it.noteOwner, it.title, it.content, it.archived)
            }
    }

    override fun findAll(): Observable<List<Note>> {
        return noteDao.findAll()
            .map {
                it.map {entity ->
                    Note(
                        entity.id,
                        entity.noteOwner,
                        entity.title,
                        entity.content,
                        entity.archived
                    )
                }
            }
    }

    override fun findAllArchived(): Observable<List<Note>> {
        return noteDao.findAllArchived()
            .map {
                it.map {entity ->
                    Note(
                        entity.id,
                        entity.noteOwner,
                        entity.title,
                        entity.content,
                        entity.archived
                    )
                }
            }
    }

    override fun findAllNotArchived(): Observable<List<Note>> {
        return noteDao.findAllNotArchived()
            .map {
                it.map {entity ->
                    Note(
                        entity.id,
                        entity.noteOwner,
                        entity.title,
                        entity.content,
                        entity.archived
                    )
                }
            }
    }

    override fun filterNotes(title: String, content: String, archived: Boolean): Observable<List<Note>> {
        return noteDao.filterNotes(title, content, archived)
            .map {
                it.map {entity ->
                    Note(
                        entity.id,
                        entity.noteOwner,
                        entity.title,
                        entity.content,
                        entity.archived
                    )
                }
            }
    }
}