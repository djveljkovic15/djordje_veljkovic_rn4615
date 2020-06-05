package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey()
    val username : String,
    val pin : String
)