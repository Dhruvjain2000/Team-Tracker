package app.tracker.android.teamtracker.LocationUtil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.tracker.android.teamtracker.Model.DMS;
import com.tracker.android.teamtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CityByCoordinates extends AppCompatActivity {

    private EditText etCityName;
    private TextView tvResultsLat, tvResultsLon;
    private Button btnSubmit;
    private String lat ,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_by_coordinates);

        etCityName = findViewById(R.id.etCityName);
        tvResultsLat = findViewById(R.id.tvResultsLat);
        tvResultsLon = findViewById(R.id.tvResultsLon);
        btnSubmit = findViewById(R.id.btnSubmit);
        final String[] url = new String[1];

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName =etCityName.getText().toString();
                url[0] = "https://api.opencagedata.com/geocode/v1/json?q=" + cityName + "&key=6fedf86b146747c5bf6095d43d2cc712&language=en&pretty=1";
                makeConnection(url[0]);

            }
        });
    }

    private void makeConnection(String urlS) {

        try {
            URL url = new URL(urlS);
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(CityByCoordinates.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String ans = response.body().string();
                    try {
                         parseJson(ans);
                         tvResultsLat.setText(lat);
                         tvResultsLon.setText(lon);
                    } catch (JSONException e) {
                        tvResultsLat.setText(e.getMessage());
                        tvResultsLon.setText(e.getMessage());
                        e.getStackTrace();
                    }
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private void parseJson(String ans) throws JSONException {

        JSONObject jsonObject = new JSONObject(ans);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            JSONObject object2 = object.getJSONObject("annotations");
            JSONObject object3 = object2.getJSONObject("DMS");

            DMS obj = new DMS(object3.getString("lat"), object3.getString("lng "));
            lat = obj.getLat();
            lon = obj.getLng();
            break;
        }

    }

}
