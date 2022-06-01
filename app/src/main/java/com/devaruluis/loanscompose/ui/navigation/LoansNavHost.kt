package com.devaruluis.loanscompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devaruluis.loanscompose.ui.HomeScreenBody
import com.devaruluis.loanscompose.ui.registries.OccupationRegistryBody
import com.devaruluis.loanscompose.ui.registries.PeopleRegistryBody

@Composable
fun LoansNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = LoansScreen.Home.name,
        modifier = modifier
    ) {
        composable(LoansScreen.Home.name) {
            HomeScreenBody()
        }
        composable(LoansScreen.PeopleR.name) {
            PeopleRegistryBody()
        }
        composable(LoansScreen.OccupationR.name) {
            OccupationRegistryBody()
        }
    }
}
