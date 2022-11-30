package com.example.dietapps.ui.consumption_list

import com.example.dietapps.data.Consumption

sealed class ConsumptionListEvent{
    data class OnDeleteConsumptionClick(val consumption: Consumption):ConsumptionListEvent()
    object OnUndoDeleteClick:ConsumptionListEvent()
    data class OnConsumptionClick(val consumption: Consumption):ConsumptionListEvent()
    object OnAddConsumptionClick:ConsumptionListEvent()
}
