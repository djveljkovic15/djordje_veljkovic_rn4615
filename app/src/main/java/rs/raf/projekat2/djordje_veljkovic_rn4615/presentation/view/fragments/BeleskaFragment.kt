package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleska.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.NoteContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.MainActivity
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.NoteActivity
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.adapter.BeleskaAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.recycler.diff.BeleskaDiffCallback
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.NoteState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.NoteViewModel
import timber.log.Timber

class BeleskaFragment : Fragment(R.layout.fragment_beleska), CompoundButton.OnCheckedChangeListener{

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()
    lateinit var noteAdapter: BeleskaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecycleView()
        initObservers()
        initFilter()

        onCheckedChanged(fragmentBeleskeSw,false)

//        initAddTestNotes()        //Trenutno puni svaki put, idealno puni samo jednom ali je test tako da :shrug:
    }

    private fun initAddTestNotes() {
//        val note1 = Note(1,"Dzo","Title 1", "Ovo je neki content... :) ", false)
//        val note2 = Note(2,"Dzo","Title 2", "Ovo je neki content... :) ", false)
//        val note3 = Note(3,"Dzo","Title 3", "Ovo je neki content... :) ", false)
//        noteViewModel.save(note1)
//        noteViewModel.save(note2)
        noteViewModel.saveTestNotes()
    }

    // Nesto sam ovde upetljao, imam bug da dok ne switchujem prvi put nece dobro da filtrira novoarhivirane
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when(buttonView?.id){
            R.id.fragmentBeleskeSw -> {

                fragmentBeleskeSw.setOnCheckedChangeListener(this)

                noteViewModel.filterNote(fragmentBeleskeFilterEt.text.toString(), isChecked)
            }else->{
                Timber.e("456")

            }
        }
    }

    private fun initFilter() {

        fragmentBeleskeSw.setOnCheckedChangeListener(this)

        var filter = ""
//        var archive : Boolean

        fragmentBeleskeFilterEt.doAfterTextChanged {
            filter = fragmentBeleskeFilterEt.text.toString()
//            archive = false

            noteViewModel.filterNote(filter, fragmentBeleskeSw.isChecked)
        }

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
        initNewNote()
        fragmentBeleskeRW.layoutManager = LinearLayoutManager(activity)
        noteAdapter = BeleskaAdapter(BeleskaDiffCallback(),
            {
                noteViewModel.deleteById(it.id)

            },{
                // Mogu i da stavim da prosledjujem samo ID, pa da u NoteActivity radim getNoteByID
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra(MainActivity.MESSAGE_KEY, it)
                startActivity(intent)

            },{ // Mogli smo da napravimo samo setArchived kao poziv ka bazi
                val updatedNote = Note(it.id,it.userCreatorUsername,it.title,it.content,!it.archived)
                noteViewModel.update(it.id, updatedNote)
            })
        fragmentBeleskeRW.adapter = noteAdapter

    }

    private fun initNewNote() {
        fragmentBeleskeNewCb.setOnClickListener {
            val intent = Intent(context, NoteActivity::class.java)
            startActivity(intent)
        }
    }

//
//    private fun showLoadingState(loading: Boolean) {
//        fragmentRasporedFilterEt.isVisible = !loading
//        fragmentRasporedRW.isVisible = !loading
//        loadingPb.isVisible = loading
//    }
}