package com.example.testprime.presentation.basket.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dot.prime.presentation.common.removeZeros
import com.example.testprime.presentation.common.color_class.ItemProductColors
import com.example.testprime.R
import com.example.testprime.databinding.BasketItemBinding
import com.example.testprime.domain.basket.model.ItemBasket

class BasketProductAdapter (
    private val clickListener: (ItemBasket) -> Unit,
    private val changeBasketListener: (ItemBasket, Int) -> Unit,
    private val deleteItem: (ItemBasket) -> Unit,
    private val itemProductColors: ItemProductColors
) : ListAdapter<ItemBasket, BasketProductAdapter.BasketProductViewHolder>(
    BasketProductDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        return BasketProductViewHolder(
            view,
            clickAtPosition = { position ->
                getItem(position)?.let { product ->
                    clickListener(product)
                }
            },
            changeBasket = { position, quantity ->
                getItem(position)?.let { product ->
                    changeBasketListener(product, quantity)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: BasketProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BasketProductViewHolder(
        itemView: View, clickAtPosition: (Int) -> Unit, changeBasket: (Int, Int) -> Unit
    ): RecyclerView.ViewHolder(itemView){
        val binding = BasketItemBinding.bind(itemView)

        fun bind(basketProduct: ItemBasket){
            binding.textViewTitle.setTextColor(Color.parseColor(itemProductColors.colorItemText))
            binding.textViewPrice.setTextColor(Color.parseColor(itemProductColors.colorItemText))
            binding.textViewCount.setTextColor(Color.parseColor(itemProductColors.colorItemText))
            binding.textMinus.setTextColor(Color.parseColor(itemProductColors.colorItemText))
            binding.textPlus.setTextColor(Color.parseColor(itemProductColors.colorItemText))
            binding.textViewCaloriesWeight.setTextColor(Color.parseColor(itemProductColors.colorItemSecondText))
            binding.minus.setColorFilter(Color.parseColor(itemProductColors.colorItemMinus))
            binding.plus.setColorFilter(Color.parseColor(itemProductColors.colorItemPlus))

            try{
                if (basketProduct.product?.images?.get(0)?.link.isNullOrEmpty()) throw Exception()
                binding.imageViewProduct.load(basketProduct.product?.images?.get(0)?.link){
                    crossfade(true)
                    placeholder(R.drawable.placeholder_product)
                }
            }catch (exception: Exception){
                binding.imageViewProduct.setImageResource(R.drawable.placeholder_product)
            }
            binding.textViewTitle.text = basketProduct.product?.name
            binding.textViewCaloriesWeight.text = String.format(
                itemView.context.getString(R.string.basket_item_calories_weight),
                basketProduct.product?.options?.energyPerHundredGrams?.removeZeros() ?: "0",
                basketProduct.product?.options?.weight?.removeZeros() ?: "0"
            )
            binding.textViewCount.text = basketProduct.quantity.toString()
            binding.textViewPrice.text = String.format(itemView.context.getString(R.string.product_price), basketProduct.product?.price?.removeZeros() ?: "0")
        }

        init {
            itemView.setOnClickListener {
                clickAtPosition(bindingAdapterPosition)
            }
            binding.plus.setOnClickListener {
                val position = bindingAdapterPosition
                val currentQuantity = getItem(position).quantity ?: 0
                if (currentQuantity != 99){
                    changeBasket(position, currentQuantity + 1)
                }
            }
            binding.minus.setOnClickListener {
                val position = bindingAdapterPosition
                val currentQuantity = getItem(position).quantity ?: 0
                if (currentQuantity != 0){
                    changeBasket(position, currentQuantity - 1)
                }
            }
        }
    }

    fun deleteItem(adapterPosition: Int){
        deleteItem(getItem(adapterPosition))
    }

}

class BasketProductDiffCallBack: DiffUtil.ItemCallback<ItemBasket>(){

    override fun areItemsTheSame(oldItem: ItemBasket, newItem: ItemBasket): Boolean {
        return oldItem.product?.id == newItem.product?.id
    }

    override fun areContentsTheSame(oldItem: ItemBasket, newItem: ItemBasket): Boolean {
        return oldItem == newItem
    }

}