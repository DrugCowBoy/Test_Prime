package com.example.testprime.presentation.basket.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.testprime.databinding.DialogFragmentClearBasketBinding

class ClearBasketFragment: DialogFragment() {

    private val colorButton = "#A9D992"
    private val colorCancelButton = "#DDDDDD"
    private val radiusButton = 10
    private val colorTextButton = "#FFFFFF"
    private val colorText = "#000000"

    private var _binding: DialogFragmentClearBasketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFragmentClearBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCustomColors()
        bindClearBasket()
    }

    private fun setCustomColors(){
        binding.buttonCancel.setParams(colorEnabled = colorCancelButton, radius = radiusButton, colorText = colorTextButton)
        binding.buttonDelete.setParams(colorEnabled = colorButton, radius = radiusButton, colorText = colorTextButton)
        binding.textViewClearBasket.setTextColor(Color.parseColor(colorText))
        binding.textViewNotCancel.setTextColor(Color.parseColor(colorText))
    }

    private fun bindClearBasket(){
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonDelete.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.apply {
                this.set(IS_CLEAR_BASKET, true)
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val IS_CLEAR_BASKET = "is_clear_basket"
    }

}