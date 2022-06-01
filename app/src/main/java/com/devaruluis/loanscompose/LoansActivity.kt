package com.devaruluis.loanscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devaruluis.loanscompose.ui.LoansMenu
import com.devaruluis.loanscompose.ui.LoansTopBar
import com.devaruluis.loanscompose.ui.navigation.LoansNavHost
import com.devaruluis.loanscompose.ui.navigation.LoansScreen
import com.devaruluis.loanscompose.ui.theme.LoansComposeTheme
import kotlinx.coroutines.launch

class LoansActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoansApp()
        }
    }
}

@Composable
fun LoansApp() {
    LoansComposeTheme {
        // A surface container using the 'background' color from the theme
        val allScreens = LoansScreen.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        var currentScreen = LoansScreen.fromRoute(backStackEntry.value?.destination?.route)
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        val toggleDrawer = {
            scope.launch {
                scaffoldState.drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                LoansMenu(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        scope.launch {
                            navController.navigate(screen.name)
                            scaffoldState.drawerState.apply {
                                close()
                            }
                        }
                    },
                    currentScreen = currentScreen, closeMenu = {
                        toggleDrawer()
                    }
                )
            },
            topBar = {
                LoansTopBar(
                    onMenuClick = { toggleDrawer() }
                )
            },
        ) { innerPadding ->
            LoansNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
        SnackbarHost(hostState = snackbarHostState)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Surface() {
        LoansApp()
    }
}