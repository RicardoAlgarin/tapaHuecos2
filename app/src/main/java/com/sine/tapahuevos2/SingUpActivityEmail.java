package com.sine.tapahuevos2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpActivityEmail extends AppCompatActivity implements View.OnClickListener {

    private Button final_btn_register;
    private EditText mEditTextemail;
    private EditText mEditTextpassword;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_email);

        mFirebaseAuth = FirebaseAuth.getInstance();

        String nombre = getIntent().getStringExtra("username");

        TextView musername = (TextView) findViewById(R.id.username_greetings);
        musername.setText(nombre+",");


        final_btn_register = (Button) findViewById(R.id.final_btn_register);
        mEditTextemail = (EditText) findViewById(R.id.editTextemail);
        mEditTextpassword = (EditText) findViewById(R.id.editTextpassword);

        final_btn_register.setOnClickListener(this);
    }

    private void registerUser(){

        String email = mEditTextemail.getText().toString().trim();
        String password = mEditTextpassword.getText().toString().trim();
        mProgressDialog = new ProgressDialog(this);

        if (TextUtils.isEmpty(email)){
            Toast.makeText(SingUpActivityEmail.this, "Ingresa el email por favor",Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(password)){

            Toast.makeText(SingUpActivityEmail.this, "Ingresa la contraseña para continuar",Toast.LENGTH_LONG).show();
            return;

        }
        mProgressDialog.setMessage("Espera un momento...");
        mProgressDialog.show(this,"Registro","Espere un momento",false,true);



        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SingUpActivityEmail.this, "Registro Exitoso",Toast.LENGTH_LONG).show();

                        }
                        else {
                            Toast.makeText(SingUpActivityEmail.this, "Ups! ocurrio un problema, intenta de nuevo",Toast.LENGTH_LONG).show();

                        }
                    }
                });
                }



    @Override
    public void onClick(View v) {

        if(v == final_btn_register){

            registerUser();

        }

    }
}