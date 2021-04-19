package com.example.pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification1.*
import kotlinx.android.synthetic.main.activity_notification2.*

class NotificationActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification2)

        // 6. 받은 데이터를 추출
        val data1 = intent.getIntExtra("data1", 0)

        // 7. 데이터를 텍스트뷰에 표시
        textView2.text = data1.toString()
    }
}