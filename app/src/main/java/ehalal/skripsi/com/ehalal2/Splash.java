package ehalal.skripsi.com.ehalal2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private final Handler mHandler = new Handler();
    private static final int duration = 5000;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);

    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StartAnimations();
        mHandler.postDelayed(mPendingLauncherRunnable, Splash.duration);
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.from_botton);
        anim.reset();
        //Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.sequential);
        anim.reset();
        Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        anim.reset();



        ImageView iv = (ImageView) findViewById(R.id.logo);

        TextView tv = (TextView) findViewById(R.id.name);
        TextView vs = (TextView) findViewById(R.id.judul);

        iv.clearAnimation();
      //  iv.startAnimation(anim2);

        vs.clearAnimation();
        vs.startAnimation(anim3);

        tv.clearAnimation();
        tv.startAnimation(anim);





    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mPendingLauncherRunnable);
    }

    private final Runnable mPendingLauncherRunnable = new Runnable() {

        public void run() {
            final Intent intent = new Intent(Splash.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

}