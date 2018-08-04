package ehalal.skripsi.com.ehalal2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class News extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_fragment);

/**
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
**/

        WebView view = (WebView) this.findViewById(R.id.webview1);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());
        view.loadUrl("https://www.halalmui.org");
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setSupportZoom(true);




    }


    private class MyBrowser extends WebViewClient{

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }
}