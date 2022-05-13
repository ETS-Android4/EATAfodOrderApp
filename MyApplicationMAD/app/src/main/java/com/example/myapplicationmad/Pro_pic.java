package com.example.myapplicationmad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class Pro_pic extends AppCompatActivity {


    private CircleImageView profileImage;
    private Button closeBtn,saveBtn;
    private TextView profileChangeBtn;

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadeTask;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_pic);

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Resturant");
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile pic");

        profileImage = findViewById(R.id.pro_pic);

        closeBtn = findViewById(R.id.btnClose);
        saveBtn = findViewById(R.id.btnSave);

        profileChangeBtn = findViewById(R.id.change_profile_pic_btn);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pro_pic.this,RestuarentProfile.class));
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfileImage();
            }
        });
        profileChangeBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                CropImage.activity().setAspectRatio(1,1).start(Pro_pic.this);

            }
        });

        getUserinfo();

    }

    private void getUserinfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists() && datasnapshot.getChildrenCount()>0)
                {
                    if (datasnapshot.hasChild("image")){
                        String image = datasnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && requestCode == RESULT_OK && data != null){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            profileImage.setImageURI(imageUri);
        }
        else {
            Toast.makeText(this, "Error, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadProfileImage() {
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Set your profile");
        progressDialog.setMessage("Please Wait, While we are setting your data ");
        progressDialog.show();

        if (imageUri != null){
            final StorageReference fileRef = storageReference
                    .child(mAuth.getCurrentUser().getUid()+".jpg");

            uploadeTask = fileRef.putFile(imageUri);

            uploadeTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        myUri = downloadUri.toString();

                        HashMap<String, Object>userMap = new HashMap<>();
                        userMap.put("image",myUri);

                        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

                        progressDialog.dismiss();
                    }
                }
            });
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(this, "Image Not Selected", Toast.LENGTH_SHORT).show();
        }
    }
}