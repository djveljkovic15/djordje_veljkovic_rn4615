package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff.BeleskaDiffCallback
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder.BeleskaViewHolder

class BeleskaAdapter(beleskaDiffCallback: BeleskaDiffCallback) : ListAdapter<Note, BeleskaViewHolder>(beleskaDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeleskaViewHolder {
        //val layoutInflater = LayoutInflater.from(parent.context)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_beleske, parent, false)
        return BeleskaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeleskaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}