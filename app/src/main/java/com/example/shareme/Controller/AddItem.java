package com.example.shareme.Controller;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shareme.Model.Items;
import com.example.shareme.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class AddItem extends AppCompatActivity {


    public static final String ANONYMOUS = "anonymous";


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference db = database.getReference("items");
    private ImageButton photoPicker, photoPicker1, photoPicker2;
    private EditText nameText, descriptionText, priceText, addressText;
    private Button saveButton;
    private String mId;
    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_IMAGE_REQUEST1 = 2;
    private int PICK_IMAGE_REQUEST2 = 3;
    StorageReference imagePath; String imageLocation;
    StorageReference storage = FirebaseStorage.getInstance().getReference();
    MyAdapter adapter;
    RecyclerView rv;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mId = db.push().getKey();


        photoPicker = (ImageButton) findViewById(R.id.photoPickerButton);
        photoPicker1 = (ImageButton) findViewById(R.id.photoPickerButton1);
        photoPicker2 = (ImageButton) findViewById(R.id.photoPickerButton2);
        nameText = (EditText) findViewById(R.id.nameEditText);
        descriptionText = (EditText) findViewById(R.id.descriptionEditText);
        priceText = (EditText) findViewById(R.id.priceEditText);
        addressText = (EditText) findViewById(R.id.addressEditText);;
        saveButton = (Button) findViewById(R.id.saveButton);

        rv = findViewById(R.id.rv);

        photoPicker.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }

        });
        photoPicker1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST1);

            }

        });
        photoPicker2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST2);

            }

        });


        //SAVE
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String image = imagePath.toString();
                String name = nameText.getText().toString();
                String description = descriptionText.getText().toString();
                Float price = Float.valueOf(priceText.getText().toString());
                String address = addressText.getText().toString();

                writeNewItem(image, name, description, price, address);

            }

        });



    }
    private void writeNewItem(String image, String name, String description, float price, String address){

        if(!TextUtils.isEmpty(name) &&  !TextUtils.isEmpty(address)) {
            Items item = new Items(image, name, description, price, address);
            db.push().setValue(item);
            photoPicker.setImageURI(Uri.parse(""));
            nameText.setText("");
            descriptionText.setText("");
            priceText.setText("");
            addressText.setText("");

            rv.setAdapter(adapter);


        }
        else
        {
            Toast.makeText(AddItem.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            photoPicker.setImageURI(uri);
            imagePath = storage.child("items").child(uri.getLastPathSegment());
            imageLocation = uri.getLastPathSegment();
            imagePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddItem.this,"Uploaded...", Toast.LENGTH_SHORT);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddItem.this,"Not Uploaded...", Toast.LENGTH_SHORT);
                }
            });
        }
        else if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            photoPicker1.setImageURI(uri);


        }
        else if (requestCode == PICK_IMAGE_REQUEST2 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();


            photoPicker2.setImageURI(uri);


        }
    }
}
