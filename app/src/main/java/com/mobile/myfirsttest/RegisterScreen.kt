package com.mobile.myfirsttest

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.myfirsttest.ui.theme.MyFirstTestTheme


@Composable
fun RegisterScreen(modifier: Modifier = Modifier, onBack: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    BackHandler {
        onBack()
    }

    Column(
        modifier = modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = username,
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onValueChange = { username = it }
        )

        OutlinedTextField(
            value = email,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onValueChange = { email = it }
        )

        OutlinedTextField(
            value = password,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onValueChange = { password = it }
        )

        Button(
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                .fillMaxWidth()
                .height(40.dp),
            onClick = {
                preferenceManager.putString("username", username)
                preferenceManager.putString("email", email)
                preferenceManager.putString("password", password)
                Toast.makeText(context, "Saved Successfully to SharedPreferences", Toast.LENGTH_SHORT).show()
                onBack()
            }
        ) {
            Text("SAVE")
        }
    }
}
//Shared preference -> key value pair & Persist value over sessions.
//Intent values -> key value pair && Not persist value over sessions.
//SQ lite Database -> Relational database DB, tables, columns and work with SQL Queries as cloud database internally.

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MyFirstTestTheme {
        RegisterScreen(onBack = {})
    }
}