package com.example.pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class NotificationActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Toast show", Toast.LENGTH_SHORT).show()
    }
}