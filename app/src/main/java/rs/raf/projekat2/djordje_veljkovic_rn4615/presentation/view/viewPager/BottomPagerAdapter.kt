package rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments.BeleskaFragment
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments.RasporedFragment
import rs.raf.projekat2.djordje_veljkovic_rn4615.presentation.view.fragments.StatsFragment


class BottomPagerAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_RASPORED = 0
        const val FRAGMENT_BELESKA = 1
        const val FRAGMENT_STATS = 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            FRAGMENT_RASPORED -> RasporedFragment()
            FRAGMENT_BELESKA -> BeleskaFragment()
            else -> StatsFragment()
        }

    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

}