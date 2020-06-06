package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteEntity
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.RasporedEntity

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(entity: NoteEntity): Completable

    @Query("SELECT * FROM notes WHERE id == :id")
    abstract fun findById(id: Long): Single<NoteEntity>

    @Query("SELECT * FROM notes")
    abstract fun findAll(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE archived = 1")
    abstract fun findAllArchived(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE archived = 0")
    abstract fun findAllNotArchived(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE ((title LIKE '%' || :title || '%' OR '%' || content LIKE '%' || :content || '%') AND archived = :archived)")
    abstract fun filterNotes(title: String, content: String, archived: Boolean): Observable<List<NoteEntity>>

}