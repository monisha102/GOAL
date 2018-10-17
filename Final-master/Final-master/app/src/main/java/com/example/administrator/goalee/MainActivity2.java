package com.example.administrator.goalee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail,inputConfirmPassword;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        inputConfirmPassword = (EditText) findViewById(R.id.conf);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        textViewSignin = (TextView)findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private  void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String conf= inputConfirmPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"please enter Email",Toast.LENGTH_SHORT).show();
            return;

        }


        if(TextUtils.isEmpty(password)){

            Toast.makeText(this,"please enter Password",Toast.LENGTH_SHORT).show();
            return;

        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(conf)){
            Toast.makeText(getApplicationContext(), "Passwords donot match!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User....");
        progressDialog.show();
        progressDialog.dismiss();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity2.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();
                        }

                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity2.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();}


                    }
                });

    }
    @Override
    public void onClick(View view) {

        if (view == buttonRegister){
            registerUser();
        }

        if(view == textViewSignin){

            Intent intent = new Intent(MainActivity2.this,LoginActivity.class);
            startActivity(intent);

        }

    }
}


