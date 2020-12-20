package app.andika.dindinassignment.adapter

import android.animation.Animator
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.andika.dindinassignment.R
import app.andika.dindinassignment.model.Food
import app.andika.dindinassignment.utilities.RoundedCornersTransformation
import app.andika.dindinassignment.utilities.TextAnimation
import app.andika.dindinassignment.utilities.USD
import com.bumptech.glide.Glide


class FoodAdapter(private val foodAdapterListener: FoodAdapterListener, private val activity: Activity) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private val TAG = FoodAdapter::class.java.name
    private val foods = mutableListOf<Food>()

    fun setFoods(foods: List<Food>) {
        this.foods.clear()
        this.foods.addAll(foods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_food, viewGroup, false)

        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foods[position]

        holder.ivFood.setClipToOutline(true)

        Glide.with(activity)
                .load(food.imageURL)
                .transform(RoundedCornersTransformation(activity.baseContext, 30, 0, RoundedCornersTransformation.CornerType.TOP))
                .into(holder.ivFood)

        holder.tvFoodName.setText(food.name)
        holder.tvDescription.setText(food.description)
        holder.tvIngredient.setText(food.sizeInfo)
        val price = food.price.toString() + USD
        holder.btnAdd.setText(price)

        holder.btnAdd.setOnClickListener {
            TextAnimation.setTextWithSmoothAnimation(holder.btnAdd,"added +1", price)
            foodAdapterListener.addToChart(food.id)
        }
    }

    override fun getItemCount() = foods.size

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFoodName: TextView = itemView.findViewById(R.id.tvFoodName)
        var ivFood: ImageView = itemView.findViewById(R.id.ivFood)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var tvIngredient: TextView = itemView.findViewById(R.id.tvIngredient)
        var btnAdd: Button = itemView.findViewById(R.id.btnAdd)
    }

    interface FoodAdapterListener {

        fun addToChart(foodId: Long)
    }
}