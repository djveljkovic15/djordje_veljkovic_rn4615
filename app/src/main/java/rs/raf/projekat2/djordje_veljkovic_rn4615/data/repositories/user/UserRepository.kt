package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user

import io.reactivex.Completable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
//import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserWithNotes

interface UserRepository {

    fun addUser(user: User):Completable
    fun verifyUser(username: String, pin: String): Single<User> // ?
//    fun findUserWithNotesByUsername(username: String): Single<UserWithNotes>
}