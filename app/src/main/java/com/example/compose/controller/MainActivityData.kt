import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.compose.view.MainActivity2
import com.example.compose.view.MainActivity3
import com.example.compose.view.MainActivity4

@Composable
fun layoutData(){

    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CardView(title = "Tree", subtitle = "Apple", onClick = {
                    context.startActivity(Intent(context, MainActivity2::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Animal", subtitle = "Ape", onClick = {
                    context.startActivity(Intent(context, MainActivity3::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Sea", subtitle = "Pacific Ocean", onClick = {
                    context.startActivity(Intent(context, MainActivity4::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "River", subtitle = "Nil", onClick = {

                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Bike", subtitle = "Bajaj", onClick = {

                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Watch", subtitle = "Casio", onClick = {

                })
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Composable
fun CardView(title:String, subtitle:String, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
            .clickable { onClick.invoke() },
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(android.graphics.Color.parseColor("#EDEDED")))
                .border(2.dp, Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center


        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

