package uz.turgunboyevjurabek.simplev2.View.viewScreen

import android.annotation.SuppressLint
import android.app.LauncherActivity
import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.turgunboyevjurabek.simplev2.Model.db.MyDataBase
import uz.turgunboyevjurabek.simplev2.Model.madels.User
import uz.turgunboyevjurabek.simplev2.ui.theme.Purple80

@Composable
fun EditClass(
    navController: NavController,
    id: Int,
    name: String,
    lastName: String,
    number: String,
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val user = User(id, name, lastName, number)
        val myDataBase = MyDataBase.getInstance(context)
        EditUi(navController, user, myDataBase)
    }

}

@Preview(showSystemUi = true)
@Composable
fun ViewUiPrev() {
    //EditUi(navController = navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUi(navController: NavController, user: User, myDataBase: MyDataBase) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = White,
            actionIconContentColor = White,
            navigationIconContentColor = White
        ), title = {
            Text(
                text = "Edit User",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "back",
                )
            }
        })
    }, content = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val database = myDataBase.roomInstens()
            var name by remember {
                mutableStateOf(user.name)
            }
            var lastName by remember {
                mutableStateOf(user.lastName)
            }
            var number by remember {
                mutableStateOf(user.number)
            }
            val gradientColors = listOf(Color.Gray, Blue, Red)
            val brush = Brush.linearGradient(colors = gradientColors)

            OutlinedTextField(value = name.toString(),
                onValueChange = { name = it },
                Modifier.padding(vertical = 10.dp),
                textStyle = TextStyle(
                    brush = brush, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                ),
                label = { Text(text = "Name") })
            OutlinedTextField(value = lastName.toString(),
                onValueChange = { lastName = it },
                Modifier.padding(vertical = 10.dp),
                textStyle = TextStyle(
                    brush = brush, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                ),
                label = { Text(text = "LastName") })
            OutlinedTextField(
                value = number.toString(),
                onValueChange = { number = it },
                Modifier.padding(vertical = 10.dp),
                textStyle = TextStyle(
                    brush = brush,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                label = { Text(text = "Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            Button(modifier = Modifier
                .padding(10.dp),
                onClick = {
                    val user = User(user.id, name, lastName, number)
                    database.editUser(user)
                    navController.popBackStack()
                }
            ) {
                Text(text = "Save", fontWeight = FontWeight.ExtraBold, color = White)
            }

        }
    })

}