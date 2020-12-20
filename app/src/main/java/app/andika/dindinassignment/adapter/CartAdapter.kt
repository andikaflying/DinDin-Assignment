package app.andika.dindinassignment.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.andika.dindinassignment.R
import app.andika.dindinassignment.model.BoughtItem
import app.andika.dindinassignment.model.Food
import app.andika.dindinassignment.utilities.CART
import app.andika.dindinassignment.utilities.SharedPreferencesManager
import app.andika.dindinassignment.utilities.USD
import com.bumptech.glide.Glide

class CartAdapter(private val cartAdapterListener: CartAdapterListener, private val activity: Activity) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private val carts = mutableListOf<BoughtItem>()
    private val TAG = CartAdapter::class.java.name

    fun setCarts(boughtItems: List<BoughtItem>) {
        this.carts.clear()
        this.carts.addAll(boughtItems)
        notifyDataSetChanged()
    }

    fun removeCart(position : Int) {
        this.carts.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivCart: ImageView = itemView.findViewById(R.id.ivCart)
        var tvOrder: TextView = itemView.findViewById(R.id.tvOrder)
        var ibClose: ImageButton = itemView.findViewById(R.id.ibClose)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = carts.size

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val cart = carts[position]

        Glide.with(holder.itemView)
                .load(cart.foodImageURL)
                .into(holder.ivCart)

        holder.tvOrder.setText(cart.foodName)
        holder.tvPrice.setText(cart.foodPrice.toString() + USD)
        holder.ibClose.setOnClickListener {
            cartAdapterListener.removeFromCart(cart.id)
            this.removeCart(position)
        }
    }

    interface CartAdapterListener {
        fun removeFromCart(cartId: Long)
    }
}