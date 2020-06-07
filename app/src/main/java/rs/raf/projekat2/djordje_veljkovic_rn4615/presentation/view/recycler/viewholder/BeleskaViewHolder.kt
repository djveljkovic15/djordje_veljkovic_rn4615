package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_beleske.*
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note

class BeleskaViewHolder(override val containerView: View,
                        onDeleteClicked:(Int)->Unit,
                        onEditClicked:(Int)->Unit,
                        onArhiveClicked:(Int)->Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        recyclerBeleskeDeleteIv.setOnClickListener{
            onDeleteClicked.invoke(adapterPosition)
        }
        recyclerBeleskeEditIv.setOnClickListener{
            onEditClicked.invoke(adapterPosition)
        }
//        fragmentBeleskeNewCb.setOnClickListener{
//            onEditClicked.invoke(adapterPosition)
//        }

        recyclerBeleskeArchiveIv.setOnClickListener{
            onArhiveClicked.invoke(adapterPosition)
        }
    }
    fun bind(beleska: Note) {
        recyclerBeleskeTitleTv.text = beleska.title
        recyclerBeleskeContentTv.text = beleska.content

    }

}