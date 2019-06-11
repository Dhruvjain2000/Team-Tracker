package com.example.android.teamtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class BoardingActivity extends AhoyOnboarderActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getBaseContext();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("First Time",Context.MODE_PRIVATE);
//
//        if(sharedPreferences == null || sharedPreferences.getInt("First Time",Context.MODE_PRIVATE) == 0){
//            SharedPreferences sharedPref = getBaseContext().getSharedPreferences("First Time",Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putInt("First Time", 1);
//            editor.commit();
//        }
//        else{
//            startActivity(new Intent(getBaseContext(),SplashActivity.class));
//        }

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Coordination", "We seek to coordinate you with the admin 24 x 7. With our realtime database all your queries are processed instantaneously !", R.drawable.develop);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Location monitoring", "Be it any city or village ! We keep your location updated along with your longitude and latitude", R.drawable.map);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Request Parts", "Fell short of any equipment ? Now you can request part and ping directly to the admin", R.drawable.application);
        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Report Generation", "All reports at disposal ! With our realtime database get your report in no time", R.drawable.newspaper);


        ahoyOnboarderCard1.setTitleColor(android.R.color.black);
        ahoyOnboarderCard1.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard2.setTitleColor(android.R.color.black);
        ahoyOnboarderCard2.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard3.setTitleColor(android.R.color.black);
        ahoyOnboarderCard3.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard4.setTitleColor(android.R.color.black);
        ahoyOnboarderCard4.setDescriptionColor(android.R.color.black);


        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);


        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);


        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setImageBackground(R.drawable.boarding);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(getBaseContext(),SplashActivity.class));
    }
}
