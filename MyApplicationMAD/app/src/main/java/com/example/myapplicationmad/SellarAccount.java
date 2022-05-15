package com.example.myapplicationmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SellarAccount extends AppCompatActivity {

    private Button AddShop,EditResturantDetails;
    private LinearLayout ViewResturant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellar_account);

        AddShop= (Button)findViewById(R.id.addResturant);
        EditResturantDetails= (Button)findViewById(R.id.editResturant);
        ViewResturant= (LinearLayout) findViewById(R.id.Shop1);

        AddShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellarAccount.this,AddShopDetails.class);
                startActivity(intent);
                finish();
            }
        });

        ViewResturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellarAccount.this,ViewReasturant.class);
                startActivity(intent);
                finish();
            }
        });

        EditResturantDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellarAccount.this,EditResturantDetails.class);
                startActivity(intent);
                finish();
            }
        });



    }
}