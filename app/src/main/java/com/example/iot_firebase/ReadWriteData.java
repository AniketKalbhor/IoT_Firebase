package com.example.iot_firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadWriteData {
    FirebaseDatabase db;
    DatabaseReference myRef;
    public void ReadStatus() {
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("status");
        myRef.child("LOCK").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
