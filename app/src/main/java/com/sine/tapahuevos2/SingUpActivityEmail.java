package com.sine.tapahuevos2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SingUpActivityEmail extends AppCompatActivity {

    private Button memail_next_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_email);


        memail_next_btn2 = (Button) findViewById(R.id.email_next_btn2);

        memail_next_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
            }
        });





    }
}
