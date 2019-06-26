package app.tracker.android.teamtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tracker.android.teamtracker.R;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        Toast.makeText(this, "Dummy called", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MenuscreenActivity.class));
        finish();
    }
}
