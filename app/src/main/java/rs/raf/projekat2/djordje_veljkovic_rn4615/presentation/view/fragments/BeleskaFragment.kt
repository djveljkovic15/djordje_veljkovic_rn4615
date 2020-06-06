package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleska.*
import kotlinx.android.synthetic.main.fragment_raspored.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.NoteContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.adapter.BeleskaAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff.BeleskaDiffCallback
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.FindNoteState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.NoteViewModel
import timber.log.Timber

class BeleskaFragment : Fragment(R.layout.fragment_beleska){

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()
    lateinit var noteAdapter: BeleskaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
//        noteViewModel.findById(1)

        initRecycleView()
        initObservers()

    }

    private fun initObservers() {
        noteViewModel.findNoteState.observe(viewLifecycleOwner, Observer {
            Timber.e("beleskaaaaaaaaaaaaa moooliiitvaaaa")
            renderState(it)
        })
//        noteViewModel.getNotes()
    }

    private fun renderState(noteState: FindNoteState) {
//        when (noteState) {
//            is FindNoteState.Success -> {
//                showLoadingState(false)
//                rasporedAdapter.submitList(state.raspored)
//            }
//            is FindNoteState.Error -> {
//                showLoadingState(false)
//                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
//            }
//            is FindNoteState.DataFetched -> {
//                showLoadingState(false)
//                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
//            }
//            is FindNoteState.Loading -> {
//                showLoadingState(true)
//            }
//        }
    }

    private fun initRecycleView() {
        fragmentBeleskeRW.layoutManager = LinearLayoutManager(activity)
        noteAdapter = BeleskaAdapter(BeleskaDiffCallback())
        fragmentBeleskeRW.adapter = noteAdapter

    }

    private fun showLoadingState(loading: Boolean) {
        fragmentRasporedFilterEt.isVisible = !loading
        fragmentRasporedRW.isVisible = !loading
        loadingPb.isVisible = loading
    }
}