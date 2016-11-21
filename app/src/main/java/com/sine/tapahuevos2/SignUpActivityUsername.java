package com.sine.tapahuevos2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivityUsername extends AppCompatActivity {

    private Button mUsername_next_btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upusername);

       mUsername_next_btn1 = (Button) findViewById(R.id.username_next_btn1) ;

        mUsername_next_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignUpActivityUsername.this, SingUpActivityEmail.class));

            }
        });

    }
}
