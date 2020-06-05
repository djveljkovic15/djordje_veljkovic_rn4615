package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.note

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.NoteDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

}