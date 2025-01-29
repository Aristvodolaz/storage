package com.komus.storage.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.komus.storage.R
import com.komus.storage.ui.button.MenuButton

@Composable
fun TypeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Верхняя часть с информацией о пользователе и статусе
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Индикатор статуса
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.Green, shape = RoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Имя пользователя
            Text(
                text = "Журавлев А. И.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Логотип
            Image(
                painter = painterResource(id = R.drawable.logo), // Ваш логотип
                contentDescription = "Logo",
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопки
        MenuButton("Перемещение", "1") {
            // Действие при нажатии
        }
        Spacer(modifier = Modifier.height(8.dp))

        MenuButton("Данные о товаре", "2") {
            // Действие при нажатии
        }
        Spacer(modifier = Modifier.height(8.dp))

        MenuButton("Инвентаризация", "3") {
            // Действие при нажатии
        }
        Spacer(modifier = Modifier.height(8.dp))

        MenuButton("Размещение кондиции", "4") {
            navController.navigate("placement") // Переход на PlacementScreen
        }
    }
}
