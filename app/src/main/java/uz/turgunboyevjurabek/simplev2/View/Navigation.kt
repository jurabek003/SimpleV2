package uz.turgunboyevjurabek.simplev2.View

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uz.turgunboyevjurabek.simplev2.View.viewScreen.EditClass
import uz.turgunboyevjurabek.simplev2.View.viewScreen.MainClass

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainClass.rout) {

        composable(route = Screens.MainClass.rout,
            arguments = listOf(
                navArgument(Screens.MainClass.rout) {
                    type = NavType.StringType
                },
            )) {
            MainClass(navController = navController)
        }
        composable(
            route = Screens.EditClass.rout,
            arguments = listOf(
                navArgument(Screens.EditClass.rout) {
                    type = NavType.StringType
                },
            )
        ) {
            EditClass(navController = navController)
        }

    }
}