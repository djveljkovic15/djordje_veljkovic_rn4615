package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleska.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.NoteContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.adapter.BeleskaAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff.BeleskaDiffCallback
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.NoteState
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

        initAddTestNotes()
        initRecycleView()
        initObservers()

    }

    private fun initAddTestNotes() {
        val note1 = Note(1,"Dzo","Title 1", "Ovo je neki content... :) ", false)
        val note2 = Note(2,"Dzo","Title 2", "Ovo je neki content... :) ", false)
        val note3 = Note(3,"Dzo","Title 3", "Ovo je neki content... :) ", false)
        noteViewModel.save(note1)
        noteViewModel.save(note2)
        noteViewModel.save(note3)
    }

    private fun initObservers() {
        noteViewModel.noteState.observe(viewLifecycleOwner, Observer {
            Timber.e("beleskaaaaaaaaaaaaa moooliiitvaaaa")
            renderState(it)
        })
        noteViewModel.getAll()
    }

    private fun renderState(noteState: NoteState) {
        when (noteState) {
            is NoteState.AllSuccess -> {
//                showLoadingState(false)
                noteAdapter.submitList(noteState.notes)
            }
            is NoteState.Error -> {
//                showLoadingState(false)
                Toast.makeText(context, noteState.message, Toast.LENGTH_SHORT).show()
            }
//            is NoteState.Loading -> {
//                showLoadingState(true)
//            }
        }
    }

    private fun initRecycleView() {
        fragmentBeleskeRW.layoutManager = LinearLayoutManager(activity)
        noteAdapter = BeleskaAdapter(BeleskaDiffCallback(),
            {
//                noteViewModel.deleteNote(it.id)

            },{
                //otvara novi prozor koji povlaci podatke\menja u bazi
                //noteViewModel.save(it)
            },{ // Da li ovde mogu ovako ili da mu bacam da radi to u repou?
                var updatedNote = Note(it.id,it.userCreatorUsername,it.title,it.content,!it.archived)
                noteViewModel.save(updatedNote)
            })
        fragmentBeleskeRW.adapter = noteAdapter

    }
//
//    private fun showLoadingState(loading: Boolean) {
//        fragmentRasporedFilterEt.isVisible = !loading
//        fragmentRasporedRW.isVisible = !loading
//        loadingPb.isVisible = loading
//    }
}