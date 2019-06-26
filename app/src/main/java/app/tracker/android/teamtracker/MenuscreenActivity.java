package app.tracker.android.teamtracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import app.tracker.android.teamtracker.Adapters.MenuAdapter;
import app.tracker.android.teamtracker.Auth.LoginActivity;
import app.tracker.android.teamtracker.Forms.FormFinalActivity;
import app.tracker.android.teamtracker.Forms.FormInitialActivity;
import app.tracker.android.teamtracker.ImageUpload.ImageUploadActivity;
import app.tracker.android.teamtracker.LocationUtil.MyLocationUsingHelper;
import app.tracker.android.teamtracker.Model.Item;
import app.tracker.android.teamtracker.Model.LoginHistory;

import com.tracker.android.teamtracker.R;

import app.tracker.android.teamtracker.ReportLocation.ReportLocationActivity;
import app.tracker.android.teamtracker.RequestParts.RequestActivity;
import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MenuscreenActivity extends AppCompatActivity implements View.OnClickListener, MenuAdapter.ItemListener, ShakeDetector.OnShakeListener {

    private static final long RIPPLE_DURATION = 250;
    Button tvSwitch;
    Button tvpaytm;
    Button tvhosp;
    Button tvsignout;
    Button tvChangeLang ;
    private WebView chatWindow;
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;
    private FirebaseAuth mAuth;
    private Button btnChange;

    private DatabaseReference mDatabaseRef;
    FirebaseUser firebaseUser;
    private DatabaseReference mDatabaseUser;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;


//    static int[] imageResources = new int[]{
//            R.drawable.emotion,
//            R.drawable.music_player,
//            R.drawable.robot,
//            R.drawable.project,
//            R.drawable.clown,
//
//    };
//    static int[] Strings = new int[]{
//            R.string.voice,
//            R.string.news,
//            R.string.weather,
//            R.string.forum,
//            R.string.buy,
//
//    };

    Toolbar toolbar;
    FrameLayout root;
    View contentHamburger;
    static boolean shake = true;
    static int imageResourceIndex = 0;
    static int str = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuscreen);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Login History");
        firebaseUser = firebaseAuth.getCurrentUser();


        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Login").child(firebaseUser.getUid());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Valid",Context.MODE_PRIVATE);
        if(sharedPreferences != null || sharedPreferences.getInt("Valid",Context.MODE_PRIVATE) == 1) {
            SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Valid",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("Valid", 0);
            editor.commit();

            SharedPreferences sfEmail = getBaseContext().getSharedPreferences("email",Context.MODE_PRIVATE);
            String email = sfEmail.getString("email","Email Not found");
//            Toast.makeText(this, "SP check " + email, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, currentDateTimeString, Toast.LENGTH_SHORT).show();

            uploadFile(email,currentDateTimeString);
        }

            mAuth = FirebaseAuth.getInstance();
//        Toast.makeText(this, "Welcome " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new Item(getString(R.string.request), R.drawable.application, "#ffffff"));
        arrayList.add(new Item(getString(R.string.posI), R.drawable.form_i, "#ffffff"));
        arrayList.add(new Item(getString(R.string.posF), R.drawable.form_f, "#ffffff"));
        arrayList.add(new Item(getString(R.string.current), R.drawable.logo, "#ffffff"));
        arrayList.add(new Item(getString(R.string.input), R.drawable.form, "#ffffff"));
        arrayList.add(new Item(getString(R.string.upload),R.drawable.photo,"#ffffff"));

        MenuAdapter menuAdapter = new MenuAdapter(this, arrayList, this);
        recyclerView.setAdapter(menuAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Team Tracker");
        }


        root = findViewById(R.id.root);
        toolbar = findViewById(R.id.toolbar);
        contentHamburger = findViewById(R.id.content_hamburger);

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);


        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
//
//        LayoutInflater li  = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        assert li != null;
        View v = LayoutInflater.from(this).inflate(R.layout.guillotine, null);

        tvSwitch = v.findViewById(R.id.tvSwitch);
        tvpaytm = v.findViewById(R.id.tvpaytm);
        tvhosp = v.findViewById(R.id.tvhosp);
        tvsignout = v.findViewById(R.id.tvsignout);

//        btnChange = v.findViewById(R.id.change_lang);
//        btnChange.setOnClickListener(this);

        tvSwitch.setOnClickListener(this);
        tvpaytm.setOnClickListener(this);
        tvhosp.setOnClickListener(this);
        tvsignout.setOnClickListener(this);

        if (ShakeDetector.create(this, this)) {
//            final float sensibility = (float) (mSensibility.getProgress() + 10) / 10;
//            ShakeDetector.updateConfiguration(sensibility, mShakeNumber.getProgress());
//            Toast.makeText(this, "Shake to call activated", Toast.LENGTH_SHORT).show();

        } else {
//            Toast.makeText(this, "Shake to call disabled", Toast.LENGTH_SHORT).show();
        }

//        convertToHindi(SignupActivity.switchNumber);

    }

//    private void convertToHindi(int switchNumber) {
//        if (switchNumber == 0)
//            return;
//        TextView tvMenuscreenHeading = findViewById(R.id.tvMenuscreenHeading);
//        tvMenuscreenHeading.setText("जल जनित");
//        Button tvHosp = findViewById(R.id.tvhosp);
//        tvHosp.setText(getString(R.string.shake_to_call_h));
//
//        Button tvpaytm = findViewById(R.id.tvpaytm);
//        tvpaytm.setText(getText(R.string.paytm_karo_h));
//
//        Button signOut = findViewById(R.id.tvsignout);
//        signOut.setText(getString(R.string.sign_out_h));
//    }

    private void uploadFile(String email, String dateAndTime) {
        LoginHistory upload = new LoginHistory(email,dateAndTime);
        String uploadId = mDatabaseRef.push().getKey();
        mDatabaseRef.child(uploadId).setValue(upload);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public static int getString() {
//        if (str >= Strings.length) str = 0;
//        return Strings[str++];
        return 0;
    }

    public static int getImageResource() {
//        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
//        return imageResources[imageResourceIndex++];
        return 0;
    }

    public void start(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent(this, MainActivity.class);
//        startActivity(in);
    }

    public void stock(int pos) {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, ChatActivity.class);
//        startActivity(in);
    }

    public void sales(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, Spacewar.class);
//        startActivity(in);
    }

    public void buy(int pos) {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, MemeActivity.class);
//        startActivity(in);
    }

    public void anonymous(int pos) {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent(this, AnonymousChat.class);
//        startActivity(in);
    }

    public void ordering(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, MusicActivity.class);
//        startActivity(in);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvhosp: {
                hosp(null);
                break;
            }
            case R.id.tvsignout: {
//                mAuth.signOut();
//                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                login(null);
                break;
            }
       }
    }

    public void login(View v) {
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

    public void hosp(View v) {
//        startActivity(new Intent(this,NearbyHospitalsActivity.class));

        if (shake) {
            shake = false;
//            if (SignupActivity.switchNumber == 0)
//                Toast.makeText(this, "Shake to call disabled", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this, "अक्षम कॉल करने के लिए हिलाएँ", Toast.LENGTH_SHORT).show();
//            ShakeDetector.destroy();
//            v.setBackgroundColor(Color.rgb(255, 0, 0));

        } else {
            shake = true;
//            if (SignupActivity.switchNumber == 0)
//                Toast.makeText(this, "Shake to call enabled", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this, "सक्षम कॉल करने के लिए हिला", Toast.LENGTH_SHORT).show();
//            ShakeDetector.create(this, this);
//            v.setBackgroundColor(Color.parseColor("#19783b"));

        }

    }


    @Override
    public void onItemClick(Item item) {

        if (item.getText().equals("Form for initial position"))  {
            startActivity(new Intent(getBaseContext(), FormInitialActivity.class));
        } else if (item.getText().equals("Request Parts")) {
            startActivity(new Intent(getBaseContext(), RequestActivity.class));
        } else if (item.getText().equals("Form for final position")) {
            startActivity(new Intent(getBaseContext(), FormFinalActivity.class));
        } else if (item.getText().equals("Get Current Location")) {
            startActivity(new Intent(getBaseContext(), MyLocationUsingHelper.class));
        } else if (item.getText().equals("Fill Your Info")) {
            startActivity(new Intent(getBaseContext(), ReportLocationActivity.class));
        }else if (item.getText().equals("Upload Image")) {
            startActivity(new Intent(getBaseContext(), ImageUploadActivity.class));
        }
        else {
               Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnShake() {
        if (checkPhonePermission()) {
            if (shake) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8851506992"));
//                startActivity(intent);
            }

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 123);

        }

    }


    public boolean checkPhonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);
            }
            return false;

        } else
            return true;
    }

    public boolean checkReadPermissionPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            }
            return false;

        } else
            return true;
    }
}