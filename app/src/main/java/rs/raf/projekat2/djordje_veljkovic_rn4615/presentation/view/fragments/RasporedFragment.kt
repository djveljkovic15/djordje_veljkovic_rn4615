package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_raspored.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.RasporedContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.adapter.RasporedAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff.RasporedDiffCallback
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.RasporedState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.RasporedViewModel
import timber.log.Timber

class RasporedFragment : Fragment(R.layout.fragment_raspored) {

    private val rasporedViewModel: RasporedContract.ViewModel by sharedViewModel<RasporedViewModel>()
    lateinit var rasporedAdapter : RasporedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        initRecyclerView()
        initObservers()
        initFilter()
    }

    private fun initFilter() {

        var filter : String = ""
        var grupa : String = ""
        var dan : String = ""

        fragmentRasporedFilterEt.doAfterTextChanged {
            filter = fragmentRasporedFilterEt.text.toString()

            rasporedViewModel.filterRaspored(filter,grupa,dan)
        }
        fragmentRasporedGrupaEt.doAfterTextChanged {
            grupa = fragmentRasporedGrupaEt.text.toString()

            rasporedViewModel.filterRaspored(filter,grupa,dan)
        }
        fragmentRasporedDanEt.doAfterTextChanged {
            dan = fragmentRasporedDanEt.text.toString()

            rasporedViewModel.filterRaspored(filter,grupa,dan)
        }

//        rasporedViewModel.filterRaspored(filter,grupa,dan)
//        rasporedViewModel.filterRaspored("Irena","","")

    }

    private fun initObservers() {
        rasporedViewModel.rasporedState.observe(viewLifecycleOwner, Observer {
            Timber.e("REEE %s", it.toString())
            renderState(it)
        })

        rasporedViewModel.fetchRaspored() // ako stavim pre, nemam problem?
        rasporedViewModel.getRaspored()
        // Zasto drzim fetchRaspored ovde?
//        rasporedViewModel.fetchRaspored()

    }

    private fun initRecyclerView() {
        fragmentRasporedRW.layoutManager = LinearLayoutManager(activity)
        rasporedAdapter = RasporedAdapter(RasporedDiffCallback())
        fragmentRasporedRW.adapter = rasporedAdapter
    }


    private fun renderState(state: RasporedState) {
        when (state) {
            is RasporedState.Success -> {
                showLoadingState(false)
                rasporedAdapter.submitList(state.raspored)
            }
            is RasporedState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is RasporedState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is RasporedState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        fragmentRasporedFilterEt.isVisible = !loading
        fragmentRasporedRW.isVisible = !loading
        loadingPb.isVisible = loading
    }

}