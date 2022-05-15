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

        switch (v.getId()){
            case R.id.back:
                startActivity(new Intent(this,RestuarentProfile.class));
                break;

            case R.id.post:
                AddFood();
                break;


        }

    }

    private void AddFood() {
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


//     private EditText dishaname,dishdesc,dishqty,dishpri;
//     private Button postBtn,backBtn;
//     private FirebaseFirestore db;
//
//
////    ImageButton imageButton;
////    TextInputLayout dname,ddesc,dqty,dpri;
////    Button addbtn;
////    String dish_name,dish_desc,dish_qty,dish_price;
////    Uri imageuri;
////    private Uri mcropimageuri;
////    FirebaseStorage storage;
////    StorageReference storageReference;
////    FirebaseDatabase firebaseDatabase;
////    DatabaseReference databaseReference,dataa;
////    FirebaseAuth mAuth;
////    StorageReference ref;
////    String ResId,RandomUID, name,email,address;
//
////    private TextView banner;
////    private TextInputLayout dishname,dishdesc,dishqty,dishpri;
////    private Button post;
////    private ProgressBar progressBar;
////    private FirebaseAuth mAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_food);
//
//        dishaname = findViewById(R.id.dishname);
//        dishdesc = findViewById(R.id.description);
//        dishqty = findViewById(R.id.quantity);
//        dishpri = findViewById(R.id.price);
//
////        storage = FirebaseStorage.getInstance();
////        storageReference = storage.getReference();
////        FAuth = FirebaseAuth.getInstance();
//
//        postBtn = (Button) findViewById(R.id.post);
//        postBtn.setOnClickListener(this);
//
//        backBtn = (Button) findViewById(R.id.back);
//        backBtn.setOnClickListener(this);
//
////        databaseReference = firebaseDatabase.getInstance().getReference("FoodDetails");
//
//
////        try{
////            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
////            dataaa = firebaseDatabase.getInstance().getReference("Resturant").child(userid);
////            dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                    Resturant resturant = dataSnapshot.getValue(Resturant.class);
////                    resname=resturant.getName();
////                    imageButton = (ImageButton) findViewById(R.id.imageupload);
////                    imageButton.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////                            onSelectImageClick(v);
////                        }
////                    });
////
////                    postBtn.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////
////                            dishaname = (EditText)findViewById(R.id.dishname);
////                            dishdesc = (EditText)findViewById(R.id.description);
////                            dishqty = (EditText)findViewById(R.id.quantity);
////                            dishpri = (EditText)findViewById(R.id.price);
////
////                            String name = dishaname.getText().toString().trim();
////                            String desc = dishdesc.getText().toString().trim();
////                            String qty = dishqty.getText().toString().trim();
////                            String pri = dishpri.getText().toString().trim();
////
////                            if (isValid()){
////                                uploadImage();
////                            }
////
////                        }
////                    });
////
////
////                }
////
////                @Override
////                public void onCancelled(@NonNull DatabaseError error) {
////
////                }
////            });
////
////        }catch(Exception e){
////            Log.e("Error: ",e.getMessage());
////        }
//
//
//
//
//
////        mAuth = FirebaseAuth.getInstance();
////        banner = (TextView) findViewById(R.id.addfodds_head);
////        banner.setOnClickListener(this);
////
////        post=(Button)findViewById(R.id.post);
////        post.setOnClickListener(this);
////
////        dishname=(TextInputLayout)findViewById(R.id.dishname);
////        dishdesc=(TextInputLayout)findViewById(R.id.description);
////        dishqty = (TextInputLayout)findViewById(R.id.quantity);
////        dishpri=(TextInputLayout)findViewById(R.id.price);
////
////        progressBar = (ProgressBar) findViewById(R.id.progressBar);
////
////
////
////    }
////
////    @Override
////    public void onClick(View v) {
////        switch (v.getId()){
////            case R.id.banner:
////                startActivity(new Intent(this,RestuarentProfile.class));
////                break;
////
////            case R.id.post:
////                postDish();
////                break;
////        }
////    }
////
////    private void postDish() {
////
////        String dname = dishname.getEditText().toString().trim();
////        String ddescription = dishdesc.getEditText().toString().trim();
////        String dquantity = dishqty.getEditText().toString().trim();
////        String dprice = dishpri.getEditText().toString().trim();
////
////
////        if (dname.isEmpty()){
////            dishname.setError("Dish name required");
////            dishname.requestFocus();
////            return;
////        }
////        if (ddescription.isEmpty()){
////            dishdesc.setError("Dish description required");
////            dishdesc.requestFocus();
////            return;
////        }
////        if (dquantity.isEmpty()){
////            dishqty.setError("Dish Quantity required");
////            dishqty.requestFocus();
////            return;
////        }
////        if (dprice.isEmpty()){
////            dishpri.setError("Dish Price required");
////            dishpri.requestFocus();
////            return;
////        }
////
////        progressBar.setVisibility(View.VISIBLE);
////
////
//    }
//
////    private void uploadImage() {
////
////        if (imageuri != null) {
////            final ProgressDialog progressDialog = new ProgressDialog(.this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.show();
////            RandomUId = UUID.randomUUID().toString();
////            ref = storageReference.child(RandomUId);
////            ChefId = FirebaseAuth.getInstance().getCurrentUser().getUid();
////            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
////
////                @Override
////                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////                        @Override
////                        public void onSuccess(Uri uri) {
////                            FoodSupplyDetails info = new FoodSupplyDetails(dishes, quantity, price, description, String.valueOf(uri), RandomUId, ChefId);
////                            firebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(State).child(City).child(Sub).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId)
////                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
////                                @Override
////                                public void onComplete(@NonNull Task<Void> task) {
////                                    progressDialog.dismiss();
////                                    Toast.makeText(Chef_PostDish.this, "Dish posted successfully", Toast.LENGTH_SHORT).show();
////                                }
////                            });
////                        }
////                    });
////
////                }
////            }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NonNull Exception e) {
////
////                    progressDialog.dismiss();
////                    Toast.makeText(Chef_PostDish.this, e.getMessage(), Toast.LENGTH_SHORT).show();
////                }
////            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
////                @Override
////                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
////
////                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
////                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
////                    progressDialog.setCanceledOnTouchOutside(false);
////                }
////            });
////        }
////
////    }
////
////    private boolean isValid() {
////        return isValid();
////    }
////
////    private void onSelectImageClick(View v) {
////        CropImage.startPickImageActivity(this);
////    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.back:
//                startActivity(new Intent(this,RestuarentProfile.class));
//                break;
//
//            case R.id.post:
//                AddFood();
//                break;
//
//
//        }
//
//    }
//
//    public void AddFood(){
//                dishaname = (EditText)findViewById(R.id.dishname);
//                dishdesc = (EditText)findViewById(R.id.description);
//                dishqty = (EditText)findViewById(R.id.quantity);
//                dishpri = (EditText)findViewById(R.id.price);
//
//                String name = dishaname.getText().toString().trim();
//                String desc = dishdesc.getText().toString().trim();
//                String qty = dishqty.getText().toString().trim();
//                String pri = dishpri.getText().toString().trim();
//
//                FoodSupplyDetails foodSupplyDetails = new FoodSupplyDetails(name,desc,qty,pri);
//
//                FirebaseDatabase db = FirebaseDatabase.getInstance();
//                DatabaseReference node = db.getReference("FoodDetails");
//
//               node.child(name).setValue(foodSupplyDetails);
//
//                dishaname.setText("");
//                dishdesc.setText("");
//                dishqty.setText("");
//                dishpri.setText("");
//                Toast.makeText(this, "Food Posted", Toast.LENGTH_SHORT).show();
//    }
//
//}









//        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference();
//        addbtn = (Button)findViewById(R.id.post);
//        dname = (TextInputLayout) findViewById(R.id.dishname);
//        ddesc = (TextInputLayout)findViewById(R.id.description);
//        dqty = (TextInputLayout) findViewById(R.id.quantity);
//        dpri = (TextInputLayout) findViewById(R.id.price);
//        mAuth = FirebaseAuth.getInstance();
//        databaseReference = firebaseDatabase.getInstance().getReference("FoodSupplyDetails");
//
//        try {
//            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            dataa = firebaseDatabase.getInstance().getReference("Resturant").child(userid);
//            dataa.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Resturant resturant = snapshot.getValue(Resturant.class);
//                    name = resturant.getName();
//                    email = resturant.getEmail();
//                    address = resturant.getAddress();
//                    imageButton = (ImageButton) findViewById(R.id.imageupload);
//                    imageButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            onSelectImageClick(v);
//                        }
//                    });
//                    addbtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dish_name = dname.getEditText().toString().trim();
//                            dish_desc = ddesc.getEditText().toString().trim();
//                            dish_qty = dqty.getEditText().toString().trim();
//                            dish_price = dpri.getEditText().toString().trim();
//
//                            if (isValid()){
//                                uploadImage();
//                            }
//
//                        }
//                    });
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//
//
//        }catch (Exception e){
//            Log.e("Error : ",e.getMessage());
//        }

//}

//    private boolean isValid() {
//        ddesc.setErrorEnabled(false);
//        ddesc.setError("");
//        dqty.setErrorEnabled(false);
//        dqty.setError("");
//        dpri.setErrorEnabled(false);
//        dpri.setError("");
//
//        boolean isValidDescription=false, isValidPrice=false, isValidQuantity=false, isValid=false;
//        if (TextUtils.isEmpty(dish_desc)){
//            ddesc.setErrorEnabled(true);
//            ddesc.setError("Description is Required");
//        }else {
//            ddesc.setError(null);
//            isValidDescription = true;
//        }
//        if (TextUtils.isEmpty(dish_qty)){
//            dqty.setErrorEnabled(true);
//            dqty.setError("Quantity is Required");
//        }else {
//            isValidQuantity = true;
//        }
//        if (TextUtils.isEmpty(dish_price)){
//            dpri.setErrorEnabled(true);
//            dpri.setError("Price is Required");
//        }else {
//
//            isValidPrice = true;
//        }
//
//        isValid = (isValidDescription && isValidQuantity && isValidPrice) ? true : false;
//
//        return isValid;
//
//
//    }
//
//    private void uploadImage() {
//        if (imageuri != null) {
//            final ProgressDialog progressDialog = new ProgressDialog(AddFood.this);
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();
//            RandomUID = UUID.randomUUID().toString();
//            ref = storageReference.child(RandomUID);
//            ResId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            FoodSupplyDetails info = new FoodSupplyDetails(dish_name,dish_desc, dish_price , dish_qty , String.valueOf(uri), RandomUID, ResId);
//                            firebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(name).child(email).child(address).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID)
//                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(AddFood.this, "Dish posted successfully", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(AddFood.this,RestuarentProfile.class));
//                                }
//                            });
//                        }
//                    });
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//
//                    progressDialog.dismiss();
//                    Toast.makeText(AddFood.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
//                    progressDialog.setCanceledOnTouchOutside(false);
//                }
//            });
//        }
//    }
//
//    private void onSelectImageClick(View v) {
//
//        CropImage.startPickImageActivity(this);
//    }
//
//    @Override
//    @SuppressLint("NewApi")
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//
//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            imageuri = CropImage.getPickImageResultUri(this, data);
//
//            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
//                mcropimageuri = imageuri;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//
//            } else {
//
//                startCropImageActivity(imageuri);
//            }
//        }
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                ((ImageButton) findViewById(R.id.imageupload)).setImageURI(result.getUri());
//                Toast.makeText(this, "Cropped Successfully", Toast.LENGTH_SHORT).show();
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Toast.makeText(this, "Cropping failed" + result.getError(), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (mcropimageuri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            startCropImageActivity(mcropimageuri);
//        } else {
//            Toast.makeText(this, "cancelling,required permission not granted", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void startCropImageActivity(Uri imageuri) {
//
//        CropImage.activity(imageuri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMultiTouchEnabled(true)
//                .start(this);
//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()){
//            case R.id.banner:
//                startActivity(new Intent(this,ResRegister.class));
//                break;
//
//            case R.id.post:
//                postFood();
//                break;
//        }
//
//    }

//    private void postFood() {
//
//        String dishname = dname.getText().toString().trim();
//        String desc = dishdesc.getText().toString().toString().trim();
//        String qty = dishqut.getText().toString().trim();
//        String pri = dishpri.getText().toString().trim();
//
//
//    }
//}