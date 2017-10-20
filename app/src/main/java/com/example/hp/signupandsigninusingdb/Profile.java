package com.example.hp.signupandsigninusingdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle ex=getIntent().getExtras();
        String uname=ex.getString("uname");
        t=(TextView)findViewById(R.id.unametextview);
        t.setText(uname);

    }
}
