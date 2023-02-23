package com.example.storemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class sign_up extends AppCompatActivity {
    private FirebaseAuth mauth;
    private EditText eml,pas;
    private Button bt;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mauth=FirebaseAuth.getInstance();
        eml=(EditText) findViewById(R.id.emailid2);
        pas=(EditText) findViewById(R.id.passwordid2);
        bt=(Button) findViewById(R.id.button3);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

    }

    private void Register() {
        String user=eml.getText().toString().trim();
        String pass=pas.getText().toString().trim();
        if(user.isEmpty())
        {
            eml.setError("Email cannot be empty.");
        }
        else if(user.isEmpty())
        {
            pas.setError("Password cannot be empty.");
        }
        else
        {
            mauth.createUserWithEmailAndPassword(user,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(sign_up.this, "User register is Successfull.", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(sign_up.this, "Registration Failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            eml.setText("");
            pas.setText("");
        }
    }
}