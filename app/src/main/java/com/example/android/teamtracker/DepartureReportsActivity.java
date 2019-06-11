package com.example.android.teamtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.teamtracker.Adapters.ArrivedAdapter;
import com.example.android.teamtracker.Adapters.DepartedAdapter;
import com.example.android.teamtracker.Model.Final;
import com.example.android.teamtracker.Model.Initial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DepartureReportsActivity extends AppCompatActivity {


    private RecyclerView rvDeparted;
    private FirebaseDatabase mData;
    private DatabaseReference mRef;
    private DatabaseReference mDeparted;
    private ArrayList<Final> departedContents = new ArrayList<Final>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departure_reports);


        mData = FirebaseDatabase.getInstance();
        mRef = mData.getReference();
        mDeparted = mRef.child("Final Forms");
        getRequestContent();
        rvDeparted = findViewById(R.id.rvDeparted);
    }

    private void getRequestContent() {

        mDeparted.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> logHis = dataSnapshot.getChildren();
                for (DataSnapshot dsLogHis : logHis) {
                    Final departContent = dsLogHis.getValue(Final.class);
                    departedContents.add(departContent);
                    rvDeparted.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    DepartedAdapter adp = new DepartedAdapter(departedContents, getBaseContext());
                    rvDeparted.setAdapter(adp);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getBaseContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
