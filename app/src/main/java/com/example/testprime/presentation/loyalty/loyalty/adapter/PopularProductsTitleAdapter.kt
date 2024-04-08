package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dot.prime.R
import com.example.testprime.databinding.LoyaltyPopularProductsTitleItemBinding

class PopularProductsTitleAdapter(
    private val colorText: String
): RecyclerView.Adapter<PopularProductsTitleAdapter.PopularProductsTitleViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularProductsTitleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loyalty_popular_products_title_item, parent, false)
        return PopularProductsTitleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularProductsTitleViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class PopularProductsTitleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = LoyaltyPopularProductsTitleItemBinding.bind(itemView)

        fun bind(){
            try{
                binding.layout.visibility = View.VISIBLE
                binding.textViewTitle.setTextColor(Color.parseColor(colorText))
            }catch (exception: Exception){
                binding.layout.visibility = View.GONE
            }
        }

    }

}