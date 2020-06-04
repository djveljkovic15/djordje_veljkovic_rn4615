package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.LoginActivity
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities.MainActivity
import timber.log.Timber

public class SplashScreen : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }


    private fun init() {
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