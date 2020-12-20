package app.andika.dindinassignment.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.andika.dindinassignment.R
import com.airbnb.mvrx.BaseMvRxFragment

class InformationFragment : BaseMvRxFragment() {
    private val TAG = CartFragment::class.java.name

    override fun invalidate() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_information, container, false)
    }
}