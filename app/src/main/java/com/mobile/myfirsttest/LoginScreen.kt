package com.mobile.myfirsttest

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.myfirsttest.ui.theme.MyFirstTestTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier, onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Column(modifier = modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "Login",
            style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)
        )
        OutlinedTextField(
            value = email,
            label = { Text("Email")},
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onValueChange = { email = it }
        )

        OutlinedTextField(
            value = password,
            label = { Text("Password")},
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onValueChange = { password = it }
        )

        Button(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp).fillMaxWidth().height(40.dp),
            onClick = {
                val savedEmail = preferenceManager.getString("email", "") ?: ""
                val savedPassword = preferenceManager.getString("password", "") ?: ""

                if (email.isNotBlank() && email == savedEmail && password == savedPassword) {
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, HomeActivity::class.java)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }) {
            Text("LOGIN")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Forgot password?",
                style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 15.sp),
                color = Color(context.resources.getColor(R.color.teal_200))
            )

            Text(
                text = "Register here",
                modifier = Modifier.clickable { onRegisterClick() },
                style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 15.sp),
                color = Color(context.resources.getColor(R.color.red))
            )
        }

    }

}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyFirstTestTheme {
        LoginScreen(onRegisterClick = {})
    }
}
