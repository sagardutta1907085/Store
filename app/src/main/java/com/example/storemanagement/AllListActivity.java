package com.example.storemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllListActivity extends AppCompatActivity {


    private Button listcus,listitem,listemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        listcus=(Button) findViewById(R.id.showcustomerlist);
        listitem=(Button) findViewById(R.id.listitm);
        listemp=(Button) findViewById(R.id.showemployeelist);

        listcus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AllListActivity.this,CustomerActivity.class);
                startActivity(intent);
            }
        });
        listitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AllListActivity.this,ItemlistActivity.class);
                startActivity(intent);
            }
        });
        listemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AllListActivity.this,PaymentsActivity.class);
                startActivity(intent);
            }
        });

    }
}