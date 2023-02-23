package com.example.storemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ImageButton i1,i2;
    private Button b1;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alert;
    private FirebaseAuth mfirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i1=(ImageButton)findViewById(R.id.creator);
        b1=(Button)findViewById(R.id.exit);
        i2=(ImageButton)findViewById(R.id.shop);
        mfirebase=FirebaseAuth.getInstance();


        i2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                open_admin();
            }
        });
        i1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                open_aboutus();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialogBuilder=new AlertDialog.Builder(MainActivity.this);
                //Button messege
                alertDialogBuilder.setTitle(R.string.Exit);
                //For question
                alertDialogBuilder.setMessage(R.string.Message_text);
                //for exit icon
                alertDialogBuilder.setIcon(R.drawable.iconexit);
                //for positive button
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Exit
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });

                alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"You have clicked on Cancel Button.", Toast.LENGTH_SHORT ).show();
                    }
                });
                alert=alertDialogBuilder.create();
                alert.show();

            }
        });
    }
    public void open_aboutus(){
        Intent intent=new Intent(MainActivity.this,aboutus.class);
        startActivity(intent);
    }
    public void open_admin(){
        FirebaseUser firebaseUser=mfirebase.getCurrentUser();
        if(firebaseUser==null) {
            Intent intent = new Intent(MainActivity.this, admin.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, Admin2.class);
            startActivity(intent);
        }

    }
}