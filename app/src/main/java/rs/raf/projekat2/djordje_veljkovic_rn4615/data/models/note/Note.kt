package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note

data class Note (
    val id: Long,
    val userCreatorUsername: String,
    val title: String,
    val content: String,
    val archived: Boolean
)