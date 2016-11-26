package com.sine.tapahuevos2;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;

    private EditText mtitleField;
    private EditText mdescField;
    private Button mSubmitBtn;
    private Uri mimageUri = null;
    private StorageReference mStorage;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

     mStorage = FirebaseStorage.getInstance().getReference();
     mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");


        mSelectImage = (ImageButton) findViewById(R.id.ImageSelect);
        mtitleField = (EditText) findViewById(R.id.titleField);
        mdescField = (EditText) findViewById(R.id.descField);
        mSubmitBtn = (Button)  findViewById(R.id.submitBtn);

      mProgressDialog = new  ProgressDialog(this);

       mSelectImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
               galleryIntent.setType("image/*");
               startActivityForResult(galleryIntent, GALLERY_REQUEST);
           }
       });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                starPosting();


            }
        });

    }

    private void starPosting() {
        mProgressDialog.setMessage("Compartiendo tu publicación...");


        final String title_val = mtitleField.getText().toString().trim();
        final String desc_val = mdescField.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val)&& !TextUtils.isEmpty(desc_val) && mimageUri != null){
            mProgressDialog.show();

            StorageReference filepath = mStorage.child("Blog_Images").child(mimageUri.getLastPathSegment());

            filepath.putFile(mimageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("title").setValue(title_val);
                    newPost.child("desc").setValue(desc_val);
                    newPost.child("image").setValue(downloadUrl.toString());
                    //TODO: insertar id para mirar quien publico newPost.child("uid").setValue(FirebaseAuth.get)
                    mProgressDialog.dismiss();


                    Intent intent = new Intent(PostActivity.this, Home_News.class);
                    ComponentName cn = intent.getComponent();
                    Intent mainIntent = IntentCompat.makeRestartActivityTask(cn);
                    startActivity(mainIntent);



                }
            });

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

           mimageUri  = data.getData();
            mSelectImage.setImageURI(mimageUri);
        }
    }
}
