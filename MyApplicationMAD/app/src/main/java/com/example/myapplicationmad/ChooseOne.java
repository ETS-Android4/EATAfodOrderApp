package com.example.myapplicationmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {

    Button customer,resturent;
    Intent intent;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        customer = (Button)findViewById(R.id.customer);
        resturent = (Button)findViewById(R.id.resturent);

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemail = new Intent(ChooseOne.this,CustomerLogin.class);
                    startActivity(loginemail);
                    finish();
                }

                if(type.equals("Phone")){
                    Intent loginphone = new Intent(ChooseOne.this,CustomerLoginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent register = new Intent(ChooseOne.this,SingIn.class);
                    startActivity(register);
                    finish();
                }

            }

        });

        resturent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(type.equals("Email")){
                    Intent loginemail = new Intent(ChooseOne.this,ResLogin.class);
                    startActivity(loginemail);
                    finish();
                }

                if(type.equals("Phone")){
                    Intent loginphone = new Intent(ChooseOne.this,ResLoginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent resregister = new Intent(ChooseOne.this,ResRegister.class);
                    startActivity(resregister);
                    finish();
                }

            }
        });

    }
}