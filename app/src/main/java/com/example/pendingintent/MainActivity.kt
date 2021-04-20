package com.example.pendingintent


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val builder1 = getNotificationBuilder("pending", "pending Intent")
            builder1.setContentTitle("notification1")
            builder1.setContentText("내용")
            builder1.setSmallIcon(R.mipmap.ic_launcher)

            // 1. notification 터치시 실행할 Activity를 관리할 Intent 생성
            val intent1 = Intent(this, NotificationActivity1::class.java)

            // 2. PendingIntent로 어떤 액티비티를 실행할지 지정해준다
            val pending1 = PendingIntent.getActivity(this, 10, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

            // 3. 클릭시 pendingActivity 실시하게 설정
            builder1.setContentIntent(pending1)


            val notification = builder1.build()
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }


        button2.setOnClickListener {
            val builder1 = getNotificationBuilder("pending", "pending Intent")
            builder1.setContentTitle("notification2")
            builder1.setContentText("내용2")
            builder1.setSmallIcon(R.mipmap.ic_launcher)

            // notification 터치시 실행할 Activity를 관리할 Intent 생성
            val intent1 = Intent(this, NotificationActivity2::class.java)
            // 액티비티로 데이터 전달
            intent1.putExtra("data1", 100)

            // PendingIntent로 어떤 액티비티를 실행할지 지정해준다
            val pending1 = PendingIntent.getActivity(this, 10,
                    intent1, PendingIntent.FLAG_UPDATE_CURRENT)

            // 클릭시 pendingActivity 실시하게 설정
            builder1.setContentIntent(pending1)

            // notification 을 클릭하면 자동으로 notification 이 사라짐
            builder1.setAutoCancel(true)

            // Action1 설정
            val intent2 = Intent(this, Receiver::class.java)
            intent2.putExtra("action", "action1")
            val pending2 = PendingIntent.getBroadcast(this, 100, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder2 = NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Action1", pending2)
            val action2 = builder2.build()

            builder1.addAction(action2)

            // Action2 설정
            val intent3 = Intent(this, Receiver::class.java)
            intent3.putExtra("action", "action2")
            val pending3 = PendingIntent.getBroadcast(this, 200, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder3 = NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Action2", pending3)
            val action3 = builder3.build()

            builder1.addAction(action3)

            val notification = builder1.build()
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification)
        }
    }

    fun getNotificationBuilder(id: String, name: String) : NotificationCompat.Builder{
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