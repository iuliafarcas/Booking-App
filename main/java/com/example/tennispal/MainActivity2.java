package com.example.tennispal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.api.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    FirebaseAuth fAuth;
    FloatingActionButton fab, fab2;
    Button add, delete, change, book;
    EditText hours, price, contact;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fab = findViewById(R.id.fab5);
        fab2 = findViewById(R.id.fabsearch2);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.del);
        change = findViewById(R.id.change);
        book = findViewById(R.id.book);
        hours =findViewById(R.id.hours);
        price = findViewById(R.id.price);
        contact = findViewById(R.id.contact);

        fStore = FirebaseFirestore.getInstance() ;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), OpenPage.class));
                finish();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = hours.getText().toString();
                String p = price.getText().toString();
                String c = contact.getText().toString();

                System.out.println(c);

                DocumentReference documentReference = fStore.collection("Info").document("1");

                if(!c.equals("Contact"))
                    documentReference.update("contact", c);
                if(!h.equals("Working Hours"));
                    documentReference.update("working_hours", h);
                if(!p.equals("Price"))
                    documentReference.update("price", p);

                Toast.makeText(MainActivity2.this,"Information change successful", Toast.LENGTH_SHORT).show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminCalendar.class));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DelBooking.class));
            }
        });
    }
}