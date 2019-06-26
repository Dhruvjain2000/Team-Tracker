package app.tracker.android.teamtracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import app.tracker.android.teamtracker.ImageUpload.CheckImageActivity;
import app.tracker.android.teamtracker.LocationUtil.CityByCoordinates;
import app.tracker.android.teamtracker.Model.Item;
import app.tracker.android.teamtracker.PushNotification.SendNotificationActivity;
import com.tracker.android.teamtracker.R;
import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MenuscreenActivity2 extends AppCompatActivity implements View.OnClickListener, MenuAdapter.ItemListener, ShakeDetector.OnShakeListener {

    private static final long RIPPLE_DURATION = 250;
    Button tvSwitch;
    Button tvpaytm;
    Button tvhosp;
    Button tvsignout;
    private WebView chatWindow;
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;
    private FirebaseAuth mAuth;
    private Button btnChange;

    static int[] imageResources = new int[]{
            R.drawable.emotion,
            R.drawable.music_player,
            R.drawable.robot,
            R.drawable.project,
            R.drawable.clown,

    };
    static int[] Strings = new int[]{
            R.string.voice,
            R.string.news,
            R.string.weather,
            R.string.forum,
            R.string.buy,

    };

    Toolbar toolbar;
    FrameLayout root;
    View contentHamburger;
    static boolean shake = true;
    static int imageResourceIndex = 0;
    static int str = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuscreen2);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        mAuth = FirebaseAuth.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new Item(getString(R.string.requests), R.drawable.meeting, "#ffffff"));
        arrayList.add(new Item(getString(R.string.arrival), R.drawable.arrival, "#ffffff"));
        arrayList.add(new Item(getString(R.string.departure), R.drawable.departure, "#ffffff"));
        arrayList.add(new Item(getString(R.string.basic), R.drawable.complete, "#ffffff"));
        arrayList.add(new Item(getString(R.string.login), R.drawable.form, "#ffffff"));
        arrayList.add(new Item(getString(R.string.city), R.drawable.logo, "#ffffff"));
        arrayList.add(new Item(getString(R.string.notify), R.drawable.bell, "#ffffff"));
        arrayList.add(new Item(getString(R.string.uploaded),R.drawable.photo,"#ffffff"));



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

        View v = LayoutInflater.from(this).inflate(R.layout.guillotine, null);

        tvSwitch = v.findViewById(R.id.tvSwitch);
        tvpaytm = v.findViewById(R.id.tvpaytm);
        tvhosp = v.findViewById(R.id.tvhosp);
        tvsignout = v.findViewById(R.id.tvsignout);

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

    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public static int getString() {
        if (str >= Strings.length) str = 0;
        return Strings[str++];
    }

    public static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    public void start(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
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
//            case R.id.tvpaytm: {
//                paytm(null);
//                break;
//            }
            case R.id.tvSwitch: {
//                SignupActivity.switchNumber = 1 - SignupActivity.switchNumber;
                Toast.makeText(this, "Inside here", Toast.LENGTH_SHORT).show();

            }
            case R.id.tvhosp: {
                hosp(null);
                break;
            }
            case R.id.tvsignout: {
//                FirebaseAuth.getInstance().signOut();
                login(null);
                break;
            }
//            case R.id.change_lang : {
//                SignupActivity.switchNumber = 1 - SignupActivity.switchNumber;
//                startActivity(new Intent(getBaseContext(),DummyActivity.class));            Toast.makeText(this, "Inside here", Toast.LENGTH_SHORT).show();
//
//
//                break;
//            }
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
            ShakeDetector.destroy();
            v.setBackgroundColor(Color.rgb(255, 0, 0));

        } else {
            shake = true;
//            if (SignupActivity.switchNumber == 0)
//                Toast.makeText(this, "Shake to call enabled", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this, "सक्षम कॉल करने के लिए हिला", Toast.LENGTH_SHORT).show();
            ShakeDetector.create(this, this);
            v.setBackgroundColor(Color.parseColor("#19783b"));

        }

    }

    public void paytm(View v) {
//        if (SignupActivity.switchNumber == 0)
//            Toast.makeText(this, "Paytm ", Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(this, "Paytm ", Toast.LENGTH_SHORT).show();

//        startActivity(new Intent(this, PaytmActivity.class));
    }

    public void anonymousChat(View v) {
//        startActivity(new Intent(this, AnonymousChat.class));
    }

    @Override
    public void onItemClick(Item item) {
//        if (SignupActivity.switchNumber == 0)
//            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(getApplicationContext(), item.text + " क्लिक किया है", Toast.LENGTH_SHORT).show();

        if (item.getText().equals("Requests"))  {
            startActivity(new Intent(getBaseContext(), RequestedActivity.class));
        } else if (item.getText().equals("Arrival Reports")) {
            startActivity(new Intent(getBaseContext(), ArrivalReportsActivity.class));
        } else if (item.getText().equals("Departure Reports")) {
            startActivity(new Intent(getBaseContext(), DepartureReportsActivity.class));
        } else if (item.getText().equals("All Workers Info")) {
            startActivity(new Intent(getBaseContext(), CompleteInfoActivity.class));
        } else if (item.getText().equals("Login Report")) {
            startActivity(new Intent(getBaseContext(), LoginInfoActivity.class));
        } else if (item.getText().equals("City Identification")) {
            startActivity(new Intent(getBaseContext(),CityByCoordinates.class));
//            Toast.makeText(this, "Don't blame me ask pranav xD", Toast.LENGTH_SHORT).show();
        } else if (item.getText().equals("Requested Parts")) {
            startActivity(new Intent(this, RequestedActivity.class));
            ;
        } else if (item.getText().equals("Send Notification")) {
            startActivity(new Intent(getBaseContext(), SendNotificationActivity.class));
        }else if(item.getText().equals("Uploaded Images")){
            startActivity(new Intent(this, CheckImageActivity.class));
        }
        else {
//            if (SignupActivity.switchNumber == 0)
                Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this, "त्रुटि! नुकसान की", Toast.LENGTH_SHORT).show();
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