package com.example.testprime.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprime.presentation.main.view.MainFragment
import com.example.testprime.domain.unleash.model.UnleashEntity
import com.example.testprime.domain.unleash.usecases.ReadFeaturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readFeaturesUseCase: ReadFeaturesUseCase
): ViewModel() {

    private val _flags = MutableLiveData<UnleashEntity>()
    val flags : LiveData<UnleashEntity> = _flags

    var tab = MainFragment.TAB_LOYALTY

    fun getFlags(){
        viewModelScope.launch {
            val flags = readFeaturesUseCase().first()
            _flags.value = flags
        }
    }

}