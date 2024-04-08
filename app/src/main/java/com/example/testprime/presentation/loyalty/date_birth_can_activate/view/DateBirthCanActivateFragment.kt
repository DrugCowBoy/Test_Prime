package com.example.testprime.presentation.loyalty.date_birth_can_activate.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.Coil
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.dot.prime.presentation.common.createSnackBar
import com.dot.prime.presentation.common.dpToPxFloat
import com.example.testprime.presentation.loyalty.date_birth_can_activate.viewmodel.DateBirthCanActivateViewModel
import com.example.testprime.presentation.loyalty.loyalty.view.LoyaltyFragment.Companion.IS_USER_DATA_CHANGED
import com.example.testprime.R
import com.example.testprime.common.UiState
import com.example.testprime.databinding.DialogFragmentBirthDateCanActivateBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
DateBirthCanActivateFragment: BottomSheetDialogFragment() {

    private val colorButton = "#A9D992"
    private val colorProgressbar = "#A9D992"
    private val radiusButton = 10
    private val colorTextButton = "#FFFFFF"
    private val colorText = "#000000"

    private var _binding: DialogFragmentBirthDateCanActivateBinding? = null
    private val binding get() = _binding!!

    private val dateBirthCanActivateViewModel: DateBirthCanActivateViewModel by viewModels()

    private val args by navArgs<DateBirthCanActivateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFragmentBirthDateCanActivateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setScreen()
        setCustomColors()
        bind()
        observeBirthDaySales()
    }

    private fun setScreen(){
        // Screen with circle border
        val bottomSheet = view?.parent as View
        bottomSheet.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        // Screen all size
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setCustomColors(){
        binding.buttonActivate.setParams(colorEnabled = colorButton, radius = radiusButton, colorText = colorTextButton)
        binding.textViewMoreCoins.setTextColor(Color.parseColor(colorText))
        binding.textViewPromotion.setTextColor(Color.parseColor(colorText))
        binding.textViewPromotionInfo.setTextColor(Color.parseColor(colorText))
        binding.textViewPromotionDetails.setTextColor(Color.parseColor(colorText))
        binding.progressBar.indeterminateDrawable.setTint(Color.parseColor(colorProgressbar))
    }

    private fun bind(){
        setImageHead()
        binding.textViewPromotion.text = args.sub
    }

    private fun setImageHead(){
        val request = ImageRequest.Builder(requireContext())
            .data(R.drawable.birth_date_head)
            .transformations(RoundedCornersTransformation(topLeft = 15.dpToPxFloat(requireContext()), topRight = 15.dpToPxFloat(requireContext())))
            .target(binding.headImage)
            .build()
        Coil.imageLoader(requireContext()).enqueue(request)
    }

    private fun observeBirthDaySales(){
        binding.buttonActivate.setOnClickListener {
            dateBirthCanActivateViewModel.enableBirthDaySales()
        }

        dateBirthCanActivateViewModel.birthDaySales.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.i("Log_prime", "BirthDaySales: Loading")
                    showLoading()
                }
                is UiState.Error -> {
                    Log.i("Log_prime", "BirthDaySales: Error")
                    showError()
                }
                is UiState.Success -> {
                    Log.i("Log_prime", "BirthDaySales: Success")
                    state.data?.let { _ ->
                        showSuccess()
                    }
                }
                is UiState.Default -> {}
            }
        }
    }

    private fun showLoading(){
        binding.buttonActivate.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.buttonActivate.visibility = View.VISIBLE
        binding.buttonActivate.createSnackBar(message = getString(R.string.connection_error), actionOk = getString(R.string.dialog_ok))
    }

    private fun showSuccess(){
        findNavController().previousBackStackEntry?.savedStateHandle?.apply {
            this[IS_USER_DATA_CHANGED] = true
        }
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}