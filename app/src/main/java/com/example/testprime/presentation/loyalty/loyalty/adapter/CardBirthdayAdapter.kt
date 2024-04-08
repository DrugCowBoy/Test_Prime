package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dot.prime.R
import com.dot.prime.common.enum_classes.BirthDayStatus
import com.dot.prime.domain.loyalty.model.InfoBirthDay
import com.dot.prime.presentation.common.helper_class.LoyaltyInfo
import com.dot.prime.presentation.common.makeBoldSymbols
import com.example.testprime.databinding.LoyaltyBirthdayItemBinding
import com.example.testprime.domain.loyalty.model.InfoBirthDay

class CardBirthdayAdapter(
    private val colorText: String,
    private val clickBirthDaySoon: (InfoBirthDay?) -> Unit,
    private val clickBirthDayCanActivate: (InfoBirthDay?) -> Unit,
    private val clickBirthDayActive: (InfoBirthDay?) -> Unit,
    private val clickBirthDayActivate: () -> Unit,
): ListAdapter<LoyaltyInfo, CardBirthdayAdapter.CardBirthdayViewHolder>(CardBirthdayDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBirthdayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loyalty_birthday_item, parent, false)
        return CardBirthdayViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardBirthdayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardBirthdayViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = LoyaltyBirthdayItemBinding.bind(itemView)

        fun bind(info: LoyaltyInfo){
            try{
                binding.cardDateBirth.visibility = View.VISIBLE

                binding.textViewDateBirthTitle.setTextColor(Color.parseColor(colorText))
                binding.textViewDateBirthDesc.setTextColor(Color.parseColor(colorText))
                binding.textViewActivate.setTextColor(Color.WHITE)

                when(info.userData?.birthDaySalesStatus){
                    BirthDayStatus.Soon.status -> birthDaySoon(infoBirthDay = info.infoBirthDay)
                    BirthDayStatus.CanActivate.status -> birthDayCanActivate(infoBirthDay = info.infoBirthDay)
                    BirthDayStatus.Active.status -> birthDayActive(infoBirthDay = info.infoBirthDay)
                    else -> birthDayUnavailable()
                }

            }catch (exception: Exception){
                binding.cardDateBirth.visibility = View.GONE
            }
        }

        private fun birthDayUnavailable(){
            binding.cardDateBirth.visibility = View.GONE
        }

        private fun birthDaySoon(infoBirthDay: InfoBirthDay?){
            binding.cardDateBirth.setOnClickListener {
                clickBirthDaySoon(infoBirthDay)
            }

            binding.textViewDateBirthTitle.setTypeface(binding.textViewDateBirthTitle.typeface, Typeface.BOLD)
            binding.textViewDateBirthTitle.text = infoBirthDay?.shortInfo
            binding.textViewDateBirthDesc.visibility = View.GONE
            binding.btnActivate.visibility = View.GONE
        }

        private fun birthDayCanActivate(infoBirthDay: InfoBirthDay?){
            binding.cardDateBirth.setOnClickListener {
                clickBirthDayCanActivate(infoBirthDay)
            }
            binding.btnActivate.setOnClickListener {
                clickBirthDayActivate()
            }

            binding.textViewDateBirthTitle.setTypeface(binding.textViewDateBirthTitle.typeface, Typeface.BOLD)
            binding.textViewDateBirthTitle.text = itemView.context.getString(R.string.birth_day_sale)

            val textInfo = infoBirthDay?.longInfo?.replaceFirst(", ", ",\n")
            binding.textViewDateBirthDesc.text = textInfo?.makeBoldSymbols(context = itemView.context, regex = "(\\d+)%")

            binding.textViewDateBirthDesc.visibility = View.VISIBLE
            binding.btnActivate.visibility = View.VISIBLE
        }

        private fun birthDayActive(infoBirthDay: InfoBirthDay?){
            binding.cardDateBirth.setOnClickListener {
                clickBirthDayActive(infoBirthDay)
            }

            binding.textViewDateBirthTitle.setTypeface(binding.textViewDateBirthTitle.typeface, Typeface.NORMAL)
            binding.textViewDateBirthTitle.text = infoBirthDay?.shortInfo.toString().makeBoldSymbols(context = itemView.context, regex = "активна")
            binding.textViewDateBirthDesc.visibility = View.GONE
            binding.btnActivate.visibility = View.GONE
        }

    }

}

class CardBirthdayDiffCallBack: DiffUtil.ItemCallback<LoyaltyInfo>(){

    override fun areItemsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return (oldItem.userData?.birthDaySalesStatus == newItem.userData?.birthDaySalesStatus) || (oldItem.infoBirthDay == newItem.infoBirthDay)
    }

}