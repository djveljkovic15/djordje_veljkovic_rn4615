package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.RasporedEntity

@Database(
    entities = [RasporedEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class RasporedDataBase : RoomDatabase() {

    abstract fun getRasporedDao(): RasporedDao

}