package app.tracker.android.teamtracker.RequestParts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.tracker.android.teamtracker.MenuscreenActivity;
import com.tracker.android.teamtracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestActivity extends AppCompatActivity {

    private EditText etName, etPincode, etCity, etCount, etPartName;
    private Button btnSubmit;

    private DatabaseReference mDatabaseRef;
    FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth mauth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;

    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        etName = findViewById(R.id.et_Name);
        etCity = findViewById(R.id.et_city_name);
        etPincode = findViewById(R.id.et_pin_code);
        etCount = findViewById(R.id.et_part_count);
        etPartName = findViewById(R.id.et_part_name);
        btnSubmit = findViewById(R.id.submit);

        mauth = FirebaseAuth.getInstance();
        mCurrentUser = mauth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Requests ");
        firebaseUser = firebaseAuth.getCurrentUser();


        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Part Requests").child(firebaseUser.getUid());
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
        UploadRequest upload = new UploadRequest(etName.getText().toString(),etPartName.getText().toString(),Integer.parseInt(etCount.getText().toString()), etCity.getText().toString(),Integer.parseInt(etPincode.getText().toString()));
        String uploadId = mDatabaseRef.push().getKey();
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(getBaseContext(), MenuscreenActivity.class));

    }
}
