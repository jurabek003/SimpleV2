package uz.turgunboyevjurabek.simplev2.View

import android.content.Context
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.turgunboyevjurabek.simplev2.Model.db.MyDataBase
import uz.turgunboyevjurabek.simplev2.Model.madels.User

@Composable
fun GetUsers(context: Context) {
    val myDataBase=MyDataBase.getInstance(context).roomInstens()

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