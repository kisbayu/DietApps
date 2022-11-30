package com.example.dietapps.ui.consumption_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.dietapps.data.Consumption
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConsumptionItem(
    consumption: Consumption,
    onEvent: (ConsumptionListEvent)->Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = consumption.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(ConsumptionListEvent.OnDeleteConsumptionClick(consumption))
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            consumption.description?.let { 
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }
        }
    }
}