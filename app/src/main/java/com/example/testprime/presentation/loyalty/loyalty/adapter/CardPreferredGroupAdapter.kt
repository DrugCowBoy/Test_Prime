package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dot.prime.R
import com.dot.prime.common.enum_classes.PreferredGroupsStatus
import com.dot.prime.presentation.common.helper_class.LoyaltyInfo
import com.dot.prime.domain.preferred_groups.model.InfoPreferredGroups
import com.dot.prime.domain.preferred_groups.model.StatusPreferredGroups
import com.dot.prime.presentation.common.dpToPx
import com.dot.prime.presentation.common.getMonthGroup
import com.example.testprime.databinding.LoyaltyPreferredGroupsItemBinding

class CardPreferredGroupAdapter(
    private val colorText: String,
    private val clickPreferredGroups: (StatusPreferredGroups?, InfoPreferredGroups?) -> Unit
): ListAdapter<LoyaltyInfo, CardPreferredGroupAdapter.CardPreferredGroupViewHolder>(
    CardPreferredGroupDiffCallBack()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardPreferredGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loyalty_preferred_groups_item, parent, false)
        return CardPreferredGroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardPreferredGroupViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardPreferredGroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = LoyaltyPreferredGroupsItemBinding.bind(itemView)

        fun bind(info: LoyaltyInfo){
            try{
                binding.cardPreferredGroups.visibility = View.VISIBLE

                binding.textTitlePreferredGroups.setTextColor(Color.parseColor(colorText))
                binding.textDescPreferredGroups.setTextColor(Color.parseColor(colorText))

                val status = info.statusPreferredGroups?.status ?: PreferredGroupsStatus.SelectGroupsNothing
                val currentMonthString = info.infoPreferredGroups?.currentMonth
                    ?.getMonthGroup(
                        context = itemView.context,
                        defaultMonth = itemView.context.getString(R.string.group_january)
                    )
                binding.textTitlePreferredGroups.text = String.format(
                    itemView.context.getString(R.string.favorite_dishes_title),
                    currentMonthString
                )

                when(status){
                    PreferredGroupsStatus.SelectGroupsCurrentMonth ->{
                        binding.textDescPreferredGroups.text = itemView.context.getString(R.string.favorite_dishes_desc_status_1)
                        binding.textViewToFavPreferredGroups.text = itemView.context.getString(R.string.fav_btn_select_new)
                        binding.textViewToFavPreferredGroups.setTextColor(Color.WHITE)
                        binding.btnToPreferredGroups.strokeWidth = 1.dpToPx(itemView.context)
                        binding.btnToPreferredGroups.strokeColor = Color.WHITE
                        binding.btnToPreferredGroups.setCardBackgroundColor(Color.TRANSPARENT)
                    }
                    PreferredGroupsStatus.SelectGroupsNextMonth ->{
                        binding.textDescPreferredGroups.text = itemView.context.getString(R.string.favorite_dishes_desc_status_2)
                        binding.textViewToFavPreferredGroups.text = itemView.context.getString(R.string.fav_btn_select)
                        binding.textViewToFavPreferredGroups.setTextColor(Color.WHITE)
                        binding.btnToPreferredGroups.strokeWidth = 1.dpToPx(itemView.context)
                        binding.btnToPreferredGroups.strokeColor = Color.WHITE
                        binding.btnToPreferredGroups.setCardBackgroundColor(Color.TRANSPARENT)
                    }
                    PreferredGroupsStatus.SelectGroupsNothing ->{
                        binding.textDescPreferredGroups.text = itemView.context.getString(R.string.favorite_dishes_desc_status_3)
                        binding.textViewToFavPreferredGroups.text = itemView.context.getString(R.string.fav_btn_look)
                        binding.textViewToFavPreferredGroups.setTextColor(Color.BLACK)
                        binding.btnToPreferredGroups.strokeWidth = 0
                        binding.btnToPreferredGroups.setCardBackgroundColor(Color.WHITE)
                    }
                }

                binding.btnToPreferredGroups.setOnClickListener {
                    clickPreferredGroups(info.statusPreferredGroups, info.infoPreferredGroups)
                }

            }catch (exception: Exception){
                binding.cardPreferredGroups.visibility = View.GONE
            }

        }

    }

}

class CardPreferredGroupDiffCallBack: DiffUtil.ItemCallback<LoyaltyInfo>(){

    override fun areItemsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return (oldItem.statusPreferredGroups == newItem.statusPreferredGroups) && (oldItem.infoPreferredGroups == newItem.infoPreferredGroups)
    }

}