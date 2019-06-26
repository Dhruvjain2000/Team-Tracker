package app.tracker.android.teamtracker.Auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tracker.android.teamtracker.R;

public class AnonymousActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous);
    }
}
