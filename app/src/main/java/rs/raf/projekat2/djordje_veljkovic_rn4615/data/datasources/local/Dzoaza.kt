package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteEntity
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.RasporedEntity
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserEntity

@Database(
    entities = [UserEntity::class, RasporedEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Dzoaza : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getRasporedDao(): RasporedDao

    abstract fun getNoteDao(): NoteDao

}