package app.andika.dindinassignment.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.andika.dindinassignment.core.FoodApplication
import app.andika.dindinassignment.model.BoughtItem
import app.andika.dindinassignment.repository.MainRepository
import app.andika.dindinassignment.state.MainState
import app.andika.dindinassignment.utilities.CART
import app.andika.dindinassignment.utilities.SharedPreferencesManager
import com.airbnb.mvrx.*

class MainViewModel (initialState: MainState, private val mainRepository: MainRepository) :
        BaseMvRxViewModel<MainState>(initialState, debugMode = true) {
    private val TAG = MainViewModel::class.java.name
    val errorMessage = MutableLiveData<String>()
    val cartSize = MutableLiveData<Int>()
    val totalPrice = MutableLiveData<Int>()

    init {
        setState {
            copy(foodList = Loading())
        }
        mainRepository.getFoodList()
            .execute {
                copy(foodList = it)
            }

        cartSize.value = 0
        totalPrice.value = 0
    }


    fun addFoodToChart(foodId: Long) {
        withState { state ->
            mainRepository.addFoodToCart(foodId).execute {
                if (it is Success) {
                    val order = it.invoke()

                    val newOrderList = orderList.toMutableList().apply {
                        add(order)
                    }

                    val amount = newOrderList.count { it.isDeleted == false }
                    cartSize.postValue(amount)
                    Log.e(TAG, "Cart size amount = " + amount)
                    Log.e(TAG, "saveCart - cart size = " + newOrderList.size)

                    copy(orderList = newOrderList)
                } else if (it is Fail) {
                    errorMessage.postValue("Failed to add movie to watchlist")
                    copy()
                } else {
                    copy()
                }
            }
        }
    }

    fun removeFoodFromCart(cartId: Long) {
        withState { state ->
                val index = state.orderList.indexOfFirst {
                    it.id == cartId
                }

                mainRepository.removeFoodFromCart(index, cartId).execute {
                    if (it is Success) {
                        Log.e(TAG, "removeFoodFromCart success")
                        Log.e(TAG, "Is deleted = " + it.invoke().isDeleted)
                        val newOrderList = orderList.toMutableList().apply {
                            set(index, it.invoke())
                        }
                        calculateTotalPrice(newOrderList.toMutableList())

                        copy(orderList = newOrderList)
                    } else if (it is Fail) {
                        errorMessage.postValue("Failed to add movie to watchlist")
                        copy()
                    } else {
                        copy()
                    }
                }
        }
    }

    fun saveCart(activity: Activity) {
        withState {
            Log.e(TAG, "Shared Preferences - save list size = " + it.orderList.size)
            SharedPreferencesManager.saveArrayList(it.orderList, CART, activity)
        }
    }

    //        setState {
//            copy(orderList = mutableListOf())
//        }

    //        withState {
//            it -> Log.e(TAG, "retrieveCart - state size = " + it.orderList.size)
//            mainRepository.getBoughtItemList(activity).execute {
//                val result = it.invoke()
//                if (result != null) {
//
//                    calculateTotalPrice(result)
//                    val amount = result.count { it.isDeleted == false }
//                    Log.e(TAG, "retrieveCart - Amount = " + amount)
//                    cartSize.postValue(amount)
//                    copy(orderList = result)
//                } else {
//                    Log.e(TAG, "retrieveCart - pusing masuk else !!!!")
//                    copy(orderList = mutableListOf())
//                }
//            }
//        }
    fun retrieveCart(activity: Activity) {
        if (SharedPreferencesManager.isExist(CART, activity)) {
            Log.e(TAG, "Shared Preferences - get list = true")
            var list = SharedPreferencesManager.getArrayList(CART, activity)!!
            calculateTotalPrice(list)
            val amount = list.count { it.isDeleted == false }
            Log.e(TAG, "Amount = " + amount)

            cartSize.postValue(amount)

            setState {
                copy(orderList = list)
            }
        } else {
            Log.e(TAG, "Shared Preferences - get list = false")
        }
    }

    fun calculateTotalPrice(boughtItemList : List<BoughtItem>) {
        var total: Int = 0
        Log.e(TAG, "Total Item = " + boughtItemList.size)
        for (item : BoughtItem in boughtItemList) {
            if (!item.isDeleted) {
                total = item.foodPrice * item.total + total
            }
        }

        Log.e(TAG, "Total Price = " + total)
        totalPrice.postValue(total)
    }

    companion object : MvRxViewModelFactory<MainViewModel, MainState> {
        override fun create(viewModelContext: ViewModelContext, state: MainState): MainViewModel? {
            val mainRepository = viewModelContext.app<FoodApplication>().mainRepository
            return MainViewModel(state, mainRepository)
        }
    }

}
