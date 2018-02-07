package com.example.atharva.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewUUID;
    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;
    private Button buttonRegisterPi;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewUUID=(TextView)findViewById(R.id.txtProfileUUID);
        buttonLogout=(Button)findViewById(R.id.btnLogout);
        buttonRegisterPi=(Button)findViewById(R.id.btnSetPiId);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Pi-IDs");
        if(user==null)
        {
            finish();
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        textViewUUID.setText("User Email : " + user.getEmail().toString()+"\n"+"UUID : " + user.getUid().toString());
        buttonLogout.setOnClickListener(this);
        buttonRegisterPi.setOnClickListener(this);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                updateRemoteState(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        if(view==buttonRegisterPi)
        {
            Intent intent=new Intent(this,RegisterPiActivity.class);
            startActivity(intent);
        }
    }

    public void updateRemoteState(DataSnapshot dataSnapshot)
    {
        String id="pi-1";
        userInfo userinf=new userInfo();
        userinf.setPiId(dataSnapshot.child(id).getValue(userInfo.class).getPiId());
        Log.d("TAG : ",userinf.getPiId());
        Toast.makeText(this,userinf.getPiId(),Toast.LENGTH_SHORT).show();
 /*       for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            userInfo userinf=new userInfo();
            userinf.setPiId(ds.child(id).getValue(userInfo.class).getPiId());
        }*/
    }
}
