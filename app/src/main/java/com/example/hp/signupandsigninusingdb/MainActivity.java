package com.example.hp.signupandsigninusingdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameinput,mobileinput,pass,confpass;
    TextView showpass;
    Button signup;
    private String Spass,cpass;
    Dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameinput= (EditText) findViewById(R.id.nameinput);
        mobileinput= (EditText) findViewById(R.id.mobileinput);
        pass= (EditText) findViewById(R.id.passinput);
        confpass= (EditText) findViewById(R.id.confirmpassinput);
        showpass=(TextView)findViewById(R.id.showpasstext);
        signup=(Button)findViewById(R.id.signup);
        db=new Dbhelper(this);
        showpass.setVisibility(View.GONE);
        pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

        show_hide_password();
        signup();  //button click

    }

    public void show_hide_password(){

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(pass.getText().length()>0)
                {
                    showpass.setVisibility(View.VISIBLE);
                }
                else{
                    showpass.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showpass.getText()=="Show"){
                    showpass.setText("Hide");
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass.setSelection(pass.length());
                }
                else{
                    showpass.setText("Show");
                    pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }

    public void signup(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameinput.getText().length()>0&&mobileinput.getText().length()>0){

                    Spass=pass.getText().toString().trim();
                    cpass=confpass.getText().toString().trim();
                    if(Spass.equals(cpass))
                    {
                        boolean IsInserted=db.insertinfo(nameinput.getText().toString(),mobileinput.getText().toString(),Spass);
                        if(IsInserted==true) {
                            Toast.makeText(MainActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,SignIn.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(MainActivity.this, "This Mobile No. is Already Registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Passwords Do not match",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(MainActivity.this, "Fill in the complete details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
