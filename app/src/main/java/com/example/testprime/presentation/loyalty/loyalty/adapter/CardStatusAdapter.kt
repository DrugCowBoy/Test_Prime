package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dot.prime.R
import com.dot.prime.databinding.LoyaltyStatusItemBinding
import com.dot.prime.presentation.common.helper_class.LoyaltyInfo
import com.dot.prime.presentation.common.makeBoldSymbols
import com.example.testprime.databinding.LoyaltyStatusItemBinding

class CardStatusAdapter(val onClick: () -> Unit) :
    ListAdapter<LoyaltyInfo, CardStatusAdapter.CardStatusViewHolder>(CardStatusDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStatusViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.loyalty_status_item, parent, false)
        return CardStatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardStatusViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = LoyaltyStatusItemBinding.bind(itemView)

        fun bind(info: LoyaltyInfo) {
            try {
                binding.root.setOnClickListener {
                    onClick.invoke()
                }
                binding.cardStatus.visibility = View.VISIBLE

                val colorCard = info.userData?.colorStatus ?: "#F7C8C9"
                val currentStatus = info.userData?.userStatus ?: "Жемчужный"
                val nextStatus = info.userData?.nextUserStatus ?: "Серебряный"
                val progressBarStart = info.userData?.rangeSum?.get(0) ?: 0
                val progressBarValue = info.userData?.userSumStatus?.toFloat()?.toInt() ?: 0

                binding.progressBar.progressTintList =
                    ColorStateList.valueOf(Color.parseColor(colorCard))
                binding.imageViewStar.setColorFilter(Color.parseColor(colorCard))

                binding.textViewStatusTitle.text = String.format(
                    itemView.context.getString(R.string.current_status),
                    currentStatus
                )
                binding.textViewStatusInfo.text = itemView.context.getString(R.string.status_info)
                val textNextStatus = nextStatus.dropLast(2)

                if (info.userData?.rangeSum?.size == 1) {
                    binding.textViewNextStatus.text = ""
                    binding.progressBar.max = progressBarStart
                    binding.progressBar.progress = progressBarStart
                } else {
                    val progressBarEnd = info.userData?.rangeSum?.get(1) ?: 4999
                    val left = progressBarEnd - progressBarValue
                    binding.textViewNextStatus.text = String.format(
                        itemView.context.getString(R.string.status_left),
                        textNextStatus,
                        left
                    ).makeBoldSymbols(context = itemView.context, regex = "(.+)(?=\\s+осталось)")

                    binding.progressBar.max = progressBarEnd - progressBarStart
                    binding.progressBar.progress = progressBarValue - progressBarStart
                }

            } catch (exception: Exception) {
                binding.cardStatus.visibility = View.GONE
            }
        }
    }

}

class CardStatusDiffCallBack : DiffUtil.ItemCallback<LoyaltyInfo>() {

    override fun areItemsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return oldItem.userData?.userSumStatus == newItem.userData?.userSumStatus
    }

}