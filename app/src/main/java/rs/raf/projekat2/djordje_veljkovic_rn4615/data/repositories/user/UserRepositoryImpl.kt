package rs.raf.projekat2.djordje_veljkovic_rn4615.data.repositories.user

import io.reactivex.Completable
import io.reactivex.Single
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.datasources.local.UserDao
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.note.Note
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserEntity
//import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.UserWithNotes
import timber.log.Timber

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun verifyUser(username: String, pin: String): Single<User> {
        return userDao
            .verifyUser(username,pin)
            .map {
                Timber.e("dje si poso")
                User(
                    it.username,
                    it.pin
                )
            }

    }

    override fun addUser(user: User): Completable {
        val userEntity = UserEntity(user.username,user.pin)
        return userDao.addUser(userEntity)
    }

//    override fun findUserWithNotesByUsername(username: String): Single<UserWithNotes> {
//        return userDao.findUserWithNotesByUsername(username)
//            .map {
//                val user = User(it.user.username, it.user.pin)
//                val notes: MutableList<Note> = mutableListOf()
//                it.notes.forEach { ti ->
//                    val note = Note(ti.id, ti.noteOwner, ti.content, ti.archived)
//                    notes.add(note)
//                }
//                UserWithNotes(user, notes)
//            }
//    }
}
