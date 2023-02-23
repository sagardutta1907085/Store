package com.example.storemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin extends AppCompatActivity {

    private FirebaseAuth mauth;
    private EditText e1,e2;
    private Button b1;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        e1=(EditText) findViewById(R.id.usernameid);
        e2=(EditText) findViewById(R.id.passwordid);
        b1=(Button) findViewById(R.id.button);
        t=(TextView) findViewById(R.id.textsignup);
        mauth=FirebaseAuth.getInstance();
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin.this,sign_up.class);
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = e1.getText().toString();
                String pass = e2.getText().toString();
                if (user.isEmpty()) {
                    e1.setError("Email cannot be empty.");
                } else if (user.isEmpty()) {
                    e2.setError("Password cannot be empty.");
                } else {
                    mauth.signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(admin.this, "Log In successful.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(admin.this, Admin2.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(admin.this, "Log In failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

            });

    }


}