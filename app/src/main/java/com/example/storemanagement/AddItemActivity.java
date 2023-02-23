package com.example.storemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity {

    EditText editTextItemName, editTextPrice, editTextQty;

    String itemId;
    Button btnAddItem,viewitem;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editTextItemName =(EditText) findViewById(R.id.edit_item_name);
        editTextPrice =(EditText) findViewById(R.id.edit_item_price);
        editTextQty =(EditText) findViewById(R.id.edit_item_quantity);
        btnAddItem =(Button) findViewById(R.id.btn_add_item);
        viewitem=(Button)findViewById(R.id.viewitems);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        viewitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddItemActivity.this,ItemlistActivity.class);
                startActivity(intent);
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String it_name = editTextItemName.getText().toString();
                int it_price=Integer.parseInt(editTextPrice.getText().toString());
                int it_qty=Integer.parseInt(editTextQty.getText().toString());



                Item item = new Item(it_name, it_price, it_qty);
                String id = currentUser.getUid();
                databaseReference.child("Users").child(id).child("Items").child(it_name).setValue(item);

                startActivity(new Intent(AddItemActivity.this, ItemlistActivity.class));
                finish();
            }
        });



    }
}