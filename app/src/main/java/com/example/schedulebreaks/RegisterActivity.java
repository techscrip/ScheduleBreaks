package com.example.schedulebreaks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = findViewById(R.id.edittext_username);
        mTextPassword = findViewById(R.id.edittext_password);
        mTextCnfPassword = findViewById(R.id.edittext_cnf_password);
        mButtonRegister = findViewById(R.id.button_login);
        mTextViewLogin = findViewById(R.id.textview_register);

        mTextViewLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this,RegisterActivity.class);
                startActivity(loginIntent);
            }

        });

        mButtonRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnfpwd = mTextCnfPassword.getText().toString().trim();

                if(pwd.equals(cnfpwd)){
                    long res = db.addUser(user,pwd);
                    if(res > 0) {
                        Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(RegisterActivity.this,"Password does not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
