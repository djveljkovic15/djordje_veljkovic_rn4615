package rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note (
    val id: Long,
    val userCreatorUsername: String,
    var title: String,
    val content: String,
    val archived: Boolean
): Parcelable