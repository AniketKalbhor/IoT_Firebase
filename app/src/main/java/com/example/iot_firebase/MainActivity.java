package com.example.iot_firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notif = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notif = new Notification.Builder(this)
                    .setContentText("Motion Alert!")
                    .setSubText("PIR sensor has detected a motion")
                    .setChannelId("Alerts")
                    .build();
            nm.createNotificationChannel(new NotificationChannel("Alerts", "PIR", NotificationManager.IMPORTANCE_DEFAULT));
        }
        else {
            notif = new Notification.Builder(this)
                    .setContentText("Motion Alert!")
                    .setSubText("PIR sensor has detected a motion")
                    .build();
        }

        myRef.child("LOCK").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int flag = snapshot.getValue(int.class);
                    if(flag == 11){
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

        Notification finalNotif = notif;
        myRef.child("PIR").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int flag = snapshot.getValue(int.class);
                    if(flag == 1){
//                        finalNotif.notify();
                        Toast.makeText(getApplicationContext(),"Motion Detected",Toast.LENGTH_SHORT).show();
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
                    myRef.child("LOCK").setValue(11);
                } else {
                    Toast.makeText(getApplicationContext(),"Door Unlocked",Toast.LENGTH_SHORT).show();
                    myRef.child("LOCK").setValue(10);
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