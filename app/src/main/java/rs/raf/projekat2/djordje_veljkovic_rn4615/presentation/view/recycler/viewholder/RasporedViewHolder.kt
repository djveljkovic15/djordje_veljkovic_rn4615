package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_raspored_termini.*
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.Raspored

class RasporedViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(raspored: Raspored) {
        recyclerRasporedPredmetTv.text = raspored.predmet
        recyclerRasporedNastavnikTv.text = raspored.nastavnik
        recyclerRasporedTipTv.text = raspored.tip
        recyclerRasporedTerminTv.text = raspored.termin
        recyclerRasporedUcionicaTv.text = raspored.ucionica
        recyclerRasporedGrupeTv.text = raspored.grupe
        recyclerRasporedDanTv.text = raspored.dan


    }

}