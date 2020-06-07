package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.contract.UserContract
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.states.UserState
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.viewmodel.UserViewModel
import timber.log.Timber


class LoginActivity: AppCompatActivity(R.layout.activity_login) {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        var username : String = sharedPreferences.getString(MainActivity.USERNAME, "")?: ""
        var pin : String = sharedPreferences.getString(MainActivity.PIN, "")?: ""

        loginBtn.setOnClickListener(){
            username = usernameEt.text.toString()
            pin = pinEt.text.toString()
            Timber.e("WHAT?")
            Timber.e("ovo: "+username + pin)
            userViewModel.verifyUser(username, pin)
        }

        userViewModel.logged.observe(this, Observer {
            when (it) {
                is UserState.Logged -> {
                    val intent = Intent(this, MainActivity::class.java)
                    Timber.e("Logged iz login activity")
                    val editor = sharedPreferences.edit()
                    editor.putString(MainActivity.USERNAME, username)
                    editor.putString(MainActivity.PIN, pin)
                    editor.apply()
                    startActivity(intent)
                    finish()
                }
                is UserState.Error -> {
                    when {
                        pin.isEmpty() -> {
                            Toast.makeText(this, "Pin ne sme biti prazan!", Toast.LENGTH_SHORT)
                                .show()
                        }
                        pin.length != 4 -> {
                            Toast.makeText(
                                this,
                                "Pin mora biti duzine od 4 cifre!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> {
                            Toast.makeText(this, "Pogresno uneti podaci!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    //Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}