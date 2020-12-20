package app.andika.dindinassignment.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import app.andika.dindinassignment.R
import app.andika.dindinassignment.adapter.FoodListPagerAdapter
import app.andika.dindinassignment.adapter.PromoSlideshow
import app.andika.dindinassignment.view.detail.BoughtItemActivity
import app.andika.dindinassignment.viewmodel.MainViewModel
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.BadgeUtils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_item_list_pager.*


class MainFragment : BaseMvRxFragment() {
    private val TAG = MainFragment::class.java.name
    private val mainViewModel: MainViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.retrieveCart(requireActivity())
        setPromoViewPager()
        setBottomViewPager()
        setCartButton()
    }

    override fun invalidate() {
        withState(mainViewModel) {

        }
    }

    private fun showLoader() {

    }

    private fun showError() {

    }

    fun setPromoViewPager() {
        var imageFileName: Array<String>? = context!!.resources.getStringArray(R.array.promo_images)

        val imageFileList = arrayOf(imageFileName.toString())

        for (imageFile in imageFileList) {
            PromoSlideshow().promoViewPager(
                    imageFileName!!
                    , vpPromo
                    , tlPromo
                    , context!!
            )
        }
    }

    private var mBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>? = null

    fun setBottomViewPager() {
        mBottomSheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(bs_list)

        header_layout.setOnClickListener(View.OnClickListener {
            if (mBottomSheetBehavior!!.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                mBottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                mBottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_HIDDEN)
            }
        })

        mBottomSheetBehavior!!.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        val viewPager = view!!.findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.adapter = FoodListPagerAdapter(fragmentManager, requireActivity())

        // Give the TabLayout the ViewPager
        val tabLayout = view!!.findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }

    fun setCartButton() {
        mainViewModel.cartSize.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it != 0) {
                    tvCart.visibility = View.VISIBLE
                    tvCart.setText(it.toString())
                } else {
                    tvCart.visibility = View.GONE
                }
            } else {
                Log.e(TAG, "Cart size = null")
                tvCart.visibility = View.GONE
            }
        })

        fabCart.setOnClickListener {
            mainViewModel.saveCart(activity!!)
            val intent = Intent(activity!!.applicationContext, BoughtItemActivity::class.java)
            activity!!.startActivity(intent)
            activity!!.finish()
        }
    }

}