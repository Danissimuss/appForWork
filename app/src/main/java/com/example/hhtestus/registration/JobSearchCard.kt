import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hhtestus.ui.theme.DarkGrey01
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.Standart

@Preview
@Composable
fun JobSearchCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = DarkGrey01)
    ){
        Column(modifier =
        Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(
                text = "Поиск сатрудников",
                color = Color.White,
                style = Standart,
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                text = "Размещение вакансий и доступ к базе резюме",
                color = Color.White,
                fontSize = 14.sp,
                style = Standart,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp )
            )
            Button(onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green,
                    contentColor = Color.White)) {
                Text(text = "Я ищу сотрудников",
                    style = Standart)
            }
        }
    }

}