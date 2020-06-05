package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.data.models.user.User
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.UserContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.LoginActivity
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.MainActivity
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
        val username : String = "Dzo"
        val pin: String = "1234"
        val newUser = User(username, pin)
        userViewModel.addUser(newUser)
    }

    private fun initVerifyUser() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)


        var username : String = sharedPreferences.getString(MainActivity.USERNAME, "")?: ""
        var pin : String = sharedPreferences.getString(MainActivity.PIN, "")?: ""

        Timber.e(username, pin)
        var usercina  = userViewModel.verifyUser("Dzo", "1234")

        Timber.e(userViewModel.verifyUser("Dzo","1234").toString())


    }


    private fun initSharedPref() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)


        var username : String = sharedPreferences.getString(MainActivity.USERNAME, "")?: ""
        var pin : String = sharedPreferences.getString(MainActivity.PIN, "")?: ""

        Timber.e(username, pin)
        isLogged(username, pin)


    }

    private fun isLogged(username : String, pin : String) {
        if (username.length in 1..11 && pin == "1234") {

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra(MainActivity.USERNAME, username)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }
    }


}