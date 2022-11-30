package com.example.dietapps.ui.consumption_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dietapps.util.UiEvent



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ConsumptionListUI(
    onNavigate: (UiEvent.Navigate)->Unit,
    viewModel: ConsumptionListViewModel = hiltViewModel()
){
    val consumptions = viewModel.consumption.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect(){
            event->when(event){
                is UiEvent.ShowSnackbar->{
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action)
                }
                is UiEvent.Navigate->{
                    viewModel.onEvent(ConsumptionListEvent.OnUndoDeleteClick)
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ConsumptionListEvent.OnAddConsumptionClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        topBar = {
            TopAppBar() {
                Text(text = "Home")
            }
        }
    ) {
        Row() {
            Text(text = "Consumption")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(consumptions.value.size) { index ->
                ConsumptionItem(
                    consumption = consumptions.value[index],
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(ConsumptionListEvent.OnConsumptionClick(consumptions.value[index]))
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}

