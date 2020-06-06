package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.NoteFilter
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note.NoteRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.NoteContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.AddNoteState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.NoteState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    private val publishSubject: PublishSubject<NoteFilter> = PublishSubject.create()

    override val addNoteState: MutableLiveData<AddNoteState> = MutableLiveData()

    override val noteState: MutableLiveData<NoteState> = MutableLiveData()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .filterNotes(it.title, it.content, it.archived)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    noteState.value = NoteState.AllSuccess(it)
                },
                {
                    noteState.value = NoteState.Error(
                        "Error happened while fetching note data from db!"
                    )
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun save(note: Note) {
        val subscription = noteRepository
            .save(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(" iz save NVM succy")
                    addNoteState.value = AddNoteState.Success
                },
                {
                    Timber.e(" iz save NVM errorr")
                    addNoteState.value = AddNoteState.Error("Error while saving note: $note!")
                }
            )

        subscriptions.add(subscription)
    }

    override fun getAll() {
        val subscription = noteRepository
            .findAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    noteState.value = NoteState.AllSuccess(it)
                }, {
                    noteState.value = NoteState.Error("Error while getting data from database!")
                    Timber.e(it)
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
                    noteState.value = NoteState.Success(it)
                },
                {
                    noteState.value = NoteState.Error("Error while finding note with id: $id!")
                }
            )

        subscriptions.add(subscription)
    }

    override fun filterNote(title: String, content: String, archived: Boolean) {
        publishSubject.onNext(NoteFilter(title, content, archived))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}