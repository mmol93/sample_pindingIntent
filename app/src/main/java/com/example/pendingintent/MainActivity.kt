package com.example.pendingintent

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val builder1 = getNotificationBuilder("pending", "pending Intent")
            builder1.setContentTitle("notification1")
            builder1.setContentText("내용")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)

            // 1. notification 터치시 실행할 Activity를 관리할 Intent 생성
            val intent1 = Intent(this, NotificationActivity1::class.java)

            // 2. PendingIntent로 어떤 액티비티를 실행할지 지정해준다
            val pending1 = PendingIntent.getActivity(this, 10, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

            // 3. 클릭시 pendingActivity 실시하게 설정
            builder1.setContentIntent(pending1)


            val notification = builder1.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

        button2.setOnClickListener {
            val builder1 = getNotificationBuilder("pending", "pending Intent")
            builder1.setContentTitle("notification2")
            builder1.setContentText("내용2")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)

            // 1. notification 터치시 실행할 Activity를 관리할 Intent 생성
            val intent1 = Intent(this, NotificationActivity2::class.java)
            // 5. 액티비티로 데이터 전달
            intent1.putExtra("data1", 100)

            // 2. PendingIntent로 어떤 액티비티를 실행할지 지정해준다
            val pending1 = PendingIntent.getActivity(this, 10,
                intent1, PendingIntent.FLAG_UPDATE_CURRENT)

            // 3. 클릭시 pendingActivity 실시하게 설정
            builder1.setContentIntent(pending1)

            // 4. notification 을 클릭하면 자동으로 notification 이 사라짐
            builder1.setAutoCancel(true)

            // 8. Action 설정
            val intent2 = Intent(this, NotificationActivity3::class.java)
            // 10. notification에서 새로운 액티비티를 여는 것운 무조건 PendingIntent 를 통해서 실행한다
            val pending2 = PendingIntent.getActivity(this, 100,
                intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            // 11. Notification 액션에 대한 정의를 한다
            val builder2 = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_compass,
                "Action1", pending2)
            // 12. Notification 액션 등록
            val action2 = builder2.build()
            // 13. Notification 액셜 실행
            builder1.addAction(action2)

            // 9. Action 설정
            val intent3 = Intent(this, NotificationActivity4::class.java)
            val pending3 = PendingIntent.getActivity(this, 100, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder3 = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_agenda, "Action2", pending3)
            val action3 = builder3.build()

            builder1.addAction(action3)

            val notification = builder1.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification)
        }
    }

    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val chanel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)

            chanel.enableLights(true)
            chanel.lightColor = Color.RED

            manager.createNotificationChannel(chanel)

            val builder1 = NotificationCompat.Builder(this, id)
            return builder1
        }else{
            val builder2 = NotificationCompat.Builder(this)
            return builder2
        }
    }
}