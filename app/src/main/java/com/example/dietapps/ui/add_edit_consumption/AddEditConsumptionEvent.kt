package com.example.dietapps.ui.add_edit_consumption

sealed class AddEditConsumptionEvent{
    data class OnTitleChange(val title:String):AddEditConsumptionEvent()
    data class OnDescriptionChange(val description:String):AddEditConsumptionEvent()
    object OnSaveConsumptionClick: AddEditConsumptionEvent()
}
