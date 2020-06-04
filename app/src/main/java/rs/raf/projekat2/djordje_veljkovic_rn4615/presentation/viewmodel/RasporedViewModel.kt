package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.ViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.RasporedRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.MainContract

class RasporedViewModel(
    private val rasporedRepository: RasporedRepository
) : ViewModel(), MainContract.ViewModel {

    override fun getRaspored() {
        rasporedRepository.getRaspored()
    }

}