package com.example.dell.indianmistry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class splashscreen extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(splashscreen.this, logged_in_user.class);
                startActivity(i);
                finish();
            }
        }, 5000);
        imageView=(ImageView)findViewById(R.id.logo);
    }
}
