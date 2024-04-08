package com.example.testprime.presentation.loyalty.date_birth_soon

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.dot.prime.R
import com.dot.prime.common.DevProd.Companion.IS_MVP_ENABLED
import com.dot.prime.databinding.DialogFragmentBirthDateSoonBinding
import com.dot.prime.presentation.common.navigation.BottomNavMenuListener
import com.dot.prime.presentation.common.navigation.ProfileTabsListener
import com.dot.prime.presentation.common.makeBoldSymbols
import com.dot.prime.presentation.common.navigation.BottomNavMenuTab
import com.dot.prime.presentation.common.navigation.ProfileTab
import com.example.testprime.databinding.DialogFragmentBirthDateSoonBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DateBirthSoonFragment: BottomSheetDialogFragment() {

    private val colorButton = "#A9D992"
    private val radiusButton = 10
    private val colorTextButton = "#FFFFFF"
    private val colorText = "#000000"
    private val colorSelectedTab = "#000000"
    private val colorUnselectedTab = "#909090"

    private var _binding: DialogFragmentBirthDateSoonBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DateBirthSoonFragmentArgs>()
    private var profileTabsListener: ProfileTabsListener? = null
    private var bottomNavMenuListener: BottomNavMenuListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileTabsListener = context as? ProfileTabsListener
        bottomNavMenuListener = context as? BottomNavMenuListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFragmentBirthDateSoonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setScreen()
        setCustomColors()
        bind()
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
        binding.buttonGreat.setParams(colorEnabled = colorButton, radius = radiusButton, colorText = colorTextButton)
        binding.textViewBirthdaySoon.setTextColor(Color.parseColor(colorText))
        binding.textViewPromotion.setTextColor(Color.parseColor(colorText))
        binding.textViewDate.setTextColor(Color.parseColor(colorText))
        binding.textViewCardDishesTitle.setTextColor(Color.parseColor(colorText))
        binding.textViewCardDishesDesc.setTextColor(Color.parseColor(colorText))
        binding.textViewToFavorites.setTextColor(Color.parseColor(colorText))
    }

    private fun bind(){
        if (args.typeBirthday == BIRTHDAY_TYPE_ACTIVE){
            binding.textViewBirthdaySoon.text = getString(R.string.bd_birthday_active)
        }else{
            binding.textViewBirthdaySoon.text = getString(R.string.bd_birthday_soon)
        }
        binding.textViewPromotion.text = args.sub
        val textInfo = args.info.replaceFirst(", ", ",\n")
        binding.textViewDate.text = textInfo.makeBoldSymbols(context = requireContext(), regex = "(\\d+)%")
        binding.imageViewProduct.setImageResource(R.drawable.example_dish_1)
        binding.buttonGreat.setOnClickListener {
            dismiss()
        }
        binding.btnToFavorites.setOnClickListener {
            dismiss()
            navigateToFavorites()
        }

        if (IS_MVP_ENABLED){
            binding.card.visibility = View.GONE
        }
    }

    private fun navigateToFavorites(){
        try {
            val imageProfile = activity?.findViewById<ImageView>(R.id.image_profile)
            val textProfile = activity?.findViewById<TextView>(R.id.text_profile)
            val imageLoyalty = activity?.findViewById<ImageView>(R.id.image_loyalty)
            val textLoyalty = activity?.findViewById<TextView>(R.id.text_loyalty)
            imageProfile?.setColorFilter(Color.parseColor(colorSelectedTab))
            textProfile?.setTextColor(Color.parseColor(colorSelectedTab))
            textProfile?.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular), Typeface.BOLD)
            imageLoyalty?.setColorFilter(Color.parseColor(colorUnselectedTab))
            textLoyalty?.setTextColor(Color.parseColor(colorUnselectedTab))
            textLoyalty?.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular), Typeface.NORMAL)

            val navController = activity?.findViewById<FragmentContainerView>(R.id.main_fragment_container)?.getFragment<NavHostFragment>()?.navController
            bottomNavMenuListener?.setBottomMenuTab(tab = BottomNavMenuTab.Profile)
            profileTabsListener?.setProfileTab(tab = ProfileTab.Favorites)
            navController?.let{
                navController.navigate(R.id.profileContainerFragment, null, navOptions {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                })
            }
        }catch (exception: Exception){ Log.i("Log_prime", "Error")}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val BIRTHDAY_TYPE_SOON = 0
        const val BIRTHDAY_TYPE_ACTIVE = 1
    }

}