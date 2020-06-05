package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_raspored_termini.*
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.raspored.Raspored

class RasporedViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(raspored: Raspored) {
        recyclerOtpusteniPredmetTv.text = raspored.predmet
        recyclerOtpusteniNastavnikTv.text = raspored.nastavnik
        recyclerOtpusteniTipTv.text = raspored.tip
        recyclerOtpusteniTerminTv.text = raspored.termin
        recyclerOtpusteniUcionicaTv.text = raspored.ucionica
        recyclerOtpusteniGrupeTv.text = raspored.grupe
        recyclerOtpusteniDanTv.text = raspored.dan


    }

}