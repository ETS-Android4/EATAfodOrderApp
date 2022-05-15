package com.example.myapplicationmad;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.UUID;

public class AddFood extends AppCompatActivity implements View.OnClickListener {

    private EditText dishaname, dishdesc, dishqty, dishpri;
    private Button postBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        postBtn = findViewById(R.id.post);
        postBtn.setOnClickListener(this);

        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(this, RestuarentProfile.class));
                break;

            case R.id.post:
                AddFoodDetails();
                break;


        }


    }

    private void AddFoodDetails() {
        dishaname = findViewById(R.id.dishname);
        dishdesc = findViewById(R.id.description);
        dishqty = findViewById(R.id.quantity);
        dishpri = findViewById(R.id.price);

        String name = dishaname.getText().toString().trim();
        String desc = dishdesc.getText().toString().trim();
        String qty = dishqty.getText().toString().trim();
        String pri = dishpri.getText().toString().trim();

        FoodSupplyDetails foodSupplyDetails = new FoodSupplyDetails(name,desc,qty,pri);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("FoodDetails");


        node.child(name).setValue(foodSupplyDetails);
        dishaname.setText("");
        dishdesc.setText("");
        dishqty.setText("");
        dishpri.setText("");
        Toast.makeText(this, "Post dish", Toast.LENGTH_SHORT).show();
    }

}
