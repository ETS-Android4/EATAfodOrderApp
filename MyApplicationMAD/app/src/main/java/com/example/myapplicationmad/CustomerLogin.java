package com.example.myapplicationmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLogin extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText editTextLcmail,editTextlcpassword;
    private Button signin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signin=(Button) findViewById(R.id.signin);
        signin.setOnClickListener(this);

        editTextLcmail=(EditText) findViewById(R.id.Lcemail);
        editTextlcpassword=(EditText) findViewById(R.id.Lcpassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,CusRegister.class));
                break;

            case R.id.signin:
                Login();
                break;
        }
    }

    private void Login() {
        String email = editTextLcmail.getText().toString().trim();
        String password = editTextlcpassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextLcmail.setError("Email is required");
            editTextLcmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextLcmail.setError("Please Enter a valid enmail ");
            editTextLcmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextlcpassword.setError("Password is required");
            editTextlcpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.GONE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(CustomerLogin.this,CustomerProfile.class));
                } else {
                    Toast.makeText(CustomerLogin.this,"Failed to Login! Please chaeck your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}