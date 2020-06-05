package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user

import androidx.room.Embedded
import androidx.room.Relation
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteEntity

data class UserEntityWithNotes(
    @Embedded
    val user: UserEntity,
    @Relation(
        parentColumn = "username",
        entityColumn = "noteOwner",
        entity = NoteEntity::class
    )
    val kurac: List<NoteEntity>
)