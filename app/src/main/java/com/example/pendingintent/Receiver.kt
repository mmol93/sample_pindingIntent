package com.example.pendingintent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent!!.getStringExtra("action")
        if (action == "action1") {
            Toast.makeText(context, "토스트1 나옴", Toast.LENGTH_SHORT).show()
        } else if (action == "action2") {
            Toast.makeText(context, "토스트2 나옴", Toast.LENGTH_SHORT).show()
        }
    }
}