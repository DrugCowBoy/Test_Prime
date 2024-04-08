package com.example.testprime.presentation.loyalty.loyalty.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.dot.prime.R
import com.dot.prime.common.UiState
import com.dot.prime.presentation.common.helper_class.LoyaltyInfo
import com.dot.prime.presentation.common.color_class.ItemProductColors
import com.dot.prime.presentation.common.createSnackBar
import com.dot.prime.presentation.common.deviceId
import com.dot.prime.presentation.common.dpToPx
import com.dot.prime.presentation.common.navigation.BottomNavMenuListener
import com.dot.prime.presentation.common.navigation.ProfileTabsListener
import com.dot.prime.presentation.common.recycler_view.ListAnimator
import com.example.testprime.presentation.loyalty.date_birth_soon.DateBirthSoonFragment.Companion.BIRTHDAY_TYPE_ACTIVE
import com.example.testprime.presentation.loyalty.date_birth_soon.DateBirthSoonFragment.Companion.BIRTHDAY_TYPE_SOON
import com.example.testprime.presentation.loyalty.estimate_product.view.EstimateProductFragment.Companion.DEFAULT_ORDER
import com.dot.prime.presentation.loyalty.loyalty.adapter.*
import com.example.testprime.presentation.loyalty.loyalty.viewmodel.LoyaltyViewModel
import com.example.testprime.presentation.loyalty.loyalty.viewmodel.LoyaltyViewModel.Companion.ERROR_POST_LOYALTY
import com.example.testprime.databinding.FragmentLoyaltyBinding
import com.example.testprime.presentation.loyalty.loyalty.adapter.BannersAndDotsAdapter
import com.example.testprime.presentation.loyalty.loyalty.adapter.BaseButtonsAdapter
import com.example.testprime.presentation.loyalty.loyalty.adapter.CardBirthdayAdapter
import com.example.testprime.presentation.loyalty.loyalty.adapter.CardPreferredGroupAdapter
import com.example.testprime.presentation.loyalty.loyalty.adapter.CardStatusAdapter
import com.example.testprime.presentation.loyalty.loyalty.adapter.PopularProductAdapter
import com.example.testprime.presentation.loyalty.loyalty.adapter.PopularProductsTitleAdapter
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoyaltyFragment : Fragment() {

    private val colorProgressbar = "#A9D992"
    private val radiusButton = 10
    private val colorTextButton = "#FFFFFF"
    private val colorText = "#000000"
    private val colorSecondText = "#FFFFFF"
    private val colorSelectedTab = "#000000"
    private val colorUnselectedTab = "#909090"
    private val colorButtonUpdate = "#A9D992"
    //
    private val colorItemPlus = "#A9D992"
    private val colorItemMinus = "#F2F2F2"
    private val colorItemText = "#000000"
    private val colorItemSecondText = "#808080"

    private var _binding: FragmentLoyaltyBinding? = null
    private val binding get() = _binding!!

    private val loyaltyViewModel: LoyaltyViewModel by viewModels()

    private var bottomNavMenuListener: BottomNavMenuListener? = null
    private var profileTabsListener: ProfileTabsListener? = null

    private var requestPermissionLauncher: ActivityResultLauncher<String>? = null

    // Timer
    private var timerBanner: Timer? = null
    // For blocking screen
    private var isBlockedScreen = false

    // Adapter for banners and dots
    private var adapterBannersAndDots: BannersAndDotsAdapter? = null
    private var adapterBaseButtons: BaseButtonsAdapter? = null
    private var adapterCardStatus: CardStatusAdapter? = null
    private var adapterCardBirthday: CardBirthdayAdapter? = null
    private var adapterCardPreferredGroup: CardPreferredGroupAdapter? = null
    private var adapterPopularProductsTitle: PopularProductsTitleAdapter? = null
    private var adapterPopularProduct: PopularProductAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        profileTabsListener = context as? ProfileTabsListener
        bottomNavMenuListener = context as? BottomNavMenuListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // System back
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.moveTaskToBack(true)
            }
        })
        // Permission
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Log_prime", "Permission - yes")
            } else {
                Log.i("Log_prime", "Permission - no")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoyaltyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermissions()
        setCustomColors()
        setGeneralRecyclerView()
        getUserData()
        observeChangeUserData()
        navigateToProfile()
        observePopularProducts()
        observeBlockedScreen()
        registerFirebase()
        setSwipeRefresh()
    }

    private fun checkPermissions(){
        if (loyaltyViewModel.isPermissionShown) return else loyaltyViewModel.isPermissionShown = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val pushPermission = Manifest.permission.POST_NOTIFICATIONS
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(requireContext(), pushPermission) -> {
                    // Permissions accessed
                }
                else -> {
                    // Permissions denied
                    requestPermissionLauncher?.launch(pushPermission)
                }
            }
        }
    }

    private fun setCustomColors(){
        binding.layoutError.buttonUpdate.setParams(colorEnabled = colorButtonUpdate, radius = radiusButton, colorText = colorTextButton)
        binding.progressBar.indeterminateDrawable.setTint(Color.parseColor(colorProgressbar))
        binding.textViewName.setTextColor(Color.parseColor(colorText))
        binding.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor(colorTextButton))
        binding.swipeRefreshLayout.setColorSchemeColors(Color.parseColor(colorProgressbar))
    }

    private fun setGeneralRecyclerView(){
        // Base setting recycler
        binding.recyclerLoyalty.layoutManager = LinearLayoutManager(context)
        binding.recyclerLoyalty.itemAnimator = ListAnimator()

        // Banners and dots
        cancelTimerBanners()
        timerBanner = Timer()
        val widthScreen = resources.displayMetrics.widthPixels
        adapterBannersAndDots = BannersAndDotsAdapter(
            timerBanner = timerBanner,
            widthScreen = widthScreen
        )

        // Base buttons
        adapterBaseButtons = BaseButtonsAdapter(
            colorSecondText = colorText,
            clickButtonEstimate = {
                if (!isBlockedScreen){
                    try{
                        findNavController().navigate(
                            LoyaltyFragmentDirections.actionLoyaltyFragmentToEstimateProductFragment(DEFAULT_ORDER)
                        )
                    }catch (exception: Exception){Log.i("Log_prime", "Error")}
                }
            },
            clickButtonPersonalDiscount = {
                if (!isBlockedScreen){
                    try{
                        findNavController().navigate(R.id.action_loyaltyFragment_to_cardFragment2)
                    }catch (exception: Exception){Log.i("Log_prime", "Error")}
                }
            }
        )

        // Status
        adapterCardStatus = CardStatusAdapter(
            onClick = {
                findNavController().navigate(R.id.action_loyaltyFragment_to_statusFragment)
            }
        )

        // Birthday
        adapterCardBirthday = CardBirthdayAdapter(
            colorText = colorText,
            clickBirthDaySoon = {infoBirthDay ->
                if (!isBlockedScreen){
                    try{
                        findNavController().navigate(LoyaltyFragmentDirections.actionLoyaltyFragmentToDateBirthSoonFragment(
                            BIRTHDAY_TYPE_SOON,
                            infoBirthDay?.sub ?: "",
                            infoBirthDay?.longInfo ?: "")
                        )
                    }catch (exception: Exception){Log.i("Log_prime", "Error")}
                }
            },
            clickBirthDayCanActivate = {infoBirthDay ->
                if (!isBlockedScreen){
                    try{
                        findNavController().navigate(LoyaltyFragmentDirections.actionLoyaltyFragmentToDateBirthCanActivateFragment(
                            infoBirthDay?.sub ?: "")
                        )
                    }catch (exception: Exception){Log.i("Log_prime", "Error")}
                }
            },
            clickBirthDayActive = {infoBirthDay ->
                if (!isBlockedScreen){
                    try{
                        findNavController().navigate(LoyaltyFragmentDirections.actionLoyaltyFragmentToDateBirthSoonFragment(
                            BIRTHDAY_TYPE_ACTIVE,
                            infoBirthDay?.sub ?: "",
                            infoBirthDay?.longInfo ?: "")
                        )
                    }catch (exception: Exception){Log.i("Log_prime", "Error")}
                }
            },
            clickBirthDayActivate = {
                if (!isBlockedScreen){
                    loyaltyViewModel.enableBirthDaySales()
                }
            }
        )

        // Preferred Groups
        adapterCardPreferredGroup = CardPreferredGroupAdapter(
            colorText = colorSecondText,
            clickPreferredGroups = {statusPreferredGroups, infoPreferredGroups ->
                if (!isBlockedScreen){
                    val bundle = bundleOf("isUserRegister" to false)
                    findNavController().navigate(R.id.action_loyaltyFragment_to_preferred_groups_navigation, bundle)
                }
            }
        )

        // Popular Products title
        adapterPopularProductsTitle = PopularProductsTitleAdapter(
            colorText = colorText
        )

        // Popular Products
        adapterPopularProduct = PopularProductAdapter(
            itemProductColors = ItemProductColors(colorItemPlus = colorItemPlus, colorItemMinus = colorItemMinus, colorItemText = colorItemText, colorItemSecondText = colorItemSecondText),
            clickListener = {popularProduct ->
                if (!isBlockedScreen){
                    Log.i("Log_prime", "PopularProduct")
                }
            },
            changeBasketListener = {popularProduct, basketQuantity ->
                if (!isBlockedScreen){
                    Log.i("Log_prime", "PopularProduct")
                }
            }
        )

        // Concat
        val concatAdapter = ConcatAdapter()
        adapterBannersAndDots?.let {
            concatAdapter.addAdapter(it)
        }
        adapterBaseButtons?.let {
            concatAdapter.addAdapter(it)
        }
        adapterCardStatus?.let {
            concatAdapter.addAdapter(it)
        }
        adapterCardBirthday?.let {
            concatAdapter.addAdapter(it)
        }
        adapterCardPreferredGroup?.let {
            concatAdapter.addAdapter(it)
        }
        adapterPopularProductsTitle?.let {
            concatAdapter.addAdapter(it)
        }
        adapterPopularProduct?.let {
            concatAdapter.addAdapter(it)
        }
        binding.recyclerLoyalty.adapter = concatAdapter

    }

    private fun getUserData(){
        binding.layoutError.buttonUpdate.setOnClickListener {
            loyaltyViewModel.getUserData()
        }

        loyaltyViewModel.getUserData()

        loyaltyViewModel.userData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.i("Log_prime", "getUserData: Loading")
                    showLoading()
                    binding.swipeRefreshLayout.isRefreshing =
                        false// hide swipe to refresh icon animation
                }
                is UiState.Error -> {
                    Log.i("Log_prime", "getUserData: Error")
                    if (state.message == ERROR_POST_LOYALTY) {
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
                    Log.i("Log_prime", "getUserData: Success")
                    state.data?.let { loyaltyInfo ->
                        // Name
                        binding.textViewName.text = String.format(getString(R.string.hi_user), loyaltyInfo.userData?.name ?: "")
                        // General loyalty recycler
                        bindData(loyaltyInfo = loyaltyInfo)
                        // Success
                        showSuccess()
                    }
                }
                is UiState.Default -> {}
            }
        }
    }

    private fun bindData(loyaltyInfo: LoyaltyInfo){
        adapterBannersAndDots?.submitList(listOf(loyaltyInfo))
        adapterBaseButtons?.submitList(listOf(loyaltyInfo))
        adapterCardStatus?.submitList(listOf(loyaltyInfo))
        adapterCardBirthday?.submitList(listOf(loyaltyInfo))
        adapterCardPreferredGroup?.submitList(listOf(loyaltyInfo))
    }

    private fun observePopularProducts(){
        loyaltyViewModel.popularProducts.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.i("Log_prime", "getPopularProducts: Loading")
                }
                is UiState.Error -> {
                    Log.i("Log_prime", "getPopularProducts: Error")
                }
                is UiState.Success -> {
                    Log.i("Log_prime", "getPopularProducts: Success")
                    state.data?.let { listPopularProducts ->
                        adapterPopularProduct?.submitList(listPopularProducts)
                    }
                }
                is UiState.Default -> {}
            }
        }
    }

    private fun showLoading(){
        binding.layoutSuccess.visibility = View.GONE
        binding.layoutError.root.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError(){
        binding.layoutSuccess.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.layoutError.root.visibility = View.VISIBLE
    }

    private fun showSuccess(){
        binding.layoutError.root.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.layoutSuccess.visibility = View.VISIBLE
    }

    private fun observeChangeUserData(){
        // Clear savedStateHandle
        findNavController().currentBackStackEntry?.savedStateHandle?.remove<Boolean>(
            IS_USER_DATA_CHANGED
        )
        // Change data
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            IS_USER_DATA_CHANGED
        )?.observe(viewLifecycleOwner){isUserDataChanged ->
            if (isUserDataChanged){
                loyaltyViewModel.getUserData()
            }
        }
    }

    private fun cancelTimerBanners(){
        timerBanner?.cancel()
        timerBanner = null
    }

    private fun observeBlockedScreen(){
        loyaltyViewModel.isBlockedScreen.observe(viewLifecycleOwner) {
            isBlockedScreen = it
        }
    }

    private fun registerFirebase(){
        try {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val deviceId = context?.deviceId() ?: ""
                    val fcmToken = task.result
                    loyaltyViewModel.registerFirebase(deviceId = deviceId, fcmToken = fcmToken)
                }else{
                    Log.i("Log_prime", "Firebase Token: Error")
                }
            }
        }catch (exception: Exception){
            Log.i("Log_prime", "Firebase Error: ${exception.message}")
        }
    }

    private fun setSwipeRefresh(){
        binding.swipeRefreshLayout.setDistanceToTriggerSync(200.dpToPx(context))
        binding.swipeRefreshLayout.setOnRefreshListener {
            loyaltyViewModel.getUserData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loyaltyViewModel.cancelQueries()
        cancelTimerBanners()
        _binding = null
    }

    companion object{
        const val IS_USER_DATA_CHANGED = "is_user_data_changed"
    }

}