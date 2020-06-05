package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteEntity

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(entity: NoteEntity): Completable

    @Query("SELECT * FROM notes WHERE id == :id")
    abstract fun findById(id: Long): Single<NoteEntity>

}