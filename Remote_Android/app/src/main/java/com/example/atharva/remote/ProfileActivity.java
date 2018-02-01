package com.example.atharva.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewUUID;
    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewUUID=(TextView)findViewById(R.id.txtProfileUUID);
        buttonLogout=(Button)findViewById(R.id.btnLogout);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user==null)
        {
            finish();
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        textViewUUID.setText("User Email : " + user.getEmail().toString()+"\n"+"UUID : " + user.getUid().toString());
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==buttonLogout)
        {
            finish();
            firebaseAuth.signOut();
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
