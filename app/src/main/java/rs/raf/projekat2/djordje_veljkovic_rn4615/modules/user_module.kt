package rs.raf.projekat2.djordje_veljkovic_rn4615.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.user.UserDataBase
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user.UserRepository
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user.UserRepositoryImpl
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.UserViewModel

val userModule = module {

    viewModel {
        UserViewModel(userRepository = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(
            userDao = get()
        )
    }

    single {
        get<UserDataBase>().getUserDao()
    }

}