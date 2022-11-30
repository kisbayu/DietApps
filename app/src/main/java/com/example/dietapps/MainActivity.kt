package com.example.dietapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dietapps.ui.add_edit_consumption.AddEditConsumption
import com.example.dietapps.ui.consumption_list.ConsumptionListUI
import com.example.dietapps.ui.theme.DietAppsTheme
import com.example.dietapps.util.Routes
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DietAppsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.CONSUMPTION_LIST )
                    {
                    composable(Routes.CONSUMPTION_LIST){
                        ConsumptionListUI(onNavigate =
                            {navController.navigate(it.route)})
                    }
                    composable(
                        route = Routes.ADD_EDIT_CONSUMPTION+"?consumptionId={consumptionId}",
                        arguments = listOf(
                            navArgument(name = "consumptionId"){
                                type= NavType.IntType
                                defaultValue=-1
                            }
                        )
                    ){
                        AddEditConsumption(onPopBackStack = {
                            navController.popBackStack()
                        })
                    }
                }

            }
        }
    }
}





