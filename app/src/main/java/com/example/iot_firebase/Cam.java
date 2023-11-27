package com.example.iot_firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Cam extends AppCompatActivity {
    ImageView cam;
    Button refresh, capture;
    ProgressBar load;
    TextView time;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        refresh = findViewById(R.id.Btn);
        capture = findViewById(R.id.newCapture);
        cam = findViewById(R.id.imageView);
        load = findViewById(R.id.loading);
        time = findViewById(R.id.timestamp);

        FirebaseDatabase db;
        DatabaseReference myRef;
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("status");
        load.setVisibility(View.VISIBLE);
        String imgStr = "https://firebasestorage.googleapis.com/v0/b/iot-firebase-cc75c.appspot.com/o/data%2Fphoto.jpg?alt=media&token=81e2f323-b52a-4e21-9b5f-3cd27a6d12c9";
        Picasso.get().load(imgStr).into(cam);
        load.setVisibility(View.INVISIBLE);
        Intent cam = getIntent();
        String timestamp = cam.getStringExtra("stamp");
        if(timestamp != null)
            {
                time.setText(timestamp);
            }

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Capturing live photo",Toast.LENGTH_SHORT).show();
                myRef.child("requestphoto").setValue(1);
                load.setVisibility(View.INVISIBLE);Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                view.startAnimation(animation);
                String imgStr = "https://firebasestorage.googleapis.com/v0/b/iot-firebase-cc75c.appspot.com/o/data%2Fphoto.jpg?alt=media&token=81e2f323-b52a-4e21-9b5f-3cd27a6d12c9";
                Picasso.get().load(imgStr).into((Target) cam);
                load.setVisibility(View.INVISIBLE);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                view.startAnimation(animation);
                String imgStr = "https://firebasestorage.googleapis.com/v0/b/iot-firebase-cc75c.appspot.com/o/data%2Fphoto.jpg?alt=media&token=81e2f323-b52a-4e21-9b5f-3cd27a6d12c9";
                Picasso.get().load(imgStr).into((Target) cam);
                load.setVisibility(View.INVISIBLE);
            }
        });

    }
}