package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff.BeleskaDiffCallback
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder.BeleskaViewHolder

class BeleskaAdapter(beleskaDiffCallback: BeleskaDiffCallback,
                     val onDeleteClicked: (Note)->Unit,
//                     val onEditClicked: (Note?)->Unit,
                     val onEditClicked: (Note)->Unit,
                     val onArchiveClicked: (Note)->Unit
                     ) : ListAdapter<Note, BeleskaViewHolder>(beleskaDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeleskaViewHolder {
        //val layoutInflater = LayoutInflater.from(parent.context)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_beleske, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_beleske_cardview, parent, false)
        return BeleskaViewHolder(view,
            {
                val note = getItem(it)
                onDeleteClicked.invoke(note)
            },{
//                val note = it?.let { it1 -> getItem(it1) }
                val note = getItem(it)
                onEditClicked.invoke(note)
            },{
                val note = getItem(it)
                onArchiveClicked.invoke(note)
            })
    }

    override fun onBindViewHolder(holder: BeleskaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}