package com.example.dell.indianmistry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class schedule_service extends AppCompatActivity {


    ImageButton save;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    EditText problem_desc ,date, time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_service);






        problem_desc=(EditText) findViewById(R.id.problem_desc);
        date=(EditText)findViewById(R.id.date);
        time=(EditText) findViewById(R.id.timing);
        save=(ImageButton)findViewById(R.id.saved) ;





        Intent intent = getIntent();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseAuth = FirebaseAuth.getInstance();
                user = firebaseAuth.getCurrentUser();

                database=FirebaseDatabase.getInstance();
                databaseReference=database.getReference("Schedule of Service");

                String problemdesc = problem_desc.getText().toString();
                String Date=date.getText().toString();
                String Time=time.getText().toString();





                    if(problemdesc.isEmpty() || Date.isEmpty() || Time.isEmpty())
                    {
                        Toast.makeText(schedule_service.this, "Information Invalid..."
                                , Toast.LENGTH_SHORT).show();

                    }else {
                           String id= databaseReference.push().getKey();
                        service_schedule schedule= new service_schedule(problemdesc,Date,Time);
                        databaseReference.child(id).setValue(schedule);
                        Toast.makeText(schedule_service.this, "Information Saved..."
                                , Toast.LENGTH_SHORT).show();
                    }
            }
        });








    }


}
