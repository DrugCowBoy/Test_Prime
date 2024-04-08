package com.example.testprime.presentation.loyalty.loyalty.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.dot.prime.R
import com.dot.prime.presentation.common.helper_class.LoyaltyInfo
import com.dot.prime.presentation.common.dpToPx
import com.example.testprime.presentation.loyalty.loyalty.adapter.BannerAdapter.Companion.BANNER_EXTERNAL_MARGIN
import com.example.testprime.presentation.loyalty.loyalty.adapter.BannerAdapter.Companion.BANNER_INNER_MARGIN
import com.example.testprime.databinding.LoyaltyBannersAndDotsItemBinding
import java.util.*

class BannersAndDotsAdapter(
    private val timerBanner: Timer?,
    private val widthScreen: Int
): ListAdapter<LoyaltyInfo, BannersAndDotsAdapter.BannersAndDotsViewHolder>(BannerDiffCallBack()) {

    private var bannerPosition = 0// Position banners and dots
    private val periodBanner: Long = 4000// Period timer
    private var isBannersGoForward = true// Forward or back swipe banner
    var createdTask = false// Is created task for timer
    // Adapters
    private var bannerAdapter: BannerAdapter? = null
    private var dotAdapter: DotAdapter? = null
    val snapHelper = LinearSnapHelper()
    // Handler
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersAndDotsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loyalty_banners_and_dots_item, parent, false)
        return BannersAndDotsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersAndDotsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BannersAndDotsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = LoyaltyBannersAndDotsItemBinding.bind(itemView)

        fun bind(info: LoyaltyInfo){
            val bannerWidth = widthScreen - (BANNER_EXTERNAL_MARGIN * 2).dpToPx(itemView.context)
            // Set SnapHelper for stable position in banners
            snapHelper.attachToRecyclerView(binding.recyclerBanners)
            if (bannerAdapter == null) bannerAdapter = BannerAdapter(listBanner = info.banners ?: emptyList(), widthScreen = widthScreen)
            if (dotAdapter == null) dotAdapter = DotAdapter(size = info.banners?.size ?: 0)

            // Banners
            binding.recyclerBanners.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerBanners.adapter = bannerAdapter

            // Dots
            binding.recyclerDots.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerDots.adapter = dotAdapter

            // ScrollListener
            binding.recyclerBanners.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
                val visiblePositionBanner = (binding.recyclerBanners.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                if ((visiblePositionBanner != -1) && (visiblePositionBanner != bannerPosition)){
                    bannerPosition = visiblePositionBanner
                    dotAdapter?.setDot(position = visiblePositionBanner)
                }
            }

            if (!createdTask){
                // Timer task
                timerBanner?.schedule(object : TimerTask(){
                    override fun run() {
                        handler.post {
                            val visiblePositionBanner = (binding.recyclerBanners.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                            //Log.i("Log_prime", "Banner: $visiblePositionBanner, Forward: $isBannersGoForward")
                            if (isBannersGoForward){
                                if ((visiblePositionBanner + 1) >= ((info.banners?.size ?: 1) - 1)) isBannersGoForward = false
                                binding.recyclerBanners.smoothScrollToPosition(visiblePositionBanner + 1)
                            }else{
                                if ((visiblePositionBanner - 1) <= 0) isBannersGoForward = true
                                if (visiblePositionBanner > 0){
                                    binding.recyclerBanners.smoothScrollToPosition(visiblePositionBanner - 1)
                                }
                            }
                        }
                    }
                }, periodBanner, periodBanner)
                createdTask = true
            }else{
                // Restore state banners
                dotAdapter?.setDot(position = bannerPosition)
                val offset = (widthScreen - (bannerWidth + (BANNER_INNER_MARGIN * 2).dpToPx(itemView.context))) / 2
                (binding.recyclerBanners.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(bannerPosition, offset)
            }

        }
    }

}

class BannerDiffCallBack: DiffUtil.ItemCallback<LoyaltyInfo>(){

    override fun areItemsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return true
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: LoyaltyInfo, newItem: LoyaltyInfo): Boolean {
        return oldItem.banners == newItem.banners
    }

}