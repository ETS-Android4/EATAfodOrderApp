package com.example.myapplicationmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

public class CusRegister extends AppCompatActivity implements View.OnClickListener {

    private TextView banner;
    private EditText name,email,password,cpass,mobile,address;
    private Button register;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.cushdv);
        banner.setOnClickListener(this);

        register=(Button) findViewById(R.id.Signup);
        register.setOnClickListener(this);

        name=(EditText) findViewById(R.id.Name);
        email=(EditText) findViewById(R.id.Email);
        password=(EditText) findViewById(R.id.Pwd);
        cpass=(EditText) findViewById(R.id.Cpass);
        mobile=(EditText) findViewById(R.id.Mobile);
        address=(EditText) findViewById(R.id.Address);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,CustomerLogin.class));
                break;

            case R.id.Signup:
                register();
                break;
        }

    }

    private void register() {
        String cusname = name.getText().toString().trim();
        String cusemail = email.getText().toString().trim();
        String cuspassword = password.getText().toString().trim();
        String cusconpassword = cpass.getText().toString().trim();
        String cusmobile = mobile.getText().toString().trim();
        String cusaddress = address.getText().toString().trim();

        if (cusname.isEmpty()){
            name.setError("Customer  Name is required");
            name.requestFocus();
            return;
        }
        if (cusemail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(cusemail).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if (cuspassword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (cusconpassword.isEmpty()){
            cpass.setError("Confirm Password is required");
            cpass.requestFocus();
            return;
        }
        if(!cuspassword.equals(cusconpassword)){
            cpass.setError("Not Matching with password");
            cpass.requestFocus();
            return;
        }
        if (cusmobile.isEmpty()){
            mobile.setError("Mobile Number is required");
            mobile.requestFocus();
            return;
        }
        if (cusaddress.isEmpty()){
            address.setError("Address is required");
            address.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(cusemail,cuspassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Customer customer = new Customer(cusname,cusemail,cuspassword,cusconpassword,cusmobile,cusaddress);

                    FirebaseDatabase.getInstance().getReference("Customer")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(CusRegister.this, "Custopmer REgister successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CusRegister.this,CustomerLogin.class));
                            }
                            else{
                                Toast.makeText(CusRegister.this, "Failed to register Customer! Try again!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(CusRegister.this, "Failed to register Customer! Try again!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
