package com.example.iot_firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Switch Lock;
    Button next;
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = findViewById(R.id.button);
        Lock = findViewById(R.id.door);
        status = findViewById(R.id.status);
        FirebaseDatabase db;
        DatabaseReference myRef;
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("status");
        Intent notify = new Intent(getApplicationContext(), MainActivity.class);
        notify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(this, 100, notify, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.doorbell)
                .setChannelId("Alerts")
                .setContentText("Motion Alert!")
                .setContentIntent(pi)
                .setSubText("PIR sensor has detected a motion")
                .setChannelId("Alerts")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notif = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            notif = new Notification.Builder(this)
//                    .setContentText("Motion Alert!")
//                    .setSmallIcon(R.drawable.doorbell)
//                    .setChannelId("Alerts")
//                    .setSubText("PIR sensor has detected a motion")
//                    .build();
//            nm.createNotificationChannel(new NotificationChannel("Alerts", "Motion", NotificationManager.IMPORTANCE_DEFAULT));
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notif = new Notification.Builder(this)
//                    .setContentText("Motion Alert!")
//                    .setSmallIcon(R.drawable.doorbell)
//                    .setSubText("PIR sensor has detected a motion")
//                    .build();
//        }
//        Notification finalNotif = notif;
        myRef.child("PIR").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int flag = snapshot.getValue(int.class);
                    if(flag == 1){
//                        nm.notify(1, finalNotif);
                        nm.notify(100, nbuilder.build());
                        Toast.makeText(getApplicationContext(),"Motion Detected",Toast.LENGTH_SHORT).show();
                        myRef.child("PIR").setValue(0);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("LOCK").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int flag = snapshot.getValue(int.class);
                    if(flag == 1){
                        status.setText("Locked");
                    }
                    else{
                        status.setText("Unlocked");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Lock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // on below line we are checking
                // if switch is checked or not.
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),"Door Locked",Toast.LENGTH_SHORT).show();
                    myRef.child("LOCK").setValue(1);
                } else {
                    Toast.makeText(getApplicationContext(),"Door Unlocked",Toast.LENGTH_SHORT).show();
                    myRef.child("LOCK").setValue(0);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cam;
                cam = new Intent(MainActivity.this, Cam.class);
                startActivity(cam);
            }
        });
    }
    }