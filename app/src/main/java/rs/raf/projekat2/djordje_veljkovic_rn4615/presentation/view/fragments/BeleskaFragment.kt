package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.NoteContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.RasporedContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.NoteViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.RasporedViewModel

class BeleskaFragment : Fragment(R.layout.fragment_beleska){

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        noteViewModel.findById(1)
    }

}