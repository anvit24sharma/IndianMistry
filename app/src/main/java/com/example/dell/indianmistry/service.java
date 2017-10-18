package com.example.dell.indianmistry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class service extends AppCompatActivity {

    ImageView logo;
    TextView nameofservice;
    Button schedule_service;
    Button book_service_now;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Intent intent = getIntent();


        final String service = intent.getStringExtra(logged_in_user.SERVICE);

        nameofservice = (TextView) findViewById(R.id.name_of_service);
        nameofservice.setText(service);
        logo = (ImageView) findViewById(R.id.logo);
        schedule_service = (Button) findViewById(R.id.schedule_service);
        book_service_now = (Button) findViewById(R.id.book_service_now);


        if (service.equals("Electrician")) {
            logo.setImageResource(R.drawable.electrician);

        } else if (Objects.equals(service, "Plumber")) {
            logo.setImageResource(R.drawable.plumber);

        } else if (Objects.equals(service, "Car Service")) {
            logo.setImageResource(R.drawable.car_service);

        } else if (Objects.equals(service, "Bike Service")) {
            logo.setImageResource(R.drawable.bike_service);

        } else if (Objects.equals(service, "Cleaning")) {
            logo.setImageResource(R.drawable.cleaning);

        } else if (Objects.equals(service, "AC Fridge Others")) {
            logo.setImageResource(R.drawable.others);

        } else if (Objects.equals(service, "Photographer")) {
            logo.setImageResource(R.drawable.photographer);

        } else if (Objects.equals(service, "DJ Booking")) {
            logo.setImageResource(R.drawable.djbooking);

        }

        schedule_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(service.this, schedule_service.class);
                startActivity(intent1);

            }
        });

        book_service_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(service.this, book_service.class);
                startActivity(intent1);


            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(service.this, logged_in_user.class));

    }
}
