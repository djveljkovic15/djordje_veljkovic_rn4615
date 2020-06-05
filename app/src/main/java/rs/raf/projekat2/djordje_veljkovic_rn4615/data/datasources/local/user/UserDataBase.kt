package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.user

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

}