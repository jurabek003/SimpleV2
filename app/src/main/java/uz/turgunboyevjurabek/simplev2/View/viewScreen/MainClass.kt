@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.simplev2.View.viewScreen

import GetUsers
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.simplev2.Model.db.MyDataBase
import uz.turgunboyevjurabek.simplev2.Model.madels.User
import uz.turgunboyevjurabek.simplev2.View.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainClass(navController: NavController) {

    val myDataBase = MyDataBase.getInstance(LocalContext.current).roomInstens()
    val userListState = remember { mutableStateOf<List<User>>(emptyList()) }

    LaunchedEffect(Unit) {
        userListState.value = myDataBase.getAllUser()
    }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors =TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        text = "Home page",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    showBottomSheet = true
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add, contentDescription = "add user"
                )
            }
        }) {innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val context = LocalContext.current
            val list = ArrayList<User>()
            list.addAll(userListState.value)
            GetUsers(list, context, delete = { item ->
                myDataBase.deleteUser(item)
                userListState.value = myDataBase.getAllUser()
            }, onClickNav = {
                navController.navigate("edit_class/id=${it.id}?name=${it.name}?lastName=${it.lastName}?number=${it.number}")
            })

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    tonalElevation = 20.dp

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        var name by remember {
                            mutableStateOf("")
                        }
                        var lastName by remember {
                            mutableStateOf("")
                        }
                        var number by remember {
                            mutableStateOf("")
                        }
                        val gradientColors = listOf(Color.Gray, Color.Blue, Color.Red)
                        val brush = Brush.linearGradient(colors = gradientColors)

                        OutlinedTextField(
                            value = name.toString(),
                            onValueChange = { name = it },
                            Modifier.padding(vertical = 10.dp),
                            textStyle = TextStyle(
                                brush = brush, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                            ),
                            label = { Text(text = "Name") },
                            placeholder = { Text(text = "Please enter your name :)") })

                        OutlinedTextField(
                            value = lastName.toString(),
                            onValueChange = { lastName = it },
                            Modifier.padding(vertical = 10.dp),
                            textStyle = TextStyle(
                                brush = brush, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                            ),
                            label = { Text(text = "LastName") },
                            placeholder = { Text(text = "Please enter your lastName :)") })

                        OutlinedTextField(
                            value = number.toString(),
                            onValueChange = { number = it },
                            Modifier.padding(vertical = 10.dp),
                            textStyle = TextStyle(
                                brush = brush, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                            ),
                            label = { Text(text = "Number") },
                            placeholder = { Text(text = "Please enter your Number :)") })

                        Spacer(modifier = Modifier.height(40.dp))
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .height(50.dp),
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                        val user = User(0, name, lastName, number)
                                        myDataBase.insetUser(user)
                                        userListState.value = myDataBase.getAllUser()
                                    }
                                }
                            }) {
                            Text(
                                text = "Save item in room and list",
                                color = Color.Unspecified,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                        }

                    }

                }
            }
        }

    }
}