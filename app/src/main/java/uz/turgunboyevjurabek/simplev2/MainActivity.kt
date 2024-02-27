package uz.turgunboyevjurabek.simplev2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.simplev2.Model.db.MyDataBase
import uz.turgunboyevjurabek.simplev2.Model.madels.User
import uz.turgunboyevjurabek.simplev2.ui.theme.SimpleV2Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleV2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val myDataBase=MyDataBase.getInstance(this).roomInstens()


                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(80.dp)
                        ,
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    val user=User(0,"Jurabek","Turg'unboyev","903654746")
                                    myDataBase.insetUser(user)
                            },
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "add user")
                            }
                        },
                        content = {

                            if (myDataBase.getAllUser().isNotEmpty()){
                                val list by remember{
                                    mutableStateOf(ArrayList<User>())
                                }
                                list.addAll(myDataBase.getAllUser())

                                LazyColumn{
                                    items(list.size){it->
                                        Card(modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                            .padding(10.dp)
                                        ) {
                                            Column(
                                                Modifier
                                                    .fillMaxSize()
                                                    .padding(5.dp),
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Row {
                                                    Text(text = list[it].name.toString())
                                                    Spacer(modifier = Modifier.size(10.dp))
                                                    Text(text = list[it].lastName.toString())
                                                }
                                                Text(text = list[it].number.toString())
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    )
                }
            }
        }
    }
}
