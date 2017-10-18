package com.example.dell.indianmistry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private Button verify;
    private EditText editTextEmail;
    private EditText name;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    String email,password;
  // public static final String EXTRA_MESSAGE1="com.example.dell.indianmistry.EXTRA_MESSAGE1";
 //   public static final String EXTRA_MESSAGE2="com.example.dell.indianmistry.EXTRA_MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent intent = getIntent();



        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
         verify = (Button) findViewById(R.id.Verify);
        editTextEmail=(EditText) findViewById(R.id.sign_up_email);
        editTextPassword=(EditText) findViewById(R.id.sign_up_password);
        editTextPhone =(EditText)  findViewById(R.id.sign_up_phoneno);
        name=(EditText) findViewById(R.id.name);

        verify.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                registerUser();

            }
        });



    }

    private void registerUser(){
      email= editTextEmail.getText().toString().trim();
      password= editTextPassword.getText().toString().trim();
        String phoneno= editTextPhone.getText().toString().trim();

        if(TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".com")) {
            Toast.makeText(this, "Please enter Valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password) || password.length()<7) {
            Toast.makeText(this, "Please enter password with minimum 8 characters", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(phoneno) || phoneno.length()<10) {
            Toast.makeText(this, "Please enter Phone no. ", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("on complete");
                        if(task.isSuccessful()){
                            System.out.println("in successful");
                            Toast.makeText(SignUp.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this,otp.class);
                            startActivity(intent);
                        }
                        else{
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.relative_layout),"Registering Error...",Snackbar.LENGTH_SHORT);

                            snackbar.show();
                        }
                    }
                }
        );







    }




}
