package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    val noteOwner: String,
    val content: String,
    val archived: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}