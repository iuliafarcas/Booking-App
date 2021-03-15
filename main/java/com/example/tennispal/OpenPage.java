package com.example.tennispal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class OpenPage extends AppCompatActivity {

    Button reg, player, admin;
    FirebaseAuth fAuth;
    public static Activity a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = findViewById(R.id.register);
        player = findViewById(R.id.signP);
        admin = findViewById(R.id.signA);

        fAuth = FirebaseAuth.getInstance();
        a = this;

        if(fAuth.getCurrentUser() != null)
        {
            if(fAuth.getCurrentUser().getUid().equals("Ack6gFIwO5dwT8vpkmwozeCAezx1"))
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            else startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginPlayer.class));
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginAdmin.class));
            }
        });
    }
}