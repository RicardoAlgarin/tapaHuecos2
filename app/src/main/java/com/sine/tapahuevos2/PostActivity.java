package com.sine.tapahuevos2;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.location.Location;

import static com.google.ads.AdRequest.LOGTAG;

public class PostActivity extends AppCompatActivity/* implements
GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks*/{

    private static final String LOGTAG = "android-localizacion";

    private static final int PETICION_PERMISO_LOCALIZACION = 101;

    private GoogleApiClient apiClient;

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;

    private EditText mtitleField;
    private EditText mdescField;
    private Button mSubmitBtn;
    private Uri mimageUri = null;
    private StorageReference mStorage;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mDatabase;
    private Button mgetlocalitacion;
    private TextView lblLatidud;
    private TextView lblLongitud;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

     mStorage = FirebaseStorage.getInstance().getReference();
     mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");


        mSelectImage = (ImageButton) findViewById(R.id.image_select);
        mtitleField = (EditText) findViewById(R.id.titleField);
        mdescField = (EditText) findViewById(R.id.descField);
        mSubmitBtn = (Button)  findViewById(R.id.submitBtn);
        mgetlocalitacion= (Button) findViewById(R.id.localitation_btn);
     //   lblLatidud= (TextView) findViewById(R.id.post_latitud);
      //  lblLongitud = (TextView) findViewById(R.id.post_longitud);


      /*   apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
*/

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

     /*
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.

        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {


        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        } else {

            Location lastLocation =
                    LocationServices.FusedLocationApi.getLastLocation(apiClient);

            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }




    private void updateUI(Location loc) {

        if (loc != null) {
            lblLatidud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
        } else {
            lblLatidud.setText("Latitud: (desconocida)");
            lblLongitud.setText("Longitud: (desconocida)");
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);

                updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }
*/

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
                    newPost.child("Latitud").setValue(lblLatidud);
                    newPost.child("Longitud").setValue(lblLongitud);
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
