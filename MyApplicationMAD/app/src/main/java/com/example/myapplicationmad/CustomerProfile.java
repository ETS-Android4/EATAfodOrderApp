package com.example.myapplicationmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String cusId;
    private TextView order;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        logout=(Button) findViewById(R.id.logout);
        order=(TextView) findViewById(R.id.order);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(CustomerProfile.this,CustomerLogin.class));
                finish();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerProfile.this,AddFood.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Customer");
        cusId = user.getUid();

        final EditText cname = (EditText) findViewById(R.id.cname);
        final EditText cemail = (EditText) findViewById(R.id.cemail);
        final EditText cmobile = (EditText) findViewById(R.id.cmobile);
        final EditText caddress = (EditText) findViewById(R.id.address);
        final TextView cproname = (TextView) findViewById(R.id.name);
        final TextView cproemail = (TextView) findViewById(R.id.email);

        reference.child(cusId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Customer customer = snapshot.getValue(Customer.class);

                if (customer != null){
                    String name = customer.name;
                    String email = customer.email;
                    String mobile = customer.mobile;
                    String address = customer.address;
                    String pname = customer.name;
                    String pemail = customer.email;

                    cname.setText(name);
                    cemail.setText(email);
                    cmobile.setText(mobile);
                    caddress.setText(address);
                    cproname.setText(name);
                    cproemail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomerProfile.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }
}