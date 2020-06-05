package rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.raspored

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.RasporedDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.RasporedEntity

@Database(entities = [RasporedEntity::class], version = 1)
abstract class RasporedDataBase : RoomDatabase() {

    abstract fun getRasporedDao(): RasporedDao

}