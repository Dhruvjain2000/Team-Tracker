package app.tracker.android.teamtracker.ImageUpload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import app.tracker.android.teamtracker.MenuscreenActivity2;
import com.tracker.android.teamtracker.R;

public class CheckImageActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnQuit;
    private WebView chatWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_image);
        btnQuit = findViewById(R.id.btnQuit);
        chatWindow = findViewById(R.id.chatWindow);

        WebSettings webSettings = chatWindow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        CheckImageActivity.WebViewClientImpl webViewClient = new CheckImageActivity.WebViewClientImpl(CheckImageActivity.this);
        chatWindow.setWebViewClient(webViewClient);
        chatWindow.loadUrl("https://console.firebase.google.com/u/0/project/team-tracker-4a430/overview");

        btnQuit.setOnClickListener(this);

    }

    public class WebViewClientImpl extends WebViewClient
    {

        private Activity activity = null;

        public WebViewClientImpl(CheckImageActivity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {

            if(url.indexOf("https://console.firebase.google.com/u/0/project/team-tracker-4a430/overview") > -1 ) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }

    }
    @Override
    public void onClick(View view) {
//        Toast.makeText(this, "Leaving Chat room", Toast.LENGTH_SHORT).show();
        // chatWindow.getSettings().setLoadsImagesAutomatically(true);
        startActivity(new Intent(getBaseContext(), MenuscreenActivity2.class));
        chatWindow.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        chatWindow.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        chatWindow.loadUrl("https://console.firebase.google.com/u/0/project/team-tracker-4a430/overview");
    }


}