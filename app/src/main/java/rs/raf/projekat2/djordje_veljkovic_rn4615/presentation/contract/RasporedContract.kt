package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.RasporedState


interface RasporedContract {

    interface ViewModel {

        val rasporedState: LiveData<RasporedState>

        fun fetchRaspored()

        fun getRaspored()

        fun filterRaspored(filter: String, grupa:String, dan:String)
    }

}