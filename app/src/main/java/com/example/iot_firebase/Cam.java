package com.example.iot_firebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Cam extends AppCompatActivity {
    ImageView cam;
    Button refresh;
    ProgressBar load;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        refresh = findViewById(R.id.Btn);
        cam = findViewById(R.id.imageView);
        load = findViewById(R.id.loading);

        FirebaseDatabase db;
        DatabaseReference myRef;
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("status");
        load.setVisibility(View.GONE);load.setVisibility(View.GONE);
        String imgStr = "https://firebasestorage.googleapis.com/v0/b/iot-doorbell1.appspot.com/o/data%2Fphoto.jpg?alt=media&token=e9b7edd7-a411-468d-9e61-eb4796484869";
        Picasso.get().load(imgStr).into(cam);
        load.setVisibility(View.GONE);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            load.setVisibility(View.GONE);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
            view.startAnimation(animation);
            String imgStr = "https://firebasestorage.googleapis.com/v0/b/iot-doorbell1.appspot.com/o/data%2Fphoto.jpg?alt=media&token=e9b7edd7-a411-468d-9e61-eb4796484869";
            Picasso.get().load(imgStr).into(cam);
            }
        });

    }
}