package com.example.hp.signupandsigninusingdb;

import android.content.Intent;
import android.database.Cursor;
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

public class SignIn extends AppCompatActivity {

    EditText mobinput,passinput;
    TextView showpass2;
    Button signin;
    Dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mobinput=(EditText)findViewById(R.id.mobinput);
        passinput=(EditText)findViewById(R.id.passinput);
        showpass2=(TextView) findViewById(R.id.showpasstext2);
        signin=(Button)findViewById(R.id.signin);
        db=new Dbhelper(this);

        show_hide_password2();
        signin();
    }

    public void show_hide_password2(){

        passinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(passinput.getText().length()>0)
                {
                    showpass2.setVisibility(View.VISIBLE);
                }
                else{
                    showpass2.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        showpass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showpass2.getText()=="Show"){
                    showpass2.setText("Hide");
                    passinput.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passinput.setSelection(passinput.length());
                }
                else{
                    showpass2.setText("Show");
                    passinput.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }
    public  void signin()
    {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.getAllData();
                while(res.moveToNext()){
                    if(mobinput.getText().toString().equals(res.getString(1))){
                        if(passinput.getText().toString().equals(res.getString(2)))
                        {
                            String uname=res.getString(0);
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(SignIn.this,Profile.class);
                            i.putExtra("uname",uname);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Invalid Mobile/Password..!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //    Toast.makeText(SignIn.this, "Invalid Mobile No. !!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
