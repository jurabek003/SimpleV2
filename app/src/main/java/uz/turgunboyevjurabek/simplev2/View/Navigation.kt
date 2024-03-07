package uz.turgunboyevjurabek.simplev2.View

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uz.turgunboyevjurabek.simplev2.Model.madels.User
import uz.turgunboyevjurabek.simplev2.View.viewScreen.EditClass
import uz.turgunboyevjurabek.simplev2.View.viewScreen.MainClass

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainClass.rout) {
        composable(
            route = Screens.MainClass.rout,
        ) {
            MainClass(navController = navController)
        }
        composable(
            route = "edit_class/id={id}?name={name}?lastName={lastName}?number={number}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("lastName") {
                    type = NavType.StringType
                },
                navArgument("number") {
                    type = NavType.StringType
                },
            )
        ) {backStackEntry ->

            val arguments = requireNotNull(backStackEntry.arguments)
            val id = arguments.getInt("id")
            val name = arguments.getString("name")
            val lastName = arguments.getString("lastName")
            val number = arguments.getString("number")
//            val user = User(id, name, lastName, number)

            EditClass(navController = navController,id=id,name= name.toString(),lastName= lastName.toString(),number= number.toString())
        }

    }
}