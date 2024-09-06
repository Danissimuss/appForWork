package com.example.hhtestus.validation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.Navigation.responceAnsw
import com.example.hhtestus.downPanel.ApiSide.RetrofitClient
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.DarkBlue
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01
import com.example.hhtestus.ui.theme.numb

@Composable
fun codeDetector(viewModel: SharedViewModel, navController:NavController){

    var code by remember { mutableStateOf("") }
    val focusRequesters = remember { List(4) { FocusRequester() } }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val responceAnsw = remember {
        responceAnsw( RetrofitClient.apiService, viewModel, onError = { message ->
            isLoading = false
            errorMessage = message
        }, onSuccess = {
            navController.navigate("homeScreen")
        })
    }

    val allfilledfields = code.length == 4

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(4) { index ->
            OutlinedTextField(
                value = if (code.length > index) code[index].toString() else "",
                onValueChange = { input ->

                        handleTextInput(input, index, code, focusRequesters) { updatedCode ->
                            code = updatedCode
                        }

                },
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .focusRequester(focusRequesters[index]),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DarkGrey02,
                    unfocusedContainerColor = DarkGrey02,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = White01
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = numb,
                singleLine = true,
                placeholder = { Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "*",
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center)
                }
                }
            )
        }
    }

    Button(
        onClick = {

            if (allfilledfields) {
                responceAnsw.fetchOff()
            }

        },
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(end = 16.dp, start = 16.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (allfilledfields) Blue else DarkBlue,
            contentColor = White01
        )
    ) {
        Text(text = "Подтвердить", style = Standart)
    }

}