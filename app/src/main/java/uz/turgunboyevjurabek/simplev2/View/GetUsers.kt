@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
import android.content.Context
import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import uz.turgunboyevjurabek.simplev2.Model.db.MyDataBase
import uz.turgunboyevjurabek.simplev2.Model.madels.User
@Composable
fun GetUsers(
    userList: ArrayList<User>,
    context: Context,
    delete: (item: User) -> Unit,
    onClickNav: (item:User)->Unit
    ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = userList, key = { it.id }) { index ->
            SwipeToDeleteCointainer(item = index, onDelete = {
                userList -= index
                delete(index)
            })
            {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(5.dp)
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onClickNav(index)
                        }) {
                        Text(text = index.name.toString())
                        Text(text = index.lastName.toString())
                    }
                }
            }

        }
    }
}


@Composable
fun <T> SwipeToDeleteCointainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 1000,
    content: @Composable (T) -> Unit,
) {

    var isRemoved by remember {
        mutableStateOf(false)
    }

    val state = rememberDismissState(confirmValueChange = { value ->
        if (value == DismissValue.DismissedToStart) {
            isRemoved = true
            true
        } else {
            false
        }
    })
    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }
    AnimatedVisibility(
        visible = !isRemoved, exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration), shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(state = state,
            background = { DeleteBacround(swipeDismissState = state) },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}
@Composable
fun DeleteBacround(
    swipeDismissState: DismissState,
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Transparent
    } else {
        Color.Transparent
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .size(30.dp)
        )
    }

}
