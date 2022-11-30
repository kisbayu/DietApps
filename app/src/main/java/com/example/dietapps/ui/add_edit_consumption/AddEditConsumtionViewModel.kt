package com.example.dietapps.ui.add_edit_consumption

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dietapps.data.Consumption
import com.example.dietapps.data.ConsumptionRepository
import com.example.dietapps.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditConsumtionViewModel @Inject constructor(
    private val repository: ConsumptionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    var consumption by mutableStateOf<Consumption?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val consumptionId = savedStateHandle.get<Int>("consumptionId")!!
        if(consumptionId != -1){
            viewModelScope.launch {
                repository.getConsumptionById(consumptionId)?.let {
                    consumption ->
                    title = consumption.title
                    description = consumption.description?:""
                    this@AddEditConsumtionViewModel.consumption=consumption
                }
            }
        }
    }

    fun onEvent(event: AddEditConsumptionEvent){
        when(event){
            is AddEditConsumptionEvent.OnTitleChange->{
                title = event.title
            }
            is AddEditConsumptionEvent.OnDescriptionChange->{
                description = event.description
            }
            is AddEditConsumptionEvent.OnSaveConsumptionClick->{
                viewModelScope.launch {
                    if(title.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "Please Fill Title"
                        ))
                        return@launch
                    }
                    repository.insertConsumption(
                        Consumption(
                            title = title,
                            description = description,
                            id=consumption?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}