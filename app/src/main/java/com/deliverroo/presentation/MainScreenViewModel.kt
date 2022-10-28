package com.deliverroo.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deliverroo.domain.use_cases.customer.CustomerUseCases
import com.deliverroo.domain.use_cases.delivery.DeliveryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val deliveryUseCases: DeliveryUseCases,
    private val customerUseCases: CustomerUseCases
): ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.DeleteDelivery -> {
                viewModelScope.launch {
                    deliveryUseCases.deleteDelivery(event.delivery)
                }
            }
        }
    }
}