package com.example.dell.indianmistry;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class logged_in_user extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{

    public static final String SERVICE="com.example.dell.indianmistry.service";
    String profilename;
    TextView your_id;
    TextView your_name;
    ImageView edit_icon;

    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView electrician , plumber, car_service,bike_service,photographer,cleaning,others,djbooking;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_user);

        Intent intent = getIntent();

       firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference=FirebaseDatabase.getInstance().getReference("Profile");


       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            Map<String,String> map=dataSnapshot.getValue(Map.class);
             profilename=map.get("name");
                your_name.setText(profilename);

            }



        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });*/

        electrician = (TextView) findViewById(R.id.electrician);
        plumber =(TextView) findViewById(R.id.plumber);
        car_service =(TextView)findViewById(R.id.carservice);
        bike_service=(TextView)findViewById(R.id.bikeservice);
        cleaning=(TextView) findViewById(R.id.cleaning);
        others =(TextView)findViewById(R.id.acfridgeother);
        photographer=(TextView)findViewById(R.id.photograper);
        djbooking =(TextView)findViewById(R.id.djbooking);

        electrician.setOnClickListener(this);
        plumber.setOnClickListener(this);
        car_service.setOnClickListener(this);
        bike_service.setOnClickListener(this);
        cleaning.setOnClickListener(this);
        others.setOnClickListener(this);
        photographer.setOnClickListener(this);
        djbooking.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



       your_id=(TextView) navigationView.getHeaderView(0).findViewById(R.id.email_id_textview);
        your_id.setText(user.getEmail());

        your_name=(TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_name);


            if(profilename!=null) {
                your_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_name);
                your_name.setText(profilename);
            }


      edit_icon =(ImageView)navigationView.getHeaderView(0).findViewById(R.id.edit_icon);
        edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(logged_in_user.this,profile.class));
            }
        });


    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {


            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {

            startActivity(new Intent(logged_in_user.this,profile.class));

        } else if (id == R.id.bookings) {

        } else if (id == R.id.book_services) {

        } else if (id == R.id.wallet) {

        } else if (id == R.id.invite_earn) {

        } else if (id == R.id.call_us) {

        }else if (id == R.id.rate) {

        }else if (id == R.id.privacy) {

        }else if (id == R.id.tnc) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {


            if(v == electrician){
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);

                intent.putExtra(SERVICE,electrician.getText().toString());
                startActivity( intent);
            }
               else if(v == plumber){

                    finish();
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);
                    intent.putExtra(SERVICE,plumber.getText().toString());
                    startActivity( intent);
                }

                else if(v == car_service){

                    finish();
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);
                    intent.putExtra(SERVICE,car_service.getText().toString());
                    startActivity( intent);
                }

                else if(v == bike_service){

                    finish();
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);
                    intent.putExtra(SERVICE,bike_service.getText().toString());
                    startActivity( intent);
                }
               else if(v == cleaning){

                    finish();
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);
                    intent.putExtra(SERVICE,cleaning.getText().toString());
                    startActivity( intent);
                }
               else if(v == others){

                    finish();
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);
                    intent.putExtra(SERVICE,others.getText().toString());
                    startActivity( intent);
                }
                else if(v == photographer){

                    finish();
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);
                    intent.putExtra(SERVICE,photographer.getText().toString());
                    startActivity( intent);
                }
                else if(v == djbooking){

                    finish();
                Intent intent;
                intent = new Intent(logged_in_user.this,service.class);
                    intent.putExtra(SERVICE,djbooking.getText().toString());
                    startActivity(intent );
                }

        }

    }

