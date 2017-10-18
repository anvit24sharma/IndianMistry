package com.example.dell.indianmistry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {

    public static  final String NAME="com.example.dell.indianmistry.name";
    FirebaseAuth firebaseAuth;
    private Button logout_button;
    private EditText name;
    private TextView email_id;

    private EditText phoneno;
    private Button   save;
    private ImageButton edit_button;
    Intent intent;
   public DatabaseReference databaseReference;
FirebaseDatabase database;
    public FirebaseUser user;
    public  String profile_name,phone_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        intent=getIntent();

        firebaseAuth =FirebaseAuth.getInstance();
       user = firebaseAuth.getCurrentUser();




       name = (EditText) findViewById(R.id.name);
        email_id = (TextView) findViewById(R.id.email_id_textview);
        phoneno = (EditText) findViewById(R.id.Phoneno_edittext);
        save =(Button) findViewById(R.id.save_button);
        edit_button=(ImageButton) findViewById(R.id.edit_button);



        name.setEnabled(false);
       phoneno.setEnabled(false);
        save.setEnabled(false);


       email_id.setText(user.getEmail());



         profile_name = name.getText().toString().trim();
         phone_no = phoneno.getText().toString().trim();
        intent.putExtra(NAME,profile_name);



        if(null == firebaseAuth.getCurrentUser())
        {
            finish();
            startActivity(new Intent(profile.this, LoginActivity.class));

        }



        logout_button = (Button) findViewById(R.id.logout_button);

        logout_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       firebaseAuth.signOut();
                        finish();
                        intent=new Intent(profile.this,LoginActivity.class);

                        startActivity(intent);
                    }
                }
        );
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveuserinfo();



            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(true);
                phoneno.setEnabled(true);
                save.setEnabled(true);

            }
        });
    }
    public void saveuserinfo()
    {
        String profile_name = name.getText().toString().trim();

        String phone_no = phoneno.getText().toString().trim();

        profile_objects po= new profile_objects(profile_name,phone_no);

        user = firebaseAuth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("Profile");

        String email= user.getEmail();


        if(profile_name.isEmpty() || phone_no.isEmpty())
        {
            Toast.makeText(profile.this, "Information Invalid...", Toast.LENGTH_SHORT).show();

        }else {

            String id= databaseReference.push().getKey();
             databaseReference.child(id).setValue(po);
            Toast.makeText(profile.this, "Information Saved...", Toast.LENGTH_SHORT).show();
            intent.putExtra(NAME,profile_name);
        }


        name.setText(profile_name);
        phoneno.setText(phone_no);
        save.setEnabled(false);
        name.setEnabled(false);
        phoneno.setEnabled(false);
    }
}
