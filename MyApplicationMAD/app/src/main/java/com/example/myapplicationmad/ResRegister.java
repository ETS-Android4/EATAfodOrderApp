package com.example.myapplicationmad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ResRegister extends AppCompatActivity implements View.OnClickListener {

    private TextView banner;
    private EditText rname,remail,rpassword,rcpass,rmobile,raddress;
    private Button rregister;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;



    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://eata-application-mad-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.hdv);
        banner.setOnClickListener(this);

        rregister=(Button) findViewById(R.id.RSignup);
        rregister.setOnClickListener(this);

        rname=(EditText) findViewById(R.id.RName);
        remail=(EditText) findViewById(R.id.REmail);
        rpassword=(EditText) findViewById(R.id.RPwd);
        rcpass=(EditText) findViewById(R.id.RCpass);
        rmobile=(EditText) findViewById(R.id.RMobile);
        raddress=(EditText) findViewById(R.id.RAddress);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        final EditText rname = findViewById(R.id.RName);
//        final EditText remail = findViewById(R.id.REmail);
//        final EditText rpass = findViewById(R.id.RPwd);
//        final EditText rcpass = findViewById(R.id.RCpass);
//        final EditText rmobile = findViewById(R.id.RMobile);
//        final EditText raddress = findViewById(R.id.RAddress);
//
//        final Button rregister = findViewById(R.id.RSignup);



//        rregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String rnameTxt = rname.getText().toString();
//                final String remailTxt = remail.getText().toString();
//                final String rpassTxt = rpass.getText().toString();
//                final String rcpassTxt = rcpass.getText().toString();
//                final String rmobileTxt = rmobile.getText().toString();
//                final String raddressTxt = raddress.getText().toString();
//
//                //check if user fill all the fields
//                if (rnameTxt.isEmpty() || remailTxt.isEmpty() || rpassTxt.isEmpty() || rcpassTxt.isEmpty() || rmobileTxt.isEmpty() || raddressTxt.isEmpty()) {
//                    Toast.makeText(ResRegister.this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
//
//                } else if (!rpassTxt.equals(rcpassTxt)) {
//                    Toast.makeText(ResRegister.this, "Password are not matching", Toast.LENGTH_SHORT).show();
//                }
////                else if (!Patterns.EMAIL_ADDRESS.matcher(remailTxt).matches()){
////                    Toast.makeText(ResRegister.this, "Email is Not Valid", Toast.LENGTH_SHORT).show();
////                }
//                else {
//
//                    databaseReference.child("Restaurants").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            if (snapshot.hasChild(remailTxt)) {
//                                Toast.makeText(ResRegister.this, "Email is Already registered", Toast.LENGTH_SHORT).show();
//                            }
//
//                            else {
//
//
//                                databaseReference.child("Restaurants").child(remailTxt).child("Res_name").setValue(rnameTxt);
//                                databaseReference.child("Restaurants").child(remailTxt).child("Res_pass").setValue(rpassTxt);
//                                databaseReference.child("Restaurants").child(remailTxt).child("Res_Mobile").setValue(rmobileTxt);
//                                databaseReference.child("Restaurants").child(remailTxt).child("Res_Address").setValue(raddressTxt);
//
//                                Toast.makeText(ResRegister.this, "User Register Successfull", Toast.LENGTH_SHORT).show();
//                                finish();
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                }
//
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,ResLogin.class));
                break;
                
            case R.id.RSignup:
                resregister();
                break;
        }

    }

    private void resregister() {
        String name = rname.getText().toString().trim();
        String email = remail.getText().toString().trim();
        String password = rpassword.getText().toString().trim();
        String conpassword = rcpass.getText().toString().trim();
        String mobile = rmobile.getText().toString().trim();
        String address = raddress.getText().toString().trim();

        if (name.isEmpty()){
            rname.setError("Resturent  Name is required");
            rname.requestFocus();
            return;
        }
        if (email.isEmpty()){
            remail.setError("Email is required");
            remail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            remail.setError("Please provide valid email");
            remail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            rpassword.setError("Password is required");
            rpassword.requestFocus();
            return;
        }
        if (conpassword.isEmpty()){
            rcpass.setError("Confirm Password is required");
            rcpass.requestFocus();
            return;
        }
        if(!password.equals(conpassword)){
            rcpass.setError("Not Matching with password");
            rcpass.requestFocus();
            return;
        }
        if (mobile.isEmpty()){
            rmobile.setError("Mobile Number is required");
            rmobile.requestFocus();
            return;
        }
        if (address.isEmpty()){
            raddress.setError("Address is required");
            raddress.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Resturant resturant = new Resturant(name,email,password,conpassword,mobile,address);

                   FirebaseDatabase.getInstance().getReference("Resturant")
                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                           .setValue(resturant).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(ResRegister.this, "Resturent Register Successful", Toast.LENGTH_LONG).show();
                               startActivity(new Intent(ResRegister.this,ResLogin.class));
                           }
                           else {
                               Toast.makeText(ResRegister.this, "Failed to register Resturant! Try again!", Toast.LENGTH_LONG).show();
                               progressBar.setVisibility(View.GONE);
                           }
                       }
                   });
               }
               else {
                   Toast.makeText(ResRegister.this, "Failed to register Resturant! Try again!", Toast.LENGTH_LONG).show();
                   progressBar.setVisibility(View.GONE);
               }
            }
        });


    }
}