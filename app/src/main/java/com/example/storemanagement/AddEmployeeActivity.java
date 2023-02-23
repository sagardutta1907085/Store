package com.example.storemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEmployeeActivity extends AppCompatActivity {

    Button employeeadd,viewemployee;
    EditText employeeName, employeePhone, employeeSalary, employeeLeaves;
    FirebaseDatabase reff;
    DatabaseReference dreff;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        employeeName = findViewById(R.id.editemployeename);
        employeePhone = findViewById(R.id.editemployeephone);
        employeeSalary = findViewById(R.id.editemployeesalary);
        employeeLeaves= findViewById(R.id.editemployeeleaves);
       employeeadd = findViewById(R.id.addemployee);
       viewemployee=(Button)findViewById(R.id.viewemployee);


        reff = FirebaseDatabase.getInstance();
        dreff = reff.getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        viewemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddEmployeeActivity.this,PaymentsActivity.class);
                startActivity(intent);
            }
        });

        employeeadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employee_name = employeeName.getText().toString();
                String employee_phone=employeePhone.getText().toString();
                int employee_salary=Integer.parseInt(employeeSalary.getText().toString());
                int employee_leaves=Integer.parseInt(employeeLeaves.getText().toString());

                if(employee_name.isEmpty()){
                    Toast.makeText(AddEmployeeActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }if(employee_phone.isEmpty()){
                    Toast.makeText(AddEmployeeActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }if(Integer.toString(employee_salary).isEmpty()){
                    Toast.makeText(AddEmployeeActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }if(Integer.toString(employee_leaves).isEmpty()){
                    Toast.makeText(AddEmployeeActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Employee employee = new Employee(employee_name, employee_phone, employee_salary, employee_leaves);
                String id = currentUser.getUid();
                dreff.child("Users").child(id).child("Employees").child(employee_phone).setValue(employee);
                startActivity(new Intent(AddEmployeeActivity.this, PaymentsActivity.class));
                finish();
            }
        });
    }
}