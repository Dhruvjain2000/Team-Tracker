package com.example.android.teamtracker.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.teamtracker.MenuscreenActivity;
import com.example.android.teamtracker.R;
import com.example.android.teamtracker.RequestParts.UploadRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormInitialActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth mauth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;

    private ProgressDialog pd;

    private EditText etName, etCurrentLocation, etClientLocation, etClientName,etDate;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_initial);

        etName = findViewById(R.id.et_Name);
        etCurrentLocation = findViewById(R.id.et_current_loc);
        etClientLocation = findViewById(R.id.et_client_loc);
        etDate = findViewById(R.id.et_date);
        etClientName = findViewById(R.id.et_client_name);
        btnSubmit = findViewById(R.id.btnSubmit);

        mauth = FirebaseAuth.getInstance();
        mCurrentUser = mauth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Initial Forms ");
        firebaseUser = firebaseAuth.getCurrentUser();


        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Forms").child(firebaseUser.getUid());
        pd = new ProgressDialog(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Upload in progress");
                pd.show();
                uploadFile();
            }
        });
    }
    private void uploadFile() {
        Initial upload = new Initial(etName.getText().toString(),etCurrentLocation.getText().toString(),etClientLocation.getText().toString(), etClientName.getText().toString(),etDate.getText().toString());
        String uploadId = mDatabaseRef.push().getKey();
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(getBaseContext(), MenuscreenActivity.class));

    }
}
