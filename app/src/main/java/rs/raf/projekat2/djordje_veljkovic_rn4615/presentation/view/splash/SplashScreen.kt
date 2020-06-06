package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.UserContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.LoginActivity
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.MainActivity
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.UserState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.UserViewModel
import timber.log.Timber

public class SplashScreen : AppCompatActivity(){

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {
        initNewUser()
        initVerifyUser()
//        initSharedPref()
    }
    private fun initNewUser() {
        val username = "user"
        val pin = "1234"
        val newUser = User(username, pin)
        userViewModel.addUser(newUser)
    }

    private fun initVerifyUser() {

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)


        var username : String = sharedPreferences.getString(MainActivity.USERNAME, "")?: ""
        var pin : String = sharedPreferences.getString(MainActivity.PIN, "")?: ""



        userViewModel.verifyUser(username, pin)

        Timber.e("Zablokiran na splashu!?")
        userViewModel.logged.observe(this, Observer {

            Timber.e("Zablokiran na splashuuuuuuuuuu!?")
            renderState(it, username, pin)

        })
//        renderState(UserState.Error, username, pin)

        Timber.e("Zablokiran na splashuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu!?")
//        initObserver(username, pin)


    }

//    private fun initObserver(username: String, pin: String){
//        userViewModel.logged.observe(this, Observer {
//            renderState(it, username, pin)
//
//        })
//    }
    private fun renderState(state: UserState,username:String, pin: String  ) {
        when (state) {
            is UserState.Logged -> {
                val intent = Intent(this, MainActivity::class.java)
                Timber.e("Logged iz splash screen")
                intent.putExtra(MainActivity.USERNAME, username)
                intent.putExtra(MainActivity.PIN, pin)
                startActivity(intent)
                finish()
            }
            is UserState.Error -> {
                Timber.e("Error iz splash screen")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                finish()
            }
            else ->{
                Timber.e("Error iz splash screen DEFAULT VERZIJA")
            }

        }
    }


//    private fun initSharedPref() {
//        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
//
//
//        var username : String = sharedPreferences.getString(MainActivity.USERNAME, "")?: ""
//        var pin : String = sharedPreferences.getString(MainActivity.PIN, "")?: ""
//
//        Timber.e(username, pin)
//        isLogged(username, pin)
//
//
//    }
//
//    private fun isLogged(username : String, pin : String) {
//        if (username.length in 1..11 && pin == "1234") {
//
//            val intent = Intent(this, MainActivity::class.java)
//
//            intent.putExtra(MainActivity.USERNAME, username)
//            startActivity(intent)
//            finish()
//        }else{
//            val intent = Intent(this, LoginActivity::class.java)
//
//            startActivity(intent)
//            finish()
//        }
//    }


}