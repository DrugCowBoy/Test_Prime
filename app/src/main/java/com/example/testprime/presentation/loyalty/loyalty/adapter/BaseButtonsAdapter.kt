package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dot.prime.R
import com.dot.prime.presentation.common.helper_class.LoyaltyInfo
import com.dot.prime.presentation.common.roundToInt
import com.example.testprime.databinding.LoyaltyBaseButtonsItemBinding

class BaseButtonsAdapter(
    private val colorSecondText: String,
    private val clickButtonEstimate: () -> Unit,
    private val clickButtonPersonalDiscount: () -> Unit
): ListAdapter<LoyaltyInfo, BaseButtonsAdapter.BaseButtonsViewHolder>(BaseButtonsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseButtonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loyalty_base_buttons_item, parent, false)
        return BaseButtonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseButtonsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BaseButtonsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = LoyaltyBaseButtonsItemBinding.bind(itemView)

        fun bind(info: LoyaltyInfo){
            try{
                binding.layoutBaseButtons.visibility = View.VISIBLE

                binding.textViewCoinsDesc.setTextColor(Color.parseColor(colorSecondText))
                binding.textViewCoins.setTextColor(Color.parseColor(colorSecondText))
                binding.textViewRateDesc.setTextColor(Color.parseColor(colorSecondText))
                binding.textViewRate.setTextColor(Color.parseColor(colorSecondText))
                binding.textViewPersonalSalesDesc.setTextColor(Color.parseColor(colorSecondText))
                binding.textViewPersonalSales.setTextColor(Color.parseColor(colorSecondText))

                binding.textViewCoins.text = info.userData?.balance?.roundToInt() ?: "0"
                binding.textViewRate.text = info.countEstimateProducts.toString()
                binding.textViewPersonalSales.text = info.personalDiscounts?.size.toString()

            }catch (exception: Exception){
                binding.layoutBaseButtons.visibility = View.GONE
            }
        }

        init {
            binding.buttonRate.setOnClickListener {
                if (binding.textViewRate.text != "0"){
                    clickButtonEstimate()
                }
            }
            binding.buttonPersonalSales.setOnClickListener {
                clickButtonPersonalDiscount()
            }
        }

    }
}

class BaseButtonsDiffCallBack: DiffUtil.ItemCallback<LoyaltyInfo>(){

    override fun areItemsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return (oldItem.userData?.balance == newItem.userData?.balance) && (oldItem.countEstimateProducts == newItem.countEstimateProducts)
    }

}