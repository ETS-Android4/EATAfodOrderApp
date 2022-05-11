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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResLogin extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
   private EditText editTextLrmail,editTextlrpassword;
   private Button ressignin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_login);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        ressignin=(Button) findViewById(R.id.signin);
        ressignin.setOnClickListener(this);

        editTextLrmail=(EditText) findViewById(R.id.Lemail);
        editTextlrpassword=(EditText) findViewById(R.id.Lpassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth=FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,ResRegister.class));
                break;

            case R.id.signin:
                resLogin();
                break;
       }
    }

    private void resLogin() {

        String email = editTextLrmail.getText().toString().trim();
        String password = editTextlrpassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextLrmail.setError("Email is required");
            editTextLrmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextLrmail.setError("Please Enter a valid enmail ");
            editTextLrmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextlrpassword.setError("Password is required");
            editTextlrpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(ResLogin.this,CusRegister.class));
                } else {
                    Toast.makeText(ResLogin.this,"Failed to Login! Please chaeck your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

//public class ResLogin extends AppCompatActivity {
//
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eata-application-mad-default-rtdb.firebaseio.com/");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_res_login);
//
//        final EditText lremail = findViewById(R.id.Lemail);
//        final EditText lepass = findViewById(R.id.Lpassword);
//
//        final Button rlogin = findViewById(R.id.rlogin);
//
//        rlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String lremailTxt = lremail.getText().toString();
//                final String lrpasswordTxt = lepass.getText().toString();
//
//                if (lremailTxt.isEmpty() || lrpasswordTxt.isEmpty()){
//                    Toast.makeText(ResLogin.this, "Please Enter your email or password", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    databaseReference.child("Restaurants").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            if (snapshot.hasChild(lremailTxt)){
//                            final String getPassword = snapshot.child(lremailTxt).child("Password").getValue(String.class);
//                            if (getPassword.equals(lrpasswordTxt)) {
//                                Toast.makeText(ResLogin.this, "Successfully Login", Toast.LENGTH_SHORT).show();
//                                Intent rlogin = new Intent(ResLogin.this,CustomerLogin.class);
//                                startActivity(rlogin);
//                                finish();
//
//                            } else {
//                                Toast.makeText(ResLogin.this, "Wrong password", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                            else {
//                                Toast.makeText(ResLogin.this, "Wrong password", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                }
//            }
//        });
//    }
//}