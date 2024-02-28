import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import uz.turgunboyevjurabek.simplev2.Model.db.MyDataBase
import uz.turgunboyevjurabek.simplev2.Model.madels.User

@Composable
fun GetUsers(userList: List<User>,context: Context) {
    val myDataBase=MyDataBase.getInstance(context).roomInstens()
    val list=myDataBase.getAllUser()
    LazyColumn {
        items(userList.size) { index ->
            val user = userList[index]
            val animateUser = remember { mutableStateOf(false) }

                LaunchedEffect(user.id) {
                    animateUser.value = true
                }

            AnimatedVisibility(
                visible = animateUser.value,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = user.name ?: "Unknown")
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = user.lastName ?: "Unknown")
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = user.number ?: "Unknown")
                    }
                }
            }
        }
    }
}
