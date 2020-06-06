package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note


class BeleskaDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.userCreatorUsername == newItem.userCreatorUsername &&
                 oldItem.title == newItem.title &&
                    oldItem.content == newItem.content &&
                     oldItem.archived == newItem.archived



    }

}