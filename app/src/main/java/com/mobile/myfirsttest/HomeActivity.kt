package com.mobile.myfirsttest

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mobile.myfirsttest.ui.theme.MyFirstTestTheme

class HomeActivity : ComponentActivity() {

    private val context: Context = this
    private val phoneStateReceiver = PhoneStateReceiver()

    companion object {
        private const val REQUEST_CODE_PHONE_STATE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Explicitly request READ_PHONE_STATE runtime permission
        requestPhoneStatePermission()

        setContent {
            MyFirstTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Toast.makeText(context, "Home Screen onCreate", Toast.LENGTH_SHORT).show()
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun requestPhoneStatePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                REQUEST_CODE_PHONE_STATE
            )
        }
    }

    @Suppress("DEPRECATION")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PHONE_STATE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("HomeActivity", "READ_PHONE_STATE permission granted")
                Toast.makeText(this, "Phone state permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("HomeActivity", "READ_PHONE_STATE permission denied")
                Toast.makeText(this, "Phone state permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v("HomeActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.v("HomeActivity", "onResume called - Registering PhoneStateReceiver")
        val filter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(phoneStateReceiver, filter, RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        Log.v("HomeActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.v("HomeActivity", "onStop called - Unregistering PhoneStateReceiver")
        try {
            unregisterReceiver(phoneStateReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("HomeActivity", "onRestart called")
    }
}
