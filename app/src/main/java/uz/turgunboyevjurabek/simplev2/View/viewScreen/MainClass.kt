package uz.turgunboyevjurabek.simplev2.View.viewScreen

import GetUsers
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import uz.turgunboyevjurabek.simplev2.Model.db.MyDataBase
import uz.turgunboyevjurabek.simplev2.Model.madels.User
import uz.turgunboyevjurabek.simplev2.View.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainClass(navController: NavController) {

    val myDataBase = MyDataBase.getInstance(LocalContext.current).roomInstens()
    val userListState = remember {  mutableStateOf<List<User>>(emptyList()) }

    LaunchedEffect(Unit) {
        userListState.value= myDataBase.getAllUser()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(
            onClick = {
                val user = User(0, "Jo'rabek", "Turg'unboyev", "903654746")
                myDataBase.insetUser(user)
                userListState.value=myDataBase.getAllUser()
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Add, contentDescription = "add user"
            )
        }
    }, content = {
        val context = LocalContext.current
        val list = ArrayList<User>()
        list.addAll(userListState.value)
        GetUsers(
            list,
            context,
            delete = { item ->
                myDataBase.deleteUser(item)
                userListState.value = myDataBase.getAllUser()
            },
            onClickNav = {
                navController.navigate(Screens.EditClass.rout)
            }
        )
    })


}