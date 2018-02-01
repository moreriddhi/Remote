package com.example.atharva.remote;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewLoginToSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
//    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail=(EditText)findViewById(R.id.etxtEmail);
        editTextPassword=(EditText)findViewById(R.id.etxtPassword);
        buttonLogin=(Button)findViewById(R.id.btnLogin);
        textViewLoginToSignup=(TextView)findViewById(R.id.txtLoginToSignup);
        buttonLogin.setOnClickListener(this);
        textViewLoginToSignup.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
  //      firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            Intent intent=new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if(view==buttonLogin)
        {
            userLogin();
        }
        if(view == textViewLoginToSignup)
        {
            finish();
            Intent intent = new Intent(this,SignupActivity.class);
//            intent.putExtra("UUID",firebaseUser.getUid());
            startActivity(intent);
        }
    }

    private void userLogin()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Enter your Email ID",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    finish();
                    Toast.makeText(getApplicationContext(),"Successfully Loged in",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
               //     intent.putExtra("Username",firebaseAuth.getCurrentUser().getEmail());
  //                  intent.putExtra("UUID",firebaseUser.getUid());
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
