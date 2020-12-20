package app.andika.dindinassignment.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.andika.dindinassignment.utilities.CART
import app.andika.dindinassignment.utilities.INFORMATION
import app.andika.dindinassignment.utilities.ORDER
import app.andika.dindinassignment.view.detail.CartFragment
import app.andika.dindinassignment.view.detail.InformationFragment
import app.andika.dindinassignment.view.detail.OrderFragment

class BoughtItemListPagerAdapter(fm: FragmentManager?, private val context: Context) : FragmentPagerAdapter(fm!!) {
    val PAGE_COUNT = 3
    private val tabTitles = arrayOf(CART, ORDER, INFORMATION)
    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            CartFragment()
        } else if (position == 1) {
            OrderFragment()
        } else if (position == 2) {
            InformationFragment()
        } else {
            CartFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return tabTitles[position]
    }

}