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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        //   if(firebaseAuth.getCurrentUser() != null){


        // }

        progressDialog = new ProgressDialog(this);
        buttonSignIn = (Button)findViewById(R.id.buttonSignin);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        textViewSignup = (TextView)findViewById(R.id.textViewSignup);

        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"please enter Email",Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(password)){

            Toast.makeText(this,"please enter Password",Toast.LENGTH_SHORT).show();
            return;

        }



        progressDialog.setMessage("Loging in....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            startActivity(new Intent(LoginActivity.this,Home.class));
                        }
                        if(!task.isSuccessful()){

                            if (editTextPassword.length() < 6) {
                                editTextPassword.setError("too short password");
                            }
                            else
                            {

                                Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    }





                });
    }

    @Override
    public void onClick(View v) {

        if (v == buttonSignIn){
            userLogin();
        }

        if(v == textViewSignup){

            Intent intent = new Intent(LoginActivity.this,MainActivity2.class);
            startActivity(intent);

        }
    }
}
