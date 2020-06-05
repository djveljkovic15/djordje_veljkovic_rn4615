package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserEntity

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addUser(entity: UserEntity): Completable

    @Query("SELECT * FROM users WHERE username == :username AND pin == :pin")
    abstract fun verifyUser(username: String, pin: String): Single<UserEntity>
}