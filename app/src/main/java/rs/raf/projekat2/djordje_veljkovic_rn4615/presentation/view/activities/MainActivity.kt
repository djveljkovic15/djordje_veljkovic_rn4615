package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import rs.raf.projekat2.djordje_veljkovic_rn4615.R
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.viewPager.BottomPagerAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val USERNAME = "username"
        const val PIN = "pin"
        const val MESSAGE_KEY = "666"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    private fun init() {
        initViewPager()
        initNavigation()

    }

    private fun initNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_1 -> {
                    viewPager.setCurrentItem(BottomPagerAdapter.FRAGMENT_RASPORED, false)
                }

                R.id.navigation_2 -> {
                    viewPager.setCurrentItem(BottomPagerAdapter.FRAGMENT_BELESKA, false)
                }

                R.id.navigation_3 -> {
                    viewPager.setCurrentItem(BottomPagerAdapter.FRAGMENT_STATS, false)
                }

            }
            true
        }

    }

    private fun initViewPager() {
        viewPager.adapter = BottomPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 3
    }

}