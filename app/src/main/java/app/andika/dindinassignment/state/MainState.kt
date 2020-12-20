package app.andika.dindinassignment.state

import app.andika.dindinassignment.model.BoughtItem
import app.andika.dindinassignment.model.Food
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class MainState(
    val foodList: Async<List<Food>> = Uninitialized,
    val orderList: List<BoughtItem> = mutableListOf<BoughtItem>()
) : MvRxState