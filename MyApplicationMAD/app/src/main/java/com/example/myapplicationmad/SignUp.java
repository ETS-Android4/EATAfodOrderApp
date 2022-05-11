package com.example.myapplicationmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {
    Button signinemail,signinphone,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signinemail=(Button)findViewById(R.id.signinemail);
//        signinphone=(Button)findViewById(R.id.signinphone);
        signup=(Button)findViewById(R.id.signup);

        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinemail = new Intent(SignUp.this,ChooseOne.class);
                signinemail.putExtra("Home","Email");
                startActivity(signinemail);
                finish();
            }
        });

//        signinphone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signinphone = new Intent(SignUp.this,ChooseOne.class);
//                signinphone.putExtra("Home","Phone");
//                startActivity(signinphone);
//                finish();
//            }
//        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(SignUp.this,ChooseOne.class);
                signup.putExtra("Home","SignUp");
                startActivity(signup);
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        System.gc();
    }

}