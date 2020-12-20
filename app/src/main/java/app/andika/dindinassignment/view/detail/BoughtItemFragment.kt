package app.andika.dindinassignment.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import app.andika.dindinassignment.R
import app.andika.dindinassignment.adapter.BoughtItemListPagerAdapter
import app.andika.dindinassignment.adapter.FoodListPagerAdapter
import app.andika.dindinassignment.utilities.*
import app.andika.dindinassignment.viewmodel.MainViewModel
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.layout_item_list_pager.*

class BoughtItemFragment : BaseMvRxFragment() {
    private val TAG = BoughtItemFragment::class.java.name
    private val mainViewModel: MainViewModel by activityViewModel()

    override fun invalidate() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bought_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        header_layout.visibility = View.GONE

        setViewPager()
    }

    fun setViewPager() {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        val viewPager = view!!.findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.adapter = BoughtItemListPagerAdapter(fragmentManager, requireActivity())

        // Give the TabLayout the ViewPager
        val tabLayout = view!!.findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }
}