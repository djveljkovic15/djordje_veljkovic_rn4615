package rs.raf.projekat2.djordje_veljkovic_rn4615.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.Dzoaza
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.remote.RasporedService
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.raspored.RasporedRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.raspored.RasporedRepositoryImpl
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.RasporedViewModel

val rasporedModule = module {

    viewModel {
        RasporedViewModel(rasporedRepository = get())
    }

    single<RasporedRepository> {
        RasporedRepositoryImpl(
            rasporedService = get(),
            rasporedDao = get()
        )
    }

    single {
        get<Dzoaza>().getRasporedDao()
    }

    single<RasporedService> {
        create(retrofit = get())
    }

}