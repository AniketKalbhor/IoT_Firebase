package com.example.iot_firebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Cam extends AppCompatActivity {
    ImageView cam;
    Button refresh, capture;
    ProgressBar load;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        refresh = findViewById(R.id.Btn);
        capture = findViewById(R.id.newCapture);
        cam = findViewById(R.id.imageView);
        load = findViewById(R.id.loading);

        FirebaseDatabase db;
        DatabaseReference myRef;
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("status");
        load.setVisibility(View.VISIBLE);
        String imgStr = "https://firebasestorage.googleapis.com/v0/b/smart-doorbell-d2b71.appspot.com/o/data%2Fphoto.jpg?alt=media&token=61ad0a9a-6954-45bd-8bbf-22a8cfa113ee";
        Picasso.get().load(imgStr).into(cam);
        load.setVisibility(View.INVISIBLE);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Capturing live photo",Toast.LENGTH_SHORT).show();
                myRef.child("requestphoto").setValue(1);
                load.setVisibility(View.INVISIBLE);Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                view.startAnimation(animation);
                String imgStr = "https://firebasestorage.googleapis.com/v0/b/smart-doorbell-d2b71.appspot.com/o/data%2Fphoto.jpg?alt=media&token=61ad0a9a-6954-45bd-8bbf-22a8cfa113ee";
                Picasso.get().load(imgStr).into(cam);
                load.setVisibility(View.INVISIBLE);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                view.startAnimation(animation);
                String imgStr = "https://firebasestorage.googleapis.com/v0/b/smart-doorbell-d2b71.appspot.com/o/data%2Fphoto.jpg?alt=media&token=61ad0a9a-6954-45bd-8bbf-22a8cfa113ee";
                Picasso.get().load(imgStr).into(cam);
                load.setVisibility(View.INVISIBLE);
            }
        });

    }
}