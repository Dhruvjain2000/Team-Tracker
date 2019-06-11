package com.example.android.teamtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.teamtracker.Adapters.ArrivedAdapter;
import com.example.android.teamtracker.Adapters.RequestedAdapter;
import com.example.android.teamtracker.Model.Initial;
import com.example.android.teamtracker.RequestParts.UploadRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArrivalReportsActivity extends AppCompatActivity {

    private RecyclerView rvArrived;
    private FirebaseDatabase mData;
    private DatabaseReference mRef;
    private DatabaseReference mArrived;
    private ArrayList<Initial> arrivedContents = new ArrayList<Initial>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival_reports);

        mData = FirebaseDatabase.getInstance();
        mRef = mData.getReference();
        mArrived = mRef.child("Initial Forms ");
        getRequestContent();
        rvArrived = findViewById(R.id.rvArrived);
    }

    private void getRequestContent() {

        mArrived.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> logHis = dataSnapshot.getChildren();
                for (DataSnapshot dsLogHis : logHis) {
                    Initial arriveContent = dsLogHis.getValue(Initial.class);
                    arrivedContents.add(arriveContent);
                    rvArrived.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    ArrivedAdapter adp = new ArrivedAdapter(arrivedContents, getBaseContext());
                    rvArrived.setAdapter(adp);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getBaseContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

