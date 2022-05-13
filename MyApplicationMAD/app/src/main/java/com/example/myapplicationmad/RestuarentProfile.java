package com.example.myapplicationmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RestuarentProfile extends AppCompatActivity {

    private CircleImageView profileImage;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String resId;
    private TextView addfood;
    private Button reslogout;
//    private ImageView profile_image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarent_profile);

        reslogout=(Button) findViewById(R.id.reslogout);
        addfood=(TextView) findViewById(R.id.addfood);

//        profileImage=findViewById(R.id.profile_image);

        profileImage= (CircleImageView) findViewById(R.id.profile_image);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestuarentProfile.this,Pro_pic.class));

            }
        });

       //getUserinfo();


        addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestuarentProfile.this,AddFood.class));
            }
        });

        reslogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RestuarentProfile.this,ResLogin.class));
                finish();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Resturant");
        resId = user.getUid();




        final EditText resname = (EditText) findViewById(R.id.resname);
        final EditText resemail = (EditText) findViewById(R.id.resemail);
        final EditText resmobile = (EditText) findViewById(R.id.resmobile);
        final EditText resaddress = (EditText) findViewById(R.id.resaddress);
        final TextView proname = (TextView) findViewById(R.id.Resname);
        final TextView proemail = (TextView) findViewById(R.id.Resemail);

        reference.child(resId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Resturant resprofile = snapshot.getValue(Resturant.class);

                if (resprofile != null){
                    String name = resprofile.name;
                    String email = resprofile.email;
                    String mobile = resprofile.mobile;
                    String address = resprofile.address;
                    String pname = resprofile.name;
                    String pemail = resprofile.email;

                    resname.setText(name);
                    resemail.setText(email);
                    resmobile.setText(mobile);
                    resaddress.setText(address);
                    proname.setText(pname);
                    proemail.setText(pemail);
                }

                if (snapshot.exists() && snapshot.getChildrenCount()>0)
                {
                    if (snapshot.hasChild("image")){
                        String image = snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RestuarentProfile.this, "Something is Wrong", Toast.LENGTH_SHORT).show();
            }
        });




    }
//    private void getUserinfo() {
//        reference.child(resId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                if (datasnapshot.exists() && datasnapshot.getChildrenCount()>0)
//                {
//                    if (datasnapshot.hasChild("image")){
//                        String image = datasnapshot.child("image").getValue().toString();
//                        Picasso.get().load(image).into(profileImage);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }



}