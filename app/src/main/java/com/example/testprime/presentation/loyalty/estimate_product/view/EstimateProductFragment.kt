package com.example.testprime.presentation.loyalty.estimate_product.view

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.dot.prime.R
import com.dot.prime.common.UiState
import com.dot.prime.databinding.DialogFragmentEstimateProductBinding
import com.dot.prime.domain.loyalty.model.EstimateProduct
import com.dot.prime.presentation.common.color_class.EstimateProductColors
import com.dot.prime.presentation.common.createSnackBar
import com.dot.prime.presentation.detailed_order.view.DetailedOrderFragment
import com.example.testprime.presentation.loyalty.estimate_product.adapter.EstimateProductAdapter
import com.example.testprime.presentation.loyalty.estimate_product.viewmodel.EstimateProductViewModel
import com.example.testprime.presentation.loyalty.loyalty.view.LoyaltyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EstimateProductFragment: DialogFragment() {

    private val colorButtonEnabled = "#A9D992"
    private val colorButtonDisabled = "#DDDDDD"
    private val colorProgressbar = "#A9D992"
    private val colorStarEnabled = "#A9D992"
    private val colorStarDisabled = "#D9D9D9"
    private val radiusButton = 10
    private val radiusField = 10
    private val colorTextButton = "#FFFFFF"
    private val colorTextField = "#000000"
    private val colorText = "#000000"
    private val colorBorderField = "#D9D9D9"
    private val colorHintField = "#D9D9D9"

    private var _binding: DialogFragmentEstimateProductBinding? = null
    private val binding get() = _binding!!

    private val estimateProductViewModel: EstimateProductViewModel by viewModels()

    private val args by navArgs<EstimateProductFragmentArgs>()

    // Adapter for estimate cards
    private var estimateProductAdapter: EstimateProductAdapter? = null
    // Position estimate card
    private var estimatePosition = 0
    // Data user changed
    private var isUserDataChanged = false
    // For blocking list, when estimate loading
    private var isBlockedList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set dialog full screen
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set mode window above keyboard
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        _binding = DialogFragmentEstimateProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("Log_prime", "Estimate order: ${args.orderId}")
        setCustomColors()
        bind()
        setRecyclerEstimateProducts()
        getEstimateProducts()
        observeBlockedList()
    }

    private fun setCustomColors(){
        binding.buttonUpdate.setParams(colorEnabled = colorButtonEnabled, radius = radiusButton, colorText = colorTextButton)
        binding.progressBar.indeterminateDrawable.setTint(Color.parseColor(colorProgressbar))
    }

    private fun bind(){
        binding.iconClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setRecyclerEstimateProducts(){
        estimateProductAdapter = EstimateProductAdapter(
            estimateProductColors = EstimateProductColors(
                colorButtonEnabled = colorButtonEnabled,
                colorButtonDisabled = colorButtonDisabled,
                colorStarEnabled = colorStarEnabled,
                colorStarDisabled = colorStarDisabled,
                radiusButton = radiusButton,
                radiusField = radiusField,
                colorTextButton = colorTextButton,
                colorTextField = colorTextField,
                colorText = colorText,
                colorBorderField = colorBorderField,
                colorHintField = colorHintField
            ),
            clickEstimateListener = {estimateProduct, grade, comment ->
                if (!isBlockedList){
                    Log.i("Log_prime", "Estimate: ${estimateProduct.name}, grade: $grade, comment $comment")
                    isUserDataChanged = true
                    estimateProductViewModel.postEstimateProduct(idProduct = estimateProduct.id ?: "", grade = grade, userComment = comment)
                }
            }
        )
        binding.recyclerEstimateProducts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerEstimateProducts.adapter = estimateProductAdapter
        // Set SnapHelper for stable position
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerEstimateProducts)
    }

    private fun getEstimateProducts(){
        binding.buttonUpdate.setOnClickListener {
            estimateProductViewModel.getEstimateProducts(orderId = args.orderId)
        }

        estimateProductViewModel.getEstimateProducts(orderId = args.orderId)

        estimateProductViewModel.estimateProducts.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.i("Log_prime", "getEstimateProducts: Loading")
                    showLoading()
                }
                is UiState.Error -> {
                    Log.i("Log_prime", "getEstimateProducts: Error")
                    if (state.message == EstimateProductViewModel.ERROR_POST_ESTIMATE_PRODUCT) {
                        binding.progressBar.createSnackBar(
                            message = getString(R.string.connection_error),
                            actionOk = getString(R.string.dialog_ok)
                        )
                        showSuccess()
                    } else {
                        showError()
                    }
                }
                is UiState.Success -> {
                    Log.i("Log_prime", "getEstimateProducts: Success")
                    state.data?.let { estimateProductsList ->
                        if (estimateProductsList.isNotEmpty()) {
                            bindEstimateProducts(estimateProductsList = estimateProductsList)
                            setIndicator(size = estimateProductsList.size)
                        } else {
                            dismiss()
                        }
                    }
                }
                is UiState.Default -> {}
            }
        }
    }

    private fun bindEstimateProducts(estimateProductsList: List<EstimateProduct>){
        estimateProductAdapter?.submitList(estimateProductsList){
            showSuccess()
        }
    }

    private fun setIndicator(size: Int){
        binding.textViewIndicator.text = String.format(getString(R.string.estimate_indicator), estimatePosition + 1, size)
        // ScrollListener
        binding.recyclerEstimateProducts.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
            val visiblePositionBanner = (binding.recyclerEstimateProducts.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if ((visiblePositionBanner != -1) && (visiblePositionBanner != estimatePosition)){
                estimatePosition = visiblePositionBanner
                binding.textViewIndicator.text = String.format(getString(R.string.estimate_indicator), visiblePositionBanner + 1, size)
            }
        }
    }

    private fun observeBlockedList(){
        estimateProductViewModel.isBlockedList.observe(viewLifecycleOwner) {
            isBlockedList = it
        }
    }

    private fun showLoading(){
        binding.layoutSuccess.visibility = View.GONE
        binding.layoutError.visibility = View.GONE
        binding.iconClose.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError(){
        binding.progressBar.visibility = View.GONE
        binding.layoutSuccess.visibility = View.GONE
        binding.layoutError.visibility = View.VISIBLE
        binding.iconClose.visibility = View.VISIBLE
    }

    private fun showSuccess(){
        binding.progressBar.visibility = View.GONE
        binding.layoutError.visibility = View.GONE
        binding.layoutSuccess.visibility = View.VISIBLE
        binding.iconClose.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val countItemsEstimate = estimateProductViewModel.estimateProducts.value?.data?.size
        findNavController().previousBackStackEntry?.savedStateHandle?.apply {
            this[LoyaltyFragment.IS_USER_DATA_CHANGED] = isUserDataChanged
            this[DetailedOrderFragment.COUNT_ITEMS_ESTIMATE] = countItemsEstimate
        }
    }

    companion object{
        const val DEFAULT_ORDER = "0"
    }

}