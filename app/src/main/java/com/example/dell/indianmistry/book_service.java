package com.example.dell.indianmistry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class book_service extends AppCompatActivity {

    ImageButton location;

    private TextView latituteField;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    TextView problemd;



     int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);


        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = database.getReference("Schedule of Service");
        problemd = (TextView) findViewById(R.id.problem_desc);
        location = (ImageButton) findViewById(R.id.location_picker);
        latituteField=(TextView)findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                                            Intent intent1;

                                                try {
                                                    intent1 = builder.build((Activity) getApplicationContext());
                                                    startActivityForResult(intent1,PLACE_PICKER_REQUEST);
                                                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                                                    e.printStackTrace();
                                                }
                                        }
                                    });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }


    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==PLACE_PICKER_REQUEST)
        {
            if(resultCode==RESULT_OK)
            {
                Place place =PlacePicker.getPlace(data,book_service.this);
                String address= String.format("Place: %s" ,place.getAddress());
              // latituteField.setText(address);

            }

        }
    }
}





        /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            Map<String,String> map=dataSnapshot.getValue(Map.class);
           String   problemdesc=map.get("name");
                problemd.setText(problemdesc);

            }



        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });*/









