package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserEntity

//import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserEntityWithNotes

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addUser(entity: UserEntity): Completable

    @Query("SELECT * FROM users WHERE username == :username AND pin == :pin")
    abstract fun verifyUser(username: String, pin: String): Single<UserEntity>

    // room runs 2 queries so transaction is recommended
//    @Transaction
//    @Query("SELECT * FROM users WHERE username == :username")
//    abstract fun findUserWithNotesByUsername(username: String): Single<UserEntityWithNotes>

}