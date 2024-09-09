package com.example.hhtestus.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.LightGrey02
import com.example.hhtestus.ui.theme.LightGrey03
import com.example.hhtestus.ui.theme.Red
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01
import com.example.hhtestus.ui.theme.input

@Composable
fun EmailInputField(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true)}
    val isTextFieldNotEmpty = email.isNotEmpty()

    val context = LocalContext.current
    val imageLoader = imageLoader(context = context)


    CardContainer {

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

            Text(
                text = "Поиск работы",
                color = White01,
                style = Standart,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )

            Box(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(

                    value = email,
                    onValueChange = { email = it
                                    isEmailValid = true},

                    isError = !isEmailValid,

                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.White,
                        errorTextColor = Red,
                        focusedContainerColor = DarkGrey02,
                        unfocusedContainerColor = DarkGrey02,
                        disabledContainerColor = DarkGrey02,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Red,
                        cursorColor = White01
                    ),

                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    textStyle = input,
                    singleLine = true,

                    placeholder = {

                        if (email.isEmpty()) {

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                AsyncImage(

                                    model = ImageRequest.Builder(context)
                                        .data("android.resource://${context.packageName}/raw/response")
                                        .build(),

                                    contentDescription = "Email Icon",
                                    imageLoader = imageLoader,

                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 8.dp),

                                    contentScale = ContentScale.Fit
                                )

                                Text(
                                    text = "Электронная почта или телефон",
                                    color = LightGrey02,
                                    style = Standart.copy(textAlign = TextAlign.Start)
                                )
                            }
                        }
                    }
                )

                if (isTextFieldNotEmpty) {
                    IconButton(
                        onClick = { email = ""
                                  isEmailValid = true},

                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .background(Color.Transparent)
                            .graphicsLayer(
                                scaleX = 0.75f,
                                scaleY = 0.75f
                            )) {

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear text",
                            tint = LightGrey03
                        )

                    }
                }
            }

            if (!isEmailValid){

                Text(
                    text = "Неверный e-mail",
                    color = Red,
                    style = Standart,
                    modifier = Modifier.padding(top = 6.dp))
            }

            ActionButtons(
                email = email,
                onSubmit = {enteredEmail ->
                    if(isValidEmail(enteredEmail)){

                        navController.navigate("second/$email")

                    }else{
                        isEmailValid = false
                    }
                }
            )

        }

    }
}