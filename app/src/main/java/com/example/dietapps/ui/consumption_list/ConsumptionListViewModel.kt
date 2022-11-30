package com.example.dietapps.ui.consumption_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dietapps.data.Consumption
import com.example.dietapps.data.ConsumptionRepository
import com.example.dietapps.util.Routes
import com.example.dietapps.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsumptionListViewModel @Inject constructor(
    private val repository: ConsumptionRepository
) : ViewModel(){

    val consumption = repository.getConsumption()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var deleteConsumption: Consumption? = null

    fun onEvent(event: ConsumptionListEvent){
        when(event){
            is ConsumptionListEvent.OnConsumptionClick->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_CONSUMPTION + "?todoId=${event.consumption.id}"))
            }
            is ConsumptionListEvent.OnAddConsumptionClick->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_CONSUMPTION))
            }
            is ConsumptionListEvent.OnDeleteConsumptionClick->{
                viewModelScope.launch {
                    deleteConsumption = event.consumption
                    repository.deleteConsumption(event.consumption)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Item Delete",
                        action= "undo"
                    ))
                }
            }
            is ConsumptionListEvent.OnUndoDeleteClick->{
                deleteConsumption?.let { consumption ->
                    viewModelScope.launch {
                        repository.insertConsumption(consumption)
                    }
                }
            }
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}