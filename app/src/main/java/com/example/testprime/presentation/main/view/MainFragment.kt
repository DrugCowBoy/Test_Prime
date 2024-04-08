package com.example.testprime.presentation.main.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.dot.prime.R
import com.dot.prime.databinding.FragmentMainBinding
import com.dot.prime.presentation.common.navigation.BottomNavMenuListener
import com.dot.prime.presentation.common.navigation.BottomNavMenuTab
import com.dot.prime.presentation.common.navigation.ProfileTab
import com.dot.prime.presentation.common.navigation.ProfileTabsListener
import com.example.testprime.R
import com.example.testprime.databinding.FragmentMainBinding
import com.example.testprime.presentation.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(){

    private val colorSelectedTab = "#000000"
    private val colorUnselectedTab = "#909090"
    private val colorTabBar = "#F6F6F6"

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    private var bottomNavMenuListener: BottomNavMenuListener? = null
    private var profileTabsListener: ProfileTabsListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bottomNavMenuListener = context as? BottomNavMenuListener
        profileTabsListener = context as? ProfileTabsListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // First settings
        setCustomColor()
        val navController = binding.mainFragmentContainer.getFragment<NavHostFragment>().navController
        // Restore tab position
        when(mainViewModel.tab){
            TAB_LOYALTY -> selectTab(TAB_LOYALTY)
            TAB_MENU -> selectTab(TAB_MENU)
            TAB_BASKET -> selectTab(TAB_BASKET)
            TAB_PROFILE -> selectTab(TAB_PROFILE)
        }

        // Get feature flags
        mainViewModel.getFlags()
        mainViewModel.flags.observe(viewLifecycleOwner) { flags ->
            val isPaginationEnabled = flags.catalogPaginationIsEnabled ?: false
            //val isPaginationEnabled = true
            binding.menu.setOnClickListener {
                if (bottomNavMenuListener?.getCurrentBottomMenuTab() == BottomNavMenuTab.Menu) {
                    if (isPaginationEnabled) {
                        navigateTo(R.id.menuPaginationContainerFragment, navController, false)
                    } else {
                        navigateTo(R.id.menuContainerFragment, navController, false)
                    }
                } else {
                    selectTab(TAB_MENU)
                    if (isPaginationEnabled) {
                        navigateTo(R.id.menuPaginationContainerFragment, navController)
                    } else {
                        navigateTo(R.id.menuContainerFragment, navController)
                    }
                }
            }
        }

        binding.loyalty.setOnClickListener {
            if (bottomNavMenuListener?.getCurrentBottomMenuTab() == BottomNavMenuTab.Loyalty){
                navigateTo(R.id.loyaltyContainerFragment, navController, false)
            }else{
                selectTab(TAB_LOYALTY)
                navigateTo(R.id.loyaltyContainerFragment, navController)
            }
        }
        binding.basket.setOnClickListener {
            if (bottomNavMenuListener?.getCurrentBottomMenuTab() == BottomNavMenuTab.Basket){
                navigateTo(R.id.basketContainerFragment, navController, false)
            }else{
                selectTab(TAB_BASKET)
                navigateTo(R.id.basketContainerFragment, navController)
            }
        }
        binding.profile.setOnClickListener {
            if (bottomNavMenuListener?.getCurrentBottomMenuTab() == BottomNavMenuTab.Profile){
                profileTabsListener?.setProfileTab(tab = ProfileTab.MyData)
                navigateTo(R.id.profileContainerFragment, navController, false)
            }else{
                selectTab(TAB_PROFILE)
                navigateTo(R.id.profileContainerFragment, navController)
            }
        }
        binding.card.setOnClickListener {
            binding.mainFragmentContainer.clearFocus()// clear focus for EditText on fragments
            try{
                findNavController().navigate(R.id.action_mainFragment_to_cardFragment)
            }catch (exception: Exception){ Log.i("Log_prime", "Error")}
        }

    }

    private fun setCustomColor(){
        binding.mainBottomMenu.setBackgroundColor(Color.parseColor(colorTabBar))
    }

    private fun navigateTo(destinationId: Int, navController: NavController, restoreScreen: Boolean = true) {
        navController.navigate(destinationId, null, navOptions {
            launchSingleTop = true
            restoreState = restoreScreen
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        })
    }

    private fun selectTab(tab: String){
        when(tab){
            TAB_LOYALTY ->{
                bottomNavMenuListener?.setBottomMenuTab(tab = BottomNavMenuTab.Loyalty)

                binding.imageLoyalty.setColorFilter(Color.parseColor(colorSelectedTab))
                binding.textLoyalty.setTextColor(Color.parseColor(colorSelectedTab))
                binding.textLoyalty.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_heavy)

                binding.imageMenu.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textMenu.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textMenu.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageBasket.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textBasket.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textBasket.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageProfile.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textProfile.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textProfile.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
            }
            TAB_MENU ->{
                bottomNavMenuListener?.setBottomMenuTab(tab = BottomNavMenuTab.Menu)

                binding.imageMenu.setColorFilter(Color.parseColor(colorSelectedTab))
                binding.textMenu.setTextColor(Color.parseColor(colorSelectedTab))
                binding.textMenu.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_heavy)

                binding.imageLoyalty.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textLoyalty.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textLoyalty.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageBasket.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textBasket.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textBasket.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageProfile.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textProfile.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textProfile.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
            }
            TAB_BASKET ->{
                bottomNavMenuListener?.setBottomMenuTab(tab = BottomNavMenuTab.Basket)

                binding.imageBasket.setColorFilter(Color.parseColor(colorSelectedTab))
                binding.textBasket.setTextColor(Color.parseColor(colorSelectedTab))
                binding.textBasket.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_heavy)

                binding.imageMenu.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textMenu.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textMenu.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageLoyalty.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textLoyalty.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textLoyalty.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageProfile.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textProfile.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textProfile.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
            }
            TAB_PROFILE ->{
                bottomNavMenuListener?.setBottomMenuTab(tab = BottomNavMenuTab.Profile)

                binding.imageProfile.setColorFilter(Color.parseColor(colorSelectedTab))
                binding.textProfile.setTextColor(Color.parseColor(colorSelectedTab))
                binding.textProfile.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_heavy)

                binding.imageMenu.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textMenu.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textMenu.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageLoyalty.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textLoyalty.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textLoyalty.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
                binding.imageBasket.setColorFilter(Color.parseColor(colorUnselectedTab))
                binding.textBasket.setTextColor(Color.parseColor(colorUnselectedTab))
                binding.textBasket.typeface = ResourcesCompat.getFont(requireContext(), R.font.object_sans_regular)
            }
        }
    }

    override fun onDestroyView() {
        // Save tab position
        when(Color.parseColor(colorSelectedTab)){
            binding.textLoyalty.currentTextColor -> mainViewModel.tab = TAB_LOYALTY
            binding.textMenu.currentTextColor -> mainViewModel.tab = TAB_MENU
            binding.textBasket.currentTextColor -> mainViewModel.tab = TAB_BASKET
            binding.textProfile.currentTextColor -> mainViewModel.tab = TAB_PROFILE
        }
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val TAB_LOYALTY = "tab_loyalty"
        const val TAB_MENU = "tab_menu"
        const val TAB_BASKET = "tab_basket"
        const val TAB_PROFILE = "tab_profile"
    }

}