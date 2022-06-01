package com.devaruluis.prestamoscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devaruluis.loanscompose.ui.navigation.LoansScreen
import com.devaruluis.loanscompose.ui.theme.LoansComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoansComposeTheme {
                val allScreens = LoansScreen.values().toList()
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentScreen = LoansScreen.fromRoute(backStackEntry.value?.destination?.route)

//                Scaffold(
//                    topBar = {
//                        RallyTabRow(
//                            allScreens = allScreens,
//                            onTabSelected = { screen -> currentScreen = screen },
//                            currentScreen = currentScreen
//                        )
//                    }
//                ) { innerPadding ->
//                    Box(Modifier.padding(innerPadding)) {
//                        currentScreen.content(
//                            onScreenChange = { screen ->
//                                currentScreen = RallyScreen.valueOf(screen)
//                            }
//                        )
//                    }
//                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    PrestamosComposeTheme {
////        Greeting("Android")
//    }
//}