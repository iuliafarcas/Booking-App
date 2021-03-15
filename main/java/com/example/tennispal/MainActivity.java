package com.example.tennispal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FloatingActionButton fab, fab2, fab4;
    TextView info, infotext;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab2 = findViewById(R.id.fab2);
        fab4 = findViewById(R.id.fab4);

        info = findViewById(R.id.info);
        infotext = findViewById(R.id.infotext);


        fStore = FirebaseFirestore.getInstance();

        getData();

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), OpenPage.class));
                finish();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Calendar.class));
            }
        });
        
    }

    public void getData()
    {
        DocumentReference docRef = fStore.collection("Info").document("1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String s = p2(document.getData().toString());
                        //infotext.setText(document.getData().toString());
                        infotext.setText(s);
                    }
                }
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        getData();

    }

    public String p2(String data)
    {
        data = data.substring(1, data.length() - 1);
        String[] str = data.split(",");
        System.out.println(str[0]);
        System.out.println(str[1]);
        System.out.println(str[2]);
        String wh = "Working hours: " + str[1].substring(15, str[1].length()) + "\n";
        String price = "Price: " + str[0].substring(6, str[0].length()) + "\n";
        String contact = "Contact: " + str[2].substring(9, str[2].length()) + "\n";

        return (wh + price + contact + "Paying method: Card\nAll bookings are payed in advanced when a reservation is made.\nTo delete a booking contact the admin.");
    }

}