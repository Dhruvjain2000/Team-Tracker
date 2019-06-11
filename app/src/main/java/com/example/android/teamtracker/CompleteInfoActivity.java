package com.example.android.teamtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.teamtracker.Adapters.ArrivedAdapter;
import com.example.android.teamtracker.Adapters.CompleteInfoAdapter;
import com.example.android.teamtracker.Model.Initial;
import com.example.android.teamtracker.Model.UploadReport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompleteInfoActivity extends AppCompatActivity {

    private RecyclerView rvUploaded;
    private FirebaseDatabase mData;
    private DatabaseReference mRef;
    private DatabaseReference mUploaded;
    private ArrayList<UploadReport> uploadedContents = new ArrayList<UploadReport>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_info);


        mData = FirebaseDatabase.getInstance();
        mRef = mData.getReference();
        mUploaded = mRef.child("Info");
        getRequestContent();
        rvUploaded = findViewById(R.id.rvUploaded);
    }

    private void getRequestContent() {

        mUploaded.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> logHis = dataSnapshot.getChildren();
                for (DataSnapshot dsLogHis : logHis) {
                    UploadReport uploadReport = dsLogHis.getValue(UploadReport.class);
                    uploadedContents.add(uploadReport);
                    rvUploaded.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    CompleteInfoAdapter adp = new CompleteInfoAdapter(uploadedContents, getBaseContext());
                    rvUploaded.setAdapter(adp);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getBaseContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
