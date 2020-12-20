package app.andika.dindinassignment.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import app.andika.dindinassignment.R
import app.andika.dindinassignment.adapter.CartAdapter
import app.andika.dindinassignment.adapter.FoodAdapter
import app.andika.dindinassignment.utilities.CART
import app.andika.dindinassignment.utilities.SharedPreferencesManager
import app.andika.dindinassignment.utilities.USD
import app.andika.dindinassignment.viewmodel.MainViewModel
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.fragment_item_cart.*

class CartFragment : BaseMvRxFragment() {
    private val TAG = CartFragment::class.java.name
    private lateinit var cartAdapter: CartAdapter
    private val mainViewModel: MainViewModel by activityViewModel()

    override fun invalidate() {
        withState(mainViewModel) { state ->
            if (state.orderList != null) {
                cartAdapter.setCarts(state.orderList.filter { it.isDeleted == false })
                mainViewModel.saveCart(requireActivity())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartAdapter = CartAdapter(object : CartAdapter.CartAdapterListener {
            override fun removeFromCart(cartId: Long) {
                mainViewModel.removeFoodFromCart(cartId)
            }
        }, requireActivity())

        rvBoughtItem.adapter = cartAdapter
        mainViewModel.retrieveCart(requireActivity())

        mainViewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it != 0) {
                    tvTotal.setText(it.toString() + USD)
                } else {
                    tvTotal.setText(0.toString() + USD)
                }
            }
        })
    }
}