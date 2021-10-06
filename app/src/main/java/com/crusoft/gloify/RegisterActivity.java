package com.crusoft.gloify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends Activity {

    private TextInputEditText username,password,email,fullname,repassword;
    private Button Register;
    private RadioGroup radioGroup;
    private DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = (TextInputEditText) findViewById(R.id.ed_fullname);
        username =(TextInputEditText) findViewById(R.id.ed_register_username);
        email =(TextInputEditText) findViewById(R.id.ed_email);
        password = (TextInputEditText) findViewById(R.id.ed_register_paswd);
        repassword = (TextInputEditText) findViewById(R.id.ed_register_retype_paswd);
        radioGroup = (RadioGroup) findViewById(R.id.gender);
        Register = findViewById(R.id.sign_up_button);
        db= new DBHelper(this);
        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        final RadioButton radioButton = (RadioButton) findViewById(selectedId);


        Register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String user_fullname = fullname.getText().toString();
                String user_username = username.getText().toString();
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                String user_repassword = repassword.getText().toString();
                String user_radioutton = radioButton.getText().toString();
                Log.e("User Data","user fullname" + user_fullname + "" + user_username + " " + user_email + "" +
                        user_password  + " " + user_repassword + " " + user_radioutton);
                if(user_fullname.equals("")||user_username.equals("")||user_email.equals("")||user_password.equals("")||
                        user_repassword.equals("")||user_radioutton.equals("")){
                    Toast a = Toast.makeText(RegisterActivity.this,"Please enter the details",Toast.LENGTH_SHORT);a.show();
                }else{
                    if(user_password.equals(user_repassword)){
                        Boolean checkuser = db.checkUsername(user_username);
                        if(checkuser == false){
                            Boolean insert = db.insertData(user_username,user_password);
                            if(insert == true){
                                Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(RegisterActivity.this,"Register Unsuccessfull",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this,"User already exist ",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"Password not matching",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
