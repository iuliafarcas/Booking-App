package com.example.tennispal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPlayer extends AppCompatActivity {

    EditText email, password;
    Button login;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_player);

        email = findViewById(R.id.emailPlayer);
        password = findViewById(R.id.passPlayer);
        login = findViewById(R.id.signinPlayer);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailS = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(TextUtils.isEmpty(emailS))
                {
                    email.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(pass))
                {
                    password.setError("Password is required");
                    return;
                }

                fAuth.signInWithEmailAndPassword(emailS, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginPlayer.this,"Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            OpenPage.a.finish();
                            finish();
                        }
                        else Toast.makeText(LoginPlayer.this,"Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}