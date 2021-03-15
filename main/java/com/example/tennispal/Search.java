package com.example.tennispal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Search extends AppCompatActivity {

    EditText et;
    TextView textView;
    FloatingActionButton search;
    FirebaseFirestore fStore;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = findViewById(R.id.fabsearch);
        et = findViewById(R.id.searchtext);
        textView = findViewById(R.id.searchView);

        fStore = FirebaseFirestore.getInstance();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et.getText().toString();
                System.out.println(s);
                fStore.collection("Users")
                        .whereEqualTo("fam_name", s)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //Log.d(TAG, document.getId() + " => " + document.getData());
                                        //System.out.println(document.getData());
                                        String s = parser2(document.getData().toString());
                                        data += (s + "\n");
                                        textView.setText(data);
                                    }
                                } else {
                                    //Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });

                //System.out.println(data);

            }
        });
    }


    public String parser2(String data)
    {
        data = data.substring(1, data.length() - 1);
        String[] s = data.split(",");
        String fname = s[4].substring(10, s[4].length());
        String first_name = s[5].substring(12, s[5].length());
        String pnb = s[3].substring(8, s[3].length());

        return (first_name + " " + fname + ": " + pnb);
    }

}