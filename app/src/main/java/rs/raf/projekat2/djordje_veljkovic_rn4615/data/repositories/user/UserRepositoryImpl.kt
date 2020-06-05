package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.user.UserDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserEntity

class UserRepositoryImpl(

    private val userDao: UserDao
) : UserRepository {


//    override fun verifyUser(username: String, pin: String): Observable<User> {
//        return userDao
//            .verifyUser(username,pin)
//            .map {
//                User(
//                    username,
//                    pin
//                )
//            }
//    }
    override fun verifyUser(username: String, pin: String): Observable<User> {
        return userDao
            .verifyUser(username,pin)
            .map {
                User(
                    username,
                    pin
                )
            }
    }

    override fun addUser(user: User): Completable {
        val userEntity = UserEntity(user.username,user.pin)
        return userDao.addUser(userEntity)
    }

}
