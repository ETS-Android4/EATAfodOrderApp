package com.example.myapplicationmad;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddShopDetails extends AppCompatActivity {

    private Button SubmitAddResturant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_details);

        SubmitAddResturant= (Button)findViewById(R.id.submit_addResturant);

        SubmitAddResturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddShopDetails.this,SellarAccount.class);
                startActivity(intent);
                finish();
            }
        });

    }
}


