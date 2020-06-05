package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.Raspored


class RasporedDiffCallback : DiffUtil.ItemCallback<Raspored>() {

    override fun areItemsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.nastavnik == newItem.nastavnik &&
                oldItem.dan == newItem.dan &&
                oldItem.grupe == newItem.grupe &&
                oldItem.predmet == newItem.predmet &&
                oldItem.termin == newItem.termin &&
                oldItem.tip == newItem.tip &&
                oldItem.ucionica == newItem.ucionica




    }

}