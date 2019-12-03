package com.travelcar_test.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.travelcar_test.ui.account.AccountFragment
import com.travelcar_test.ui.car_list.CarsListFragment
import javax.inject.Inject

class TabsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CarsListFragment.newInstance()
            }
            1 -> {
                AccountFragment()
            }
            else -> return CarsListFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Cars List"
            1-> "Account"
            else -> "Cars List"
        }
    }
}