package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.MainContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.RasporedViewModel

class RasporedFragment : Fragment(R.layout.fragment_raspored) {

    private val rasporedViewModel: MainContract.ViewModel by sharedViewModel<RasporedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rasporedViewModel.fetchRaspored()
    }
}