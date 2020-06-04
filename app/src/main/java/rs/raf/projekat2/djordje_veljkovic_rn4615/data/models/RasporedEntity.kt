package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "raspored")
data class RasporedEntity(
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}