package com.crusoft.gloify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends Activity {

    private TextInputEditText username,passwd;
    private Button Login;
    private TextView signup;
    private DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username =(TextInputEditText) findViewById(R.id.ed_username);
        passwd = (TextInputEditText) findViewById(R.id.ed_passwd);
        Login =  findViewById(R.id.sign_in);
        signup = findViewById(R.id.sign_up);
        db= new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
              startActivity(i);
          }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(LoginActivity.this,"checking signin",Toast.LENGTH_SHORT).show();

                String user_username = username.getText().toString();
                String user_password = passwd.getText().toString();
                //Toast.makeText(LoginActivity.this,"username" + user_username + "password" + user_password,Toast.LENGTH_SHORT).show();

                if(user_username.equals("")||user_password.equals("")){
                    Toast.makeText(LoginActivity.this,"Please enter the details",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass = db.checkUsernamepasswd(user_username,user_password);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Sign in successful",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
