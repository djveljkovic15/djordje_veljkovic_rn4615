package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.Raspored
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff.RasporedDiffCallback
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder.RasporedViewHolder

class RasporedAdapter(rasporedDiffCallback: RasporedDiffCallback) : ListAdapter<Raspored, RasporedViewHolder>(rasporedDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RasporedViewHolder {
        //val layoutInflater = LayoutInflater.from(parent.context)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_raspored_termini, parent, false)
        return RasporedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RasporedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}