package ehalal.skripsi.com.ehalal2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ImageButton about = (ImageButton) findViewById(R.id.about);
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );


        setContentView(R.layout.activity_main);




    }



    public void about (View v){

        startActivity(new Intent(MainActivity.this,About.class));
    }


    public void scan (View v){

        startActivity(new Intent(MainActivity.this,Scan.class));
    }


    public void input (View v){

        startActivity(new Intent(MainActivity.this,ViewActivity.class));
    }


    public void news (View v){

        startActivity(new Intent(MainActivity.this,News.class));
    }




}
