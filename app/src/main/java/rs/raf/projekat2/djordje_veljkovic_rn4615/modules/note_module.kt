package rs.raf.projekat2.djordje_veljkovic_rn4615.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.Dzoaza
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note.NoteRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.note.NoteRepositoryImpl
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.NoteViewModel

val noteModule = module{

    single {
        get<Dzoaza>().getNoteDao()
    }

    single<NoteRepository> {
        NoteRepositoryImpl(
            noteDao = get()
        )
    }

    viewModel {
        NoteViewModel(noteRepository = get())
    }

}