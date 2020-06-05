package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User

interface UserRepository {

    fun verifyUser(username: String, pin: String): Observable<User>
    fun addUser(user: User):Completable
}