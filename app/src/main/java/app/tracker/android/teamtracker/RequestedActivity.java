package app.tracker.android.teamtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import app.tracker.android.teamtracker.Adapters.RequestedAdapter;

import com.tracker.android.teamtracker.R;

import app.tracker.android.teamtracker.RequestParts.UploadRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class  RequestedActivity extends AppCompatActivity {

    private RecyclerView rvRequested;
    private FirebaseDatabase mData;
    private DatabaseReference mRef;
    private DatabaseReference mRequested;
    private ArrayList<UploadRequest> requestedContents = new ArrayList<UploadRequest>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested);

        mData = FirebaseDatabase.getInstance();
        mRef = mData.getReference();
        mRequested = mRef.child("Requests ");
        getRequestContent();
        rvRequested = findViewById(R.id.rvRequested);
    }

    private void getRequestContent() {

        mRequested.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> logHis = dataSnapshot.getChildren();
                for (DataSnapshot dsLogHis : logHis) {
                    UploadRequest requestContent = dsLogHis.getValue(UploadRequest.class);
                    requestedContents.add(requestContent);
                    rvRequested.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    RequestedAdapter logsheetHistoryAdapter = new RequestedAdapter(requestedContents, getBaseContext());
                    rvRequested.setAdapter(logsheetHistoryAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getBaseContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
