package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dot.prime.presentation.common.removeZeros
import com.dot.prime.presentation.common.color_class.ItemProductColors
import com.example.testprime.R
import com.example.testprime.databinding.PopularProductItemBinding
import com.example.testprime.domain.loyalty.model.PopularProduct

class PopularProductAdapter(
    private val clickListener: (PopularProduct) -> Unit,
    private val changeBasketListener: (PopularProduct, Int) -> Unit,
    private val itemProductColors: ItemProductColors
): ListAdapter<PopularProduct, PopularProductAdapter.PopularProductViewHolder>(
    PopularProductDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_product_item, parent, false)
        return PopularProductViewHolder(
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

    override fun onBindViewHolder(holder: PopularProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PopularProductViewHolder(
        itemView: View, clickAtPosition: (Int) -> Unit, changeBasket: (Int, Int) -> Unit
    ): RecyclerView.ViewHolder(itemView){
        val binding = PopularProductItemBinding.bind(itemView)

        fun bind(product: PopularProduct){

            binding.textViewTitle.setTextColor(Color.parseColor(itemProductColors.colorItemText))
            binding.textViewPrice.setTextColor(Color.parseColor(itemProductColors.colorItemText))
            binding.textViewCalories.setTextColor(Color.parseColor(itemProductColors.colorItemSecondText))
            binding.textViewWeight.setTextColor(Color.parseColor(itemProductColors.colorItemSecondText))

            try{
                if (product.images?.get(0)?.link.isNullOrEmpty()) throw Exception()
                binding.imageViewProduct.load(product.images?.get(0)?.link){
                    crossfade(true)
                    placeholder(R.drawable.placeholder_product)
                }
            }catch (exception: Exception){
                binding.imageViewProduct.setImageResource(R.drawable.placeholder_product)
            }
            binding.textViewTitle.text = product.name
            binding.textViewCalories.text = String.format(itemView.context.getString(R.string.product_calories), product.options?.energy?.removeZeros() ?: "0")
            binding.textViewWeight.text = String.format(itemView.context.getString(R.string.product_weight), product.options?.weight?.removeZeros() ?: "0")
            binding.textViewPrice.text = String.format(itemView.context.getString(R.string.product_price), product.price?.removeZeros() ?: "0")

            // MVP
            binding.layoutPrice.visibility = View.INVISIBLE
        }

        init {
            itemView.setOnClickListener {
                clickAtPosition(bindingAdapterPosition)
            }
        }
    }

}

class PopularProductDiffCallBack: DiffUtil.ItemCallback<PopularProduct>(){

    override fun areItemsTheSame(oldItem: PopularProduct, newItem: PopularProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PopularProduct, newItem: PopularProduct): Boolean {
        return oldItem == newItem
    }

}