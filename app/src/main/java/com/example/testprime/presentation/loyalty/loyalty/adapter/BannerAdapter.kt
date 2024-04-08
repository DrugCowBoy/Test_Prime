package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dot.prime.R
import com.dot.prime.databinding.BannerItemBinding
import com.dot.prime.presentation.common.dpToPx
import com.dot.prime.presentation.common.margin
import com.example.testprime.domain.loyalty.model.Banner

class BannerAdapter(
    private val listBanner: List<Banner>,
    private val widthScreen: Int
): RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(banner = listBanner[position])
    }

    override fun getItemCount(): Int {
        return listBanner.size
    }

    inner class BannerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = BannerItemBinding.bind(itemView)

        fun bind(banner: Banner){
            val bannerWidth = widthScreen - (BANNER_EXTERNAL_MARGIN * 2).dpToPx(itemView.context)
            // Change width image
            val layoutParams = binding.imageViewBanner.layoutParams
            layoutParams.width = bannerWidth
            binding.imageViewBanner.layoutParams = layoutParams
            binding.imageViewBanner.requestLayout()
            // Set margins for banner
            binding.card.margin(
                left = BANNER_INNER_MARGIN,
                right = BANNER_INNER_MARGIN,
                context = itemView.context
            )
            // Set image
            try{
                binding.imageViewBanner.load(banner.url){
                    crossfade(true)
                    placeholder(R.drawable.banner_holder)
                }
            }catch (exception: Exception){
                binding.imageViewBanner.setImageResource(R.drawable.banner_holder)
            }
            // Card in center
            val footer =
                (widthScreen - (bannerWidth + (BANNER_INNER_MARGIN * 2).dpToPx(itemView.context))) / 2
            when(bindingAdapterPosition){
                0 ->{
                    binding.cardFooterStart.minimumWidth = footer
                    binding.cardFooterEnd.minimumWidth = 0
                }
                (itemCount - 1) ->{
                    binding.cardFooterStart.minimumWidth = 0
                    binding.cardFooterEnd.minimumWidth = footer
                }
                else ->{
                    binding.cardFooterStart.minimumWidth = 0
                    binding.cardFooterEnd.minimumWidth = 0
                }
            }
        }
    }

    companion object{
        const val BANNER_INNER_MARGIN = 3
        const val BANNER_EXTERNAL_MARGIN = 12
    }

}