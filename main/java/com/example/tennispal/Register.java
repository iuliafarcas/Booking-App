package com.example.tennispal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText name, fam, email, password, phone, cardNb, cvc, date;
    Button register;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.nameReg);
        fam = findViewById(R.id.nameReg2);
        email = findViewById(R.id.emailReg);
        password = findViewById(R.id.pass);
        phone = findViewById(R.id.phonenb);
        cardNb = findViewById(R.id.cardnb);
        cvc = findViewById(R.id.cvc);
        date = findViewById(R.id.date);
        register = findViewById(R.id.regBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailS = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                String firstname = name.getText().toString();
                String famname = fam.getText().toString();
                String phonenb = phone.getText().toString();
                String card = cardNb.getText().toString();
                String data = date.getText().toString();
                String cvc1 = cvc.getText().toString();

                if(TextUtils.isEmpty(emailS))
                {
                    email.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(pass) || pass.equals("Ana are mere"))
                {
                    password.setError("Password is required");
                    return;
                }

                if(TextUtils.isEmpty(firstname) || firstname.equals("First Name"))
                {
                    name.setError("First name is required");
                    return;
                }

                if(TextUtils.isEmpty(famname) || famname.equals("Family Name"))
                {
                    fam.setError("Family name is required");
                    return;
                }

                if(TextUtils.isEmpty(phonenb) || phonenb.equals("Phone number"))
                {
                    phone.setError("Phone number is required");
                    return;
                }

                if(TextUtils.isEmpty(card) || card.equals("Card Number"))
                {
                    cardNb.setError("Card number is required");
                    return;
                }

                if(TextUtils.isEmpty(data) || data.equals("Expiration date"))
                {
                    date.setError("Expiration date is required");
                    return;
                }

                if(TextUtils.isEmpty(cvc1) || cvc1.equals("CVC"))
                {
                    cvc.setError("CVC is required");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(emailS, pass).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this,"Registration successful", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("first_name", firstname);
                            user.put("fam_name", famname);
                            user.put("email", emailS);
                            user.put("phoneNb", phonenb);
                            user.put("cardNb", card);
                            user.put("date", data);
                            user.put("cvc", cvc1);
                            documentReference.set(user);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            OpenPage.a.finish();
                            finish();
                        }
                        else Toast.makeText(Register.this,"Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}