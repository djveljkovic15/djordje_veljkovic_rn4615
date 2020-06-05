package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.raspored

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.RasporedEntity

@Dao
abstract class RasporedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: RasporedEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<RasporedEntity>): Completable

    @Query("DELETE FROM raspored")
    abstract fun  deleteAll()

    @Query("SELECT * FROM raspored")
    abstract fun findAll(): Observable<List<RasporedEntity>>

    @Transaction
    open fun deleteAndInsertAll(entities: List<RasporedEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM raspored WHERE ((nastavnik LIKE '%'|| :filter || '%' OR  '%'|| predmet LIKE '%'|| :filter || '%') AND grupe LIKE '%' || :grupa || '%' AND dan LIKE '%' || :dan || '%')")
//    @Query("SELECT * FROM raspored WHERE nastavnik LIKE '%'|| :filter || '%' ")
    abstract fun filterRaspored(filter:String, grupa:String, dan: String):Observable<List<RasporedEntity>>
}