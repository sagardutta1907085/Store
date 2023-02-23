package com.example.storemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Admin2 extends AppCompatActivity {

    private ImageButton customer,items,employees;
    private FirebaseAuth mauth;
    private Button so;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);
        customer=(ImageButton) findViewById(R.id.customer);
        items=(ImageButton) findViewById(R.id.items);
        employees=(ImageButton) findViewById(R.id.employees);
        so=(Button)findViewById(R.id.logout);
        mauth=FirebaseAuth.getInstance();

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin2.this,AddCustomerActivity.class);
                startActivity(intent);
            }
        });

        so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mauth.signOut();
                Intent intent=new Intent(Admin2.this,admin.class);
                startActivity(intent);
            }
        });
        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin2.this,AddItemActivity.class);
                startActivity(intent);
            }
        });
        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin2.this,AddEmployeeActivity.class);
                startActivity(intent);
            }
        });




    }
}