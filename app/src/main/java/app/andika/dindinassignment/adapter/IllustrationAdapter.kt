package app.andika.dindinassignment.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import app.andika.dindinassignment.R
import com.bumptech.glide.Glide

class IllustrationAdapter(
        private var context: Context,
        private var imageFileName: Array<String>
) : PagerAdapter() {

    override fun getCount(): Int {
        return imageFileName.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        val resImage = context.resources.getIdentifier(imageFileName[position], "drawable", context.packageName)

        imageView.setBackground(context.resources.getDrawable(resImage, null))

        container.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}