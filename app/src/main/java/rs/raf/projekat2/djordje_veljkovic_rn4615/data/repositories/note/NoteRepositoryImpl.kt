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

    override fun filterNotes(filter: String, archived: Boolean): Observable<List<Note>> {
        return noteDao.filterNotes(filter, archived)
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

    override fun deleteById(id: Long): Completable {
        return noteDao.deleteById(id)
    }

    override fun update(existingId: Long, note: Note): Completable {
        return noteDao.update(existingId, note.title, note.content, note.archived)
    }

    override fun saveTestNotes(): Completable {
        val note1 = NoteEntity("Dzo","Title 1", "Ovo je neki content iz title1 gospodina... :) ", false)
        val note2 = NoteEntity("Dzo","Title 2", "Ovo je neki content iz title2 smaraca... :) ", false)
        val note3 = NoteEntity("Dzo","Title 3", "Ovo je neki content iz title3 ludaka... :) ", false)
        val note4 = NoteEntity("Dzo","Dugacki Title", "Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content Ovo je neki dugacki content  ", false)

        return noteDao.saveAll(listOf(note1, note2, note3, note4))
    }


}