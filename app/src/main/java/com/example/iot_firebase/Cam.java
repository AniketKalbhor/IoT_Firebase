package com.example.iot_firebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Cam extends AppCompatActivity {
    ImageView cam;
    Button refresh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        refresh = findViewById(R.id.Btn);
        cam = findViewById(R.id.imageView);
        FirebaseDatabase db;
        DatabaseReference myRef;
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("status");
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String imgStr = "https://firebasestorage.googleapis.com/v0/b/iot-doorbell1.appspot.com/o/data%2Fphoto.jpg?alt=media&token=e9b7edd7-a411-468d-9e61-eb4796484869";
            Picasso.get().load(imgStr).into(cam);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
            view.startAnimation(animation);
            }
        });

    }
}