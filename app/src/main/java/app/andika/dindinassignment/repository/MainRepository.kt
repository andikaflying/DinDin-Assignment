package app.andika.dindinassignment.repository

import android.app.Activity
import android.util.Log
import app.andika.dindinassignment.adapter.FoodAdapter
import app.andika.dindinassignment.model.BoughtItem
import app.andika.dindinassignment.model.Food
import app.andika.dindinassignment.utilities.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainRepository {
    private val TAG = MainRepository::class.java.name
    private val foodList = mutableListOf<Food>()
    private var itemBoughtList = mutableListOf<BoughtItem>()

    fun getFoodList() = Observable.fromCallable<List<Food>> {
//        Thread.sleep(1000)
        if (foodList.size == 0) {
            foodList.addAll(listOf(
                    Food(
                            1,
                            "Italian Pizza",
                            "Meat, Swiss-Beef Mushroom, Tuna",
                            "https://www.midnightblueelephant.com/wp-content/uploads/2020/03/aurelien-lemasson-theobald-x00CzBt4Dfk-unsplash.jpg",
                            "500 gr, 55 cm",
                            40,
                            PIZZA
                    ),
                    Food(
                            2,
                            "Hawaiian Pizza",
                            "Meat, Salad, Tuna",
                            "https://www.jessicagavin.com/wp-content/uploads/2020/07/hawaiian-pizza-16.jpg",
                            "300 gr, 55 cm",
                            44,
                            PIZZA
                    ),
                    Food(
                            3,
                            "Marzano Pizza",
                            "Meat, cheese, cream, spinach",
                            "https://assets-pergikuliner.com/fTngtdgCOsdDxVmAI57FMO3BNFk=/385x290/smart/https://assets-pergikuliner.com/uploads/image/picture/129316/picture-1449338337.jpg",
                            "200 gr, 25 cm",
                            38,
                            PIZZA
                    ),
                    Food(
                            4,
                            "Takoyaki Pizza",
                            "Takoyaki, Meat",
                            "https://d20aeo683mqd6t.cloudfront.net/articles/title_images/000/038/899/original/pixta_31668439_M.jpg",
                            "500 gr, 75 cm",
                            35,
                            PIZZA
                    ),
                    Food(
                            5,
                            "Cheese Tuna Rolls",
                            "Cheese Tuna Rolls",
                            "https://www.kikkoman.com/homecook/search/recipe/img/00000799.jpg",
                            "200 gr, 30 cm",
                            32,
                            SUSHI
                    ),
                    Food(
                            6,
                            "Tuna Rolls",
                            "Tuna Rolls",
                            "https://www.thespruceeats.com/thmb/YWotj0CB3Vd40WyYhMvl_MvJuIs=/3595x2696/smart/filters:no_upscale()/tekkamaki-suchi-roll-recipe-2031512-hero-01-79731084409e4a54beb3a494afef1308.jpg",
                            "200 gr, 20 cm",
                            36,
                            SUSHI
                    ),
                    Food(
                            7,
                            "Coca Cola",
                            "Coca Cola",
                            "https://cdn.productreview.com.au/resize/listing-picture/be9517eb-a8c2-398a-a065-b14b8b85c02a?width=170&height=170&dpr=2&v=2",
                            "80 gr, 5 cm",
                            11,
                            DRINKS
                    ),
                    Food(
                            8,
                            "Sprite",
                            "Sprite",
                            "https://images-na.ssl-images-amazon.com/images/I/61Vx5c69QyL._SX425_.jpg",
                            "50 gr, 5 cm",
                            10,
                            DRINKS
                    )
            ))
        }

        foodList
    }.subscribeOn(Schedulers.io())

    fun addFoodToCart(foodId: Long): Observable<BoughtItem> {
        return Observable.fromCallable {
            val food = foodList.first { food -> food.id == foodId }
            val id = ID_GENERATOR.getAndIncrement().toLong()

            val boughtItem = BoughtItem(id, food.id, food.name, food.imageURL, food.price, 1, CART, false)

            itemBoughtList.apply {
                add(boughtItem)
            }
            boughtItem
        }.subscribeOn(Schedulers.io())
    }

    fun removeFoodFromCart(index: Int, cartId: Long) : Observable<BoughtItem> {
        return Observable.fromCallable<BoughtItem> {
            val boughtItem = itemBoughtList.first { item -> item.id == cartId }
            boughtItem.copy(isDeleted = true)
        }.subscribeOn(Schedulers.io())
    }
}