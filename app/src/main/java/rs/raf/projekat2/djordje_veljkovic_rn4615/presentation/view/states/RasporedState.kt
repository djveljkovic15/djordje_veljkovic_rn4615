package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states

import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.Raspored

sealed class RasporedState {

    object Loading: RasporedState()

    object DataFetched: RasporedState()

    data class Success(val raspored: List<Raspored>) : RasporedState()

    data class Error(val message: String) : RasporedState()

}