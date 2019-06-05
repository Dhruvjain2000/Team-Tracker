package com.example.android.teamtracker.ReportLocation;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.teamtracker.MenuscreenActivity;
import com.example.android.teamtracker.R;
import com.example.android.teamtracker.SamplePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

public class ReportLocationActivity extends LocationBaseActivity implements SamplePresenter.SampleView {
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText name, age, gender, disease, pincode, h_name, state;
    private ProgressDialog pd;
    Button report;
    private String lat = "25.2623", lng = "82.9894";

    private DatabaseReference mDatabaseRef;
    FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth mauth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;
    private SamplePresenter samplePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_location);

        report = findViewById(R.id.bt_submit);
        name = findViewById(R.id.et_Name);
        age = findViewById(R.id.et_age);
        gender = findViewById(R.id.etGender);
        disease = findViewById(R.id.et_disease);
        h_name = findViewById(R.id.et_h_name);
        pincode = findViewById(R.id.et_pincode);
        state = findViewById(R.id.et_state);

        mauth = FirebaseAuth.getInstance();
        mCurrentUser = mauth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Info");
        firebaseUser = firebaseAuth.getCurrentUser();


        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Report").child(firebaseUser.getUid());
        pd = new ProgressDialog(this);
        samplePresenter = new SamplePresenter(this);

        getLocation();


        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Upload in progress");
                pd.show();
                uploadFile();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//            mImageUri = data.getData();
//
//            Picasso.get().load(mImageUri).into(image);
//        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        UploadReport upload = new UploadReport(name.getText().toString().trim(), Integer.parseInt(age.getText().toString()), gender.getText().toString(), disease.getText().toString(), pincode.getText().toString().trim(), h_name.getText().toString(), state.getText().toString(), mCurrentUser.getUid(), lat,lng);
        String uploadId = mDatabaseRef.push().getKey();
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(ReportLocationActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(ReportLocationActivity.this, MenuscreenActivity.class));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        samplePresenter.destroy();
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return Configurations.defaultConfiguration("Gimme the permission!", "Would you mind to turn GPS on?");
    }

    @Override
    public void onLocationChanged(Location location) {
        samplePresenter.onLocationChanged(location);
    }

    @Override
    public void onLocationFailed(@FailType int failType) {
        samplePresenter.onLocationFailed(failType);
    }

    @Override
    public void onProcessTypeChanged(@ProcessType int processType) {
        samplePresenter.onProcessTypeChanged(processType);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getLocationManager().isWaitingForLocation()
                && !getLocationManager().isAnyDialogShowing()) {
            displayProgress();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        dismissProgress();
    }

    private void displayProgress() {
//        if (progressDialog == null) {
//            progressDialog = new ProgressDialog(this);
//            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
//            progressDialog.setMessage("Getting location...");
//        }
//
//        if (!progressDialog.isShowing()) {
//            progressDialog.show();
//        }
    }

    @Override
    public String getText() {
//        return locationText.getText().toString();
        return null;
    }

    @Override
    public void setText(String text) {
//        locationText.setText(text);
        lat = text;
        lng = text;

    }

    @Override
    public void updateProgress(String text) {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.setMessage(text);
//        }
    }

    @Override
    public void dismissProgress() {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
        }
    }



