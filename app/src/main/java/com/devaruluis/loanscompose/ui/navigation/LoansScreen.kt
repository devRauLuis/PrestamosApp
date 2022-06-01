package com.devaruluis.loanscompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.devaruluis.loanscompose.ui.HomeScreenBody
import com.devaruluis.loanscompose.ui.registries.OccupationRegistryBody
import com.devaruluis.loanscompose.ui.registries.PeopleRegistryBody

enum class LoansScreen(
    val icon: ImageVector,
    val body: @Composable ((String) -> Unit) -> Unit, val displayText: String = ""
) {
    Home(
        icon = Icons.Filled.Home,
        body = { HomeScreenBody() }, displayText = "Inicio"
    ),
    PeopleR(
        icon = Icons.Filled.Person,
        body = { PeopleRegistryBody() },
        displayText = "Registro Personas"
    ),
    OccupationR(
        icon = Icons.Filled.Build,
        body = { OccupationRegistryBody() }, displayText = "Registro Ocupaciones"
    );

    @Composable
    fun content(onScreenChange: (String) -> Unit) {
        body(onScreenChange)
    }


    companion object {
        fun fromRoute(route: String?): LoansScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                PeopleR.name -> PeopleR
                OccupationR.name -> OccupationR
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}