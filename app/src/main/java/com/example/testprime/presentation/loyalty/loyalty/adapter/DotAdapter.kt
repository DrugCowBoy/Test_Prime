package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dot.prime.R
import com.dot.prime.databinding.DotItemBinding

class DotAdapter(
    private val beginPosition: Int = 0,
    private val size: Int
): RecyclerView.Adapter<DotAdapter.DotViewHolder>() {

    var selectedItemPos = beginPosition
    var lastItemSelectedPos = beginPosition

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dot_item, parent, false)
        return DotViewHolder(view)
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return size
    }

    inner class DotViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = DotItemBinding.bind(itemView)

        fun bind(){
            if(bindingAdapterPosition == selectedItemPos){
                binding.imageViewDot.setImageResource(R.drawable.active_dot)
            }else{
                binding.imageViewDot.setImageResource(R.drawable.not_active_dot)
            }
        }
    }

    fun setDot(position: Int){
        selectedItemPos = position
        if(lastItemSelectedPos == -1)
            lastItemSelectedPos = selectedItemPos
        else {
            notifyItemChanged(lastItemSelectedPos)
            lastItemSelectedPos = selectedItemPos
        }
        notifyItemChanged(selectedItemPos)
    }

}