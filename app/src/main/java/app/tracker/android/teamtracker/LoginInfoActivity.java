package app.tracker.android.teamtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import app.tracker.android.teamtracker.Adapters.LoginInfoAdapter;
import app.tracker.android.teamtracker.Model.LoginHistory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tracker.android.teamtracker.R;

import java.util.ArrayList;

public class LoginInfoActivity extends AppCompatActivity {

    private RecyclerView rvLogin;
    private FirebaseDatabase mData;
    private DatabaseReference mRef;
    private DatabaseReference mLogin;
    private ArrayList<LoginHistory> loginContents = new ArrayList<LoginHistory>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);

        mData = FirebaseDatabase.getInstance();
        mRef = mData.getReference();
        mLogin = mRef.child("Login History");
        getRequestContent();
        rvLogin = findViewById(R.id.rvLogin);
    }

    private void getRequestContent() {

        mLogin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> logHis = dataSnapshot.getChildren();
                for (DataSnapshot dsLogHis : logHis) {
                    LoginHistory loginContent = dsLogHis.getValue(LoginHistory.class);
                    loginContents.add(loginContent);
                    rvLogin.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    LoginInfoAdapter adp = new LoginInfoAdapter(loginContents, getBaseContext());
                    rvLogin.setAdapter(adp);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getBaseContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
