package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "raspored")
data class RasporedEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: List<String> = listOf(),
    val dan: String,
    val termin: String,
    val ucionica: String
)