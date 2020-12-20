package app.andika.dindinassignment.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.andika.dindinassignment.R
import app.andika.dindinassignment.adapter.FoodAdapter
import app.andika.dindinassignment.model.Food
import app.andika.dindinassignment.utilities.DRINKS
import app.andika.dindinassignment.utilities.PIZZA
import app.andika.dindinassignment.utilities.SUSHI
import app.andika.dindinassignment.viewmodel.MainViewModel
import com.airbnb.mvrx.*
import kotlinx.android.synthetic.main.fragment_food.*

class FoodFragment : BaseMvRxFragment() {
    private val TAG = FoodFragment::class.java.name
    private var mPage = 0
    private lateinit var foodAdapter: FoodAdapter
    private val mainViewModel: MainViewModel by activityViewModel()
    private lateinit var foodType: String

    override fun invalidate() {
        if (foodAdapter.itemCount == 0) {
            withState(mainViewModel) { state ->
                when (state.foodList) {
                    is Success -> {
                        val foodList = state.foodList.invoke().filter { it.type == foodType }
                        showFoods(foodList)
                    }
                    Uninitialized -> {

                    }
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = arguments!!.getInt(ARG_PAGE)
        setFoodType(mPage)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_food, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodAdapter = FoodAdapter(object : FoodAdapter.FoodAdapterListener {
            override fun addToChart(foodId: Long) {
                mainViewModel.addFoodToChart(foodId)
            }
        }, requireActivity())

        rvFood.adapter = foodAdapter

        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun showFoods(foodList: List<Food>) {
        foodAdapter.setFoods(foodList);
    }

    private fun setFoodType(page: Int) {
        when (page) {
            1 -> foodType = PIZZA
            2 -> foodType = SUSHI
            3 -> foodType = DRINKS
        }
    }

    companion object {
        const val ARG_PAGE = "ARG_PAGE"
        fun newInstance(page: Int): FoodFragment {

            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            val fragment = FoodFragment()
            fragment.arguments = args
            return fragment
        }
    }
}