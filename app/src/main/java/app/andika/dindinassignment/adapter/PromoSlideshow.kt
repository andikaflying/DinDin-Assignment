@file:Suppress("DEPRECATION")

package app.andika.dindinassignment.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.util.*
import android.os.Handler
import androidx.viewpager.widget.PagerAdapter
import app.andika.dindinassignment.utilities.SLIDER_DURATION
import app.andika.dindinassignment.utilities.SLIDER_START_DELAY

class PromoSlideshow : AppCompatActivity() {
    // Set first value for page of auto slide
    private var pagePosition = 0
    private var arraySize = 0

    // Set Handler for change illustration page
    private var handler = Handler()
    private var slide: Runnable? = null

    // Set timer for auto slide illustration
    private var swipeTimer = Timer()

    fun promoViewPager(imagePromoName: Array<String>, viewPager: ViewPager, tabLayout: TabLayout, context: Context){
        // Set trigger for auto slide illustration
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                slide?.let { handler.post(it) }
            }
        }, SLIDER_START_DELAY, SLIDER_DURATION)

        runSlider(imagePromoName, viewPager, tabLayout, context)
    }

    private fun runSlider(imagePromoName: Array<String>, viewPager: ViewPager, tabLayout: TabLayout, context: Context) {
        val adapter: PagerAdapter = IllustrationAdapter(context, imagePromoName)

        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager, true)

        arraySize = imagePromoName!!.size
        slide = Runnable {
            if (pagePosition == arraySize) {
                pagePosition = 0
            }
            viewPager.setCurrentItem(pagePosition++, true)
        }
    }

    override fun onDestroy() {
        slide?.let { handler.removeCallbacks(it) }
        swipeTimer.cancel()
        super.onDestroy()
    }
}
