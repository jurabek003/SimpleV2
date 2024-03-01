package uz.turgunboyevjurabek.simplev2.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.turgunboyevjurabek.simplev2.View.viewScreen.EditClass
import uz.turgunboyevjurabek.simplev2.View.viewScreen.MainClass

@Composable
fun Navigation() {
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainClass.rout){

        composable(route=Screens.MainClass.rout){
            MainClass(navController = navController)
        }
        composable(route=Screens.EditClass.rout){
            EditClass(navController=navController)
        }

    }
}