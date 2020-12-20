package app.andika.dindinassignment.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.andika.dindinassignment.view.main.FoodFragment

class FoodListPagerAdapter(fm: FragmentManager?, private val context: Context) : FragmentPagerAdapter(fm!!) {
    val PAGE_COUNT = 3
    private val tabTitles = arrayOf("Pizza", "Sushi", "Drinks")
    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return FoodFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return tabTitles[position]
    }

}