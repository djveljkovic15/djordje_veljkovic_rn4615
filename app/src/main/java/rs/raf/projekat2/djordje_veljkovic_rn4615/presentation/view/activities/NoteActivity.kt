package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.NoteViewModel

class NoteActivity() : AppCompatActivity(R.layout.activity_note){


    val noteViewModel: NoteViewModel  by viewModel<NoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        init()
    }
    private fun init() {
        val note: Note?

        intent?.let {

            note  = it.getParcelableExtra(MainActivity.MESSAGE_KEY)

            if (note!=null){
                saveChanges(note)
                popuni(note)
            }else{
                saveNote()
            }
        }
        back()
    }

    private fun popuni(note: Note) {
        activityNoteTitleEt.setText(note.title)
        activityNoteContentEt.setText(note.content)
    }

    private fun back() {
        activityNoteOdustaniBtn.setOnClickListener {//Da li mogu da bacim samo finish() ?
            finish()
        }
    }

    private fun saveChanges(note: Note){
        activityNoteIzmeniBtn.setOnClickListener {

            val title = activityNoteTitleEt.text.toString()
            val content = activityNoteContentEt.text.toString()

            if (title != "") {
                val newNote = Note(note.id,note.userCreatorUsername,title,content,note.archived )
                noteViewModel.update(note.id,newNote)
                finish()
            }
        }
    }
    private fun saveNote(){
        activityNoteIzmeniBtn.setOnClickListener {

            val title = activityNoteTitleEt.text.toString()
            val content = activityNoteContentEt.text.toString()

            if (title != "") {
                val newNote = Note(666,"FixedUsername",title,content,false )
                noteViewModel.save(newNote)
                finish()
            }
        }
    }
}