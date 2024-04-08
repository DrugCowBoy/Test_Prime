package com.example.testprime.presentation.basket.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.dot.prime.R
import com.dot.prime.common.DevProd.Companion.IS_MVP_ENABLED
import com.dot.prime.common.UiState
import com.dot.prime.databinding.FragmentBasketBinding
import com.dot.prime.domain.basket.model.ItemBasket
import com.example.testprime.presentation.basket.adapter.BasketProductAdapter
import com.example.testprime.presentation.basket.dialog.ClearBasketFragment.Companion.IS_CLEAR_BASKET
import com.example.testprime.presentation.basket.viewmodel.BasketViewModel
import com.example.testprime.presentation.basket.viewmodel.BasketViewModel.Companion.ERROR_POST_BASKET
import com.example.testprime.presentation.common.color_class.ItemProductColors
import com.dot.prime.presentation.common.createSnackBar
import com.dot.prime.presentation.common.recycler_view.ListAnimator
import com.dot.prime.presentation.common.recycler_view.SwipeToDeleteCallback
import com.dot.prime.presentation.product.view.ProductFragment.Companion.CODE_BASKET_AND_FAVORITES_CHANGED
import com.dot.prime.presentation.product.view.ProductFragment.Companion.CODE_BASKET_CHANGED
import com.dot.prime.presentation.product.view.ProductFragment.Companion.IS_BASKET_AND_FAVORITES_CHANGED_KEY
import com.example.testprime.R
import com.example.testprime.common.DevProd.Companion.IS_MVP_ENABLED
import com.example.testprime.common.UiState
import com.example.testprime.databinding.FragmentBasketBinding
import com.example.testprime.domain.basket.model.ItemBasket
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private val colorProgressbar = "#A9D992"
    private val radiusButton = 10
    private val colorTextButton = "#FFFFFF"
    private val colorText = "#000000"
    private val colorEmpty = "#808080"
    private val colorButtonUpdate = "#A9D992"
    //
    private val colorItemPlus = "#A9D992"
    private val colorItemMinus = "#F2F2F2"
    private val colorItemText = "#000000"
    private val colorItemSecondText = "#808080"

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val basketViewModel: BasketViewModel by viewModels()

    // Adapter
    private var basketProductAdapter: BasketProductAdapter? = null
    // For blocking list, when product loading in basket
    private var isBlockedList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // System back
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.moveTaskToBack(true)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (IS_MVP_ENABLED){
            setPlaceHolderBasket()
        }else{
            binding.layoutPlaceholder.visibility = View.GONE
            setCustomColors()
            bindBasket()
            setRecyclerBasketProducts()
            getBasketProducts()
            observeChangeBasket()
            observeBlockedList()
        }
    }

    private fun setPlaceHolderBasket(){
        binding.layoutSuccess.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.layoutEmptyBasket.visibility = View.GONE
        binding.layoutError.root.visibility = View.GONE
        binding.layoutPlaceholder.visibility = View.VISIBLE
    }

    private fun setCustomColors(){
        binding.layoutError.buttonUpdate.setParams(colorEnabled = colorButtonUpdate, radius = radiusButton, colorText = colorTextButton)
        binding.progressBar.indeterminateDrawable.setTint(Color.parseColor(colorProgressbar))
        binding.textToolbar.setTextColor(Color.parseColor(colorText))
        binding.textViewEmptyBasket.setTextColor(Color.parseColor(colorEmpty))
        binding.imageViewEmptyBasket.setColorFilter(Color.parseColor(colorEmpty))
    }

    private fun bindBasket(){
        binding.btnClearBasket.setOnClickListener {
            try {
                findNavController().navigate(R.id.action_basketFragment_to_clearBasketFragment)
            }catch (exception: Exception){Log.i("Log_prime", "Error")}
        }
    }

    private fun setRecyclerBasketProducts(){
        basketProductAdapter = BasketProductAdapter(
            itemProductColors = ItemProductColors(colorItemPlus = colorItemPlus, colorItemMinus = colorItemMinus, colorItemText = colorItemText, colorItemSecondText = colorItemSecondText),
            clickListener = {basketProduct ->
                if (!isBlockedList){
                    basketProduct.product?.id?.let { idProduct ->
                        try {
                            findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToProductFragment2(idProduct, basketProduct.quantity ?: 0))
                        }catch (exception: Exception){Log.i("Log_prime", "Error")}
                    }
                }
            },
            changeBasketListener = { item, quantity ->
                if (!isBlockedList){
                    Log.i("Log_prime", "Click Basket: ${item.product?.name}, $quantity")
                    basketViewModel.changeBasket(id = (item.product?.id ?: ""), quantity = quantity)
                }
            },
            deleteItem = { item ->
                basketViewModel.changeBasket(id = (item.product?.id ?: ""), quantity = 0)
            }
        )
        binding.recyclerBasket.layoutManager = LinearLayoutManager(context)
        binding.recyclerBasket.adapter = basketProductAdapter
        binding.recyclerBasket.itemAnimator = ListAnimator()

        val swipeHandler = object : SwipeToDeleteCallback(
            context = requireContext(),
            onSwiped = {viewHolder ->
                basketProductAdapter?.deleteItem(adapterPosition = viewHolder.bindingAdapterPosition)
            }
        ){}
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerBasket)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getBasketProducts(){
        binding.layoutError.buttonUpdate.setOnClickListener {
            basketViewModel.getBasketProducts()
        }

        basketViewModel.getBasketProducts()

        basketViewModel.basketProducts.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.i("Log_prime", "GetBasket: Loading")
                    showLoading()
                }
                is UiState.Error -> {
                    Log.i("Log_prime", "GetBasket: Error")
                    if (state.message == ERROR_POST_BASKET) {
                        binding.progressBar.createSnackBar(
                            message = getString(R.string.connection_error),
                            actionOk = getString(R.string.dialog_ok)
                        )
                        basketProductAdapter?.notifyDataSetChanged()// for updating list, if item not deleted (swipe)
                        showSuccess()
                    } else {
                        showError()
                    }
                }
                is UiState.Success -> {
                    Log.i("Log_prime", "GetBasket: Success")
                    state.data?.let { basketList ->
                        bindBasketProducts(basketList)
                    }
                }
                is UiState.Default -> {}
            }
        }
    }

    private fun bindBasketProducts(basketList: List<ItemBasket>){
        basketProductAdapter?.submitList(basketList){
            showSuccess()
            if (basketList.isEmpty()){
                binding.layoutEmptyBasket.visibility = View.VISIBLE
                binding.btnClearBasket.visibility = View.INVISIBLE
            }else{
                binding.layoutEmptyBasket.visibility = View.GONE
                binding.btnClearBasket.visibility = View.VISIBLE
            }
        }
    }

    private fun observeChangeBasket(){
        // Clear savedStateHandle
        findNavController().currentBackStackEntry?.savedStateHandle?.remove<Int>(IS_BASKET_AND_FAVORITES_CHANGED_KEY)
        findNavController().currentBackStackEntry?.savedStateHandle?.remove<Boolean>(IS_CLEAR_BASKET)
        // Change basket count
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(IS_BASKET_AND_FAVORITES_CHANGED_KEY)
            ?.observe(viewLifecycleOwner){code ->
                if ((code == CODE_BASKET_CHANGED || code == CODE_BASKET_AND_FAVORITES_CHANGED)){
                    basketViewModel.getBasketProducts()
                }
            }
        // Change clear basket
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(IS_CLEAR_BASKET)
            ?.observe(viewLifecycleOwner){isClearBasket ->
                if (isClearBasket){
                    Log.i("Log_prime", "Clear basket")
                    basketViewModel.clearAllBasket()
                }
            }
    }

    private fun observeBlockedList(){
        basketViewModel.isBlockedList.observe(viewLifecycleOwner) {
            isBlockedList = it
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

    override fun onDestroyView() {
        super.onDestroyView()
        basketViewModel.cancelQueries()
        _binding = null
    }

}