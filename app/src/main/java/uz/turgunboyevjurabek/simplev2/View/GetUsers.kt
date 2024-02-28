@file:OptIn(ExperimentalFoundationApi::class)

import android.content.Context
import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
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
//    var list by remember {
//        mutableStateOf(listOf(myDataBase.getAllUser()))
//    }

    LazyColumn {
        items(userList.size) { index ->
            val user = userList[index]
            val animateUser = remember { mutableStateOf(false) }
            /*
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
             }
             */
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .combinedClickable(
                            onLongClick = {
                                Toast
                                    .makeText(context, "O'chirildi", Toast.LENGTH_SHORT)
                                    .show()
                                myDataBase.deleteUser(user)

                            },
                            onClick = {})
                        ,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = user.name ?: "Unknown",Modifier.padding(5.dp))
                    Text(text = user.lastName ?: "Unknown",Modifier.padding(5.dp))
                    Text(text = user.number ?: "Unknown",Modifier.padding(5.dp))
                }
            }
        }

    }
}
