package app.tracker.android.teamtracker.PushNotification;

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

public class SendNotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnQuit;
    private WebView chatWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send_notification);
        btnQuit = findViewById(R.id.btnQuit);
        chatWindow = findViewById(R.id.chatWindow);

        WebSettings webSettings = chatWindow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(SendNotificationActivity.this);
        chatWindow.setWebViewClient(webViewClient);
        chatWindow.loadUrl("https://app.onesignal.com/apps/84b06347-06f3-46f4-9734-ace01303e6e3/notifications/new");

        btnQuit.setOnClickListener(this);

    }

    public class WebViewClientImpl extends WebViewClient
    {

        private Activity activity = null;

        public WebViewClientImpl(SendNotificationActivity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {

            if(url.indexOf("https://app.onesignal.com/apps/84b06347-06f3-46f4-9734-ace01303e6e3/notifications/new") > -1 ) return false;

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
        chatWindow.loadUrl("https://app.onesignal.com/apps/84b06347-06f3-46f4-9734-ace01303e6e3/notifications/new");
    }


}