package com.example.atharva.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPiActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextPiId;
    private Button buttonRegPiId;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pi);
        editTextPiId=(EditText)findViewById(R.id.etxtPiId);
        buttonRegPiId=(Button)findViewById(R.id.btnRegisterPiId);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Pi-IDs");
        buttonRegPiId.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==buttonRegPiId)
        {
            saveUserInfo();
        }

    }

    private void saveUserInfo()
    {
        String piId = editTextPiId.getText().toString().trim();
     //   Toast.makeText(this, piId, Toast.LENGTH_SHORT).show();
        if (!TextUtils.isEmpty(piId)) {
            //String id=databaseReference.push().getKey();
            userInfo userInf = new userInfo(piId);
            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            databaseReference.child(piId).setValue(userInf);
            Toast.makeText(this, "Succesful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Enter a pi ID", Toast.LENGTH_SHORT).show();
        }
    }
}
