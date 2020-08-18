package com.example.shah_koti_dhaba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText username,password,confpassword;
    Button regiter_but;
    TextView login;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        databaseHelper=new DatabaseHelper(this);
        username=(EditText)findViewById(R.id.username_register);
        password=(EditText)findViewById(R.id.password_register);
        confpassword=(EditText)findViewById(R.id.confirm);
        regiter_but=(Button)findViewById(R.id.register_but);
        login=(TextView)findViewById(R.id.login_text);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Login_Activity.class);
                startActivity(intent);
            }
        });
  regiter_but.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          String user=username.getText().toString().trim();
          String pswd=password.getText().toString().trim();
          String confpaswd=confpassword.getText().toString().trim();

          if(pswd.equals(confpaswd) && password!=null)
          {
              long val=databaseHelper.addUser(user,pswd);
              if(val > 2){
                  Toast.makeText(Register.this, "Successfully  Regitered", Toast.LENGTH_LONG).show();
                  Intent movetologin = new Intent(Register.this, Login_Activity.class);
                  startActivity(movetologin);

              }else{
                  Toast.makeText(Register.this, " Regitered Error / Maybe you haven't enter anything ", Toast.LENGTH_LONG).show();
              }
          }else{
              Toast.makeText(Register.this,"Password Not Matched",Toast.LENGTH_LONG).show();
          }

      }
  });

}}
