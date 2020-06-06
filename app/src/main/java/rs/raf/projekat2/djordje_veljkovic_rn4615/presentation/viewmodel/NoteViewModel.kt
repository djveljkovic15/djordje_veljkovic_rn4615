package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note.NoteRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user.UserRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.NoteContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.FindNoteState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.SaveNoteState

class NoteViewModel(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository
) : ViewModel(), NoteContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val saveNoteState: MutableLiveData<SaveNoteState> = MutableLiveData()

    override val findNoteState: MutableLiveData<FindNoteState> = MutableLiveData()



    override fun save(note: Note) {
        val subscription = noteRepository.save(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    saveNoteState.value = SaveNoteState.Success
                },
                {
                    saveNoteState.value = SaveNoteState.Error("Error while saving note: $note!")
                }
            )

        subscriptions.add(subscription)
    }

    override fun findById(id: Long) {
        val subscription = noteRepository.findById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    findNoteState.value = FindNoteState.FindOneSuccess(it)
                },
                {
                    findNoteState.value = FindNoteState.Error("Error while finding note with id: $id!")
                }
            )

        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}