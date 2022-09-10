package com.example.fdcapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    EditText FirstName;
    EditText PhoneNumber;
    EditText Nin;
    Button Add;


    DatabaseReference userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("FCD Application");


        FirstName=(EditText)findViewById(R.id.et_first_name);
        PhoneNumber=(EditText)findViewById(R.id.et_phone_number);
        Nin=(EditText)findViewById(R.id.et_nin);
        Add=(Button) findViewById(R.id.btn_add);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AddDetails();
            }
        });




    }

    private void AddDetails(){
        String firstName=FirstName.getText().toString();
        String phoneNumber=PhoneNumber.getText().toString();
        String nin=Nin.getText().toString();


        DataModel dataModel = new DataModel(firstName,phoneNumber,nin);

        userDetails= FirebaseDatabase.getInstance().getReference("Details");

            userDetails.child(firstName).push().setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Data Entered Successfully", Toast.LENGTH_SHORT).show();
                        FirstName.setText("");
                        PhoneNumber.setText("");
                        Nin.setText("");
                    } else{
                        Toast.makeText(MainActivity.this, "Error, Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }
