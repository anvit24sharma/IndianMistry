package com.example.dell.indianmistry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {



    private EditText Phonetext;
    private Button generateotp;
    private EditText enterotp;
    private Button resendotp;
    private Button submit;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String PhoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationcallbacks;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Intent intent = getIntent();


        //   email = intent.getStringExtra(SignUp.EXTRA_MESSAGE1);
        //password = intent.getStringExtra(SignUp.EXTRA_MESSAGE2);

        submit = (Button) findViewById(R.id.submit);
        Phonetext = (EditText) findViewById(R.id.phoneno);
        generateotp = (Button) findViewById(R.id.getotp);
        enterotp = (EditText) findViewById(R.id.enterotp);
        resendotp = (Button) findViewById(R.id.resend);

        fbAuth = FirebaseAuth.getInstance();

        submit.setEnabled(false);
        resendotp.setEnabled(false);


        verificationcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                System.out.println(" in onVerificationCompleted");


                signInWithPhoneAuthCredential(phoneAuthCredential);
                resendotp.setEnabled(false);


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException) {


                    System.out.println(" in onVerificationFailed -invalid");
                    Toast.makeText(otp.this, "Invalid Phone Number include Country Code ", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    System.out.println(" in onVerificationFailed -sms quota");

                    Toast.makeText(otp.this, "SMS quota exceeded ", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                System.out.println(" in onCodeSent");
                PhoneVerificationId = s;
                mResendToken = forceResendingToken;
              //  updateUI(STATE_CODE_SENT);
                submit.setEnabled(true);
                resendotp.setEnabled(true);
                generateotp.setEnabled(false);

            }

        };



        generateotp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String phoneno= Phonetext.getText().toString().trim();

                if(TextUtils.isEmpty(phoneno) || phoneno.length()<10) {
                    Toast.makeText(otp.this, "Please enter Phone no. ", Toast.LENGTH_SHORT).show();
                    return;
                }
                SendCode(view);

            }
        });

        resendotp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String phoneno= "+91" + Phonetext.getText().toString().trim();

                if(TextUtils.isEmpty(phoneno) || phoneno.length()<10) {
                    Toast.makeText(otp.this, "Please enter Phone no. ", Toast.LENGTH_SHORT).show();
                    return;
                }

                resendVerificationCode(phoneno,mResendToken);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                verifyCode(v);

                Intent intent1 = new Intent(otp.this, logged_in_user.class);

                startActivity(intent1);
            }

        });
    }

    public void SendCode(View view) {

        String phoneNumber = Phonetext.getText().toString();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, verificationcallbacks);

    }




    public void verifyCode(View view) {



        String code = enterotp.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(PhoneVerificationId, code);
        signInWithPhoneAuthCredential(credential);


    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationcallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {


        fbAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(otp.this, "Verified Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(otp.this, "Verification Failed...", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}


