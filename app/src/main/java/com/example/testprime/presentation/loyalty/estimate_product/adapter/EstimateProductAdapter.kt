package com.example.testprime.presentation.loyalty.estimate_product.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dot.prime.R
import com.dot.prime.databinding.EstimateProductItemBinding
import com.dot.prime.domain.loyalty.model.EstimateProduct
import com.dot.prime.presentation.common.color_class.EstimateProductColors
import com.dot.prime.presentation.common.setParams
import com.example.testprime.R
import com.example.testprime.domain.loyalty.model.EstimateProduct
import com.example.testprime.presentation.common.color_class.EstimateProductColors

class EstimateProductAdapter(
    private val estimateProductColors: EstimateProductColors,
    private val clickEstimateListener: (EstimateProduct, Int, String) -> Unit
): ListAdapter<EstimateProduct, EstimateProductAdapter.EstimateProductViewHolder>(
    EstimateProductDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstimateProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.estimate_product_item, parent, false)
        return EstimateProductViewHolder(
            view,
            clickEstimate = { position, grade, comment ->
                getItem(position)?.let { product ->
                    clickEstimateListener(product, grade, comment)
                }
            })
    }

    override fun onBindViewHolder(holder: EstimateProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EstimateProductViewHolder(
        itemView: View, clickEstimate: (Int, Int, String) -> Unit
    ): RecyclerView.ViewHolder(itemView){
        val binding = EstimateProductItemBinding.bind(itemView)

        fun bind(estimateProduct: EstimateProduct){
            // Set custom colors
            binding.buttonEstimate.setParams(
                colorEnabled = estimateProductColors.colorButtonEnabled,
                colorDisabled = estimateProductColors.colorButtonDisabled,
                radius = estimateProductColors.radiusButton,
                colorText = estimateProductColors.colorTextButton
            )
            binding.editTextFeedback.setParams(
                radiusField = estimateProductColors.radiusField,
                colorTextField = estimateProductColors.colorTextField,
                colorBorderField = estimateProductColors.colorBorderField,
                colorHintField = estimateProductColors.colorHintField,
                thicknessBorder = 1
            )
            binding.textViewTitle.setTextColor(Color.parseColor(estimateProductColors.colorText))
            binding.textViewCoins.setTextColor(Color.parseColor(estimateProductColors.colorText))
            binding.imageViewCoins.setColorFilter(Color.parseColor(estimateProductColors.colorText))
            // Set default stars
            binding.grade.text = "0"
            setColorStars(0)
            // Set empty field
            binding.editTextFeedback.setText("")
            // Disable button
            binding.buttonEstimate.isEnabled = false

            try{
                if (estimateProduct.image.isNullOrEmpty()) throw Exception()
                binding.imageViewProduct.load(estimateProduct.image){
                    crossfade(true)
                    placeholder(R.drawable.placeholder_product)
                }
            }catch (exception: Exception){
                binding.imageViewProduct.setImageResource(R.drawable.placeholder_product)
            }
            binding.textViewTitle.text = estimateProduct.name
            binding.textViewDate.text = estimateProduct.paymentDate
            binding.textViewCoins.text = itemView.context.getString(R.string.estimate_bonus)
        }

        init {
            binding.buttonEstimate.setOnClickListener {
                if (binding.buttonEstimate.isEnabled){
                    val comment = binding.editTextFeedback.text.toString().trim()
                    val grade = binding.grade.text.toString().toInt()
                    if (grade != 0){
                        clickEstimate(bindingAdapterPosition, grade, comment)
                    }
                }
            }
            binding.imageViewStar1.setOnClickListener {
                binding.grade.text = "1"
                setColorStars(1)
            }

            binding.imageViewStar2.setOnClickListener {
                binding.grade.text = "2"
                setColorStars(2)
            }

            binding.imageViewStar3.setOnClickListener {
                binding.grade.text = "3"
                setColorStars(3)
            }

            binding.imageViewStar4.setOnClickListener {
                binding.grade.text = "4"
                setColorStars(4)
            }

            binding.imageViewStar5.setOnClickListener {
                binding.grade.text = "5"
                setColorStars(5)
            }
        }

        private fun setColorStars(count: Int){
            if (count > 0){
                binding.buttonEstimate.isEnabled = true
                binding.imageViewStar1.setColorFilter(Color.parseColor(estimateProductColors.colorStarEnabled))
                if (count > 1){
                    binding.imageViewStar2.setColorFilter(Color.parseColor(estimateProductColors.colorStarEnabled))
                    if (count > 2){
                        binding.imageViewStar3.setColorFilter(Color.parseColor(estimateProductColors.colorStarEnabled))
                        if (count > 3){
                            binding.imageViewStar4.setColorFilter(Color.parseColor(estimateProductColors.colorStarEnabled))
                            if (count > 4){
                                binding.imageViewStar5.setColorFilter(Color.parseColor(estimateProductColors.colorStarEnabled))
                            }else{
                                binding.imageViewStar5.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                            }
                        }else{
                            binding.imageViewStar4.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                            binding.imageViewStar5.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                        }
                    }else{
                        binding.imageViewStar3.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                        binding.imageViewStar4.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                        binding.imageViewStar5.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                    }
                }else{
                    binding.imageViewStar2.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                    binding.imageViewStar3.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                    binding.imageViewStar4.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                    binding.imageViewStar5.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                }
            }else{
                binding.imageViewStar1.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                binding.imageViewStar2.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                binding.imageViewStar3.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                binding.imageViewStar4.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
                binding.imageViewStar5.setColorFilter(Color.parseColor(estimateProductColors.colorStarDisabled))
            }
        }
    }

}

class EstimateProductDiffCallBack: DiffUtil.ItemCallback<EstimateProduct>(){

    override fun areItemsTheSame(oldItem: EstimateProduct, newItem: EstimateProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EstimateProduct, newItem: EstimateProduct): Boolean {
        return oldItem == newItem
    }

}