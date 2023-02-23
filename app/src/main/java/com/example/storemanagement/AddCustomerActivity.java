package com.example.storemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCustomerActivity extends AppCompatActivity {

    Button AddCustomer,Viewlist;
    EditText CustomerName, CustomerPhone, CustomerDue;
    FirebaseDatabase reff;
    DatabaseReference dreff;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        CustomerDue =(EditText) findViewById(R.id.csdue);
        AddCustomer =(Button) findViewById(R.id.csadd);
        CustomerName =(EditText) findViewById(R.id.csname);
        CustomerPhone =(EditText) findViewById(R.id.csphone);
        Viewlist=(Button)findViewById(R.id.viewcustomerlist);


        reff = FirebaseDatabase.getInstance();
        dreff = reff.getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddCustomerActivity.this,CustomerActivity.class);
                startActivity(intent);
            }
        });

        AddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(CustomerName.getText().toString()))
                {
                    Toast.makeText(AddCustomerActivity.this, "Name must not be Empty.", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(CustomerPhone.getText().toString()))
                {
                    Toast.makeText(AddCustomerActivity.this, "Phone must not be Empty.", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(CustomerDue.getText().toString()))
                {
                    Toast.makeText(AddCustomerActivity.this, "Due must not be Empty.", Toast.LENGTH_SHORT).show();
                }






                else {

                    String customername = CustomerName.getText().toString();
                    String customerphone=CustomerPhone.getText().toString();
                    int customerdue=Integer.parseInt(CustomerDue.getText().toString());

                    String id = currentUser.getUid();
                    Customer customer = new Customer(customername, customerphone, customerdue);
                    dreff.child("Users").child(id).child("Customers").child(customerphone).setValue(customer);
                    startActivity(new Intent(AddCustomerActivity.this, CustomerActivity.class));
                    finish();
                }
            }
        });
    }
}