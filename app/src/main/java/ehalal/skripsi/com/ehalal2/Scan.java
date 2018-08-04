package ehalal.skripsi.com.ehalal2;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ehalal.skripsi.com.ehalal2.app.AppController;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final int duration = 5000;
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
    int success;
    ConnectivityManager conMgr;

    ProgressDialog pDialog;
    //Button btn_register, btn_login;
    //EditText txt_username, txt_password;
    Intent intent;

    private String url = Server.URL + "scan.php";

    private static final String TAG =Scan.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_NOS= "no_sertifikat";
    public final static String TAG_JENIS = "jenis";
    public final static String TAG_NP= "nama_produk";
    public final static String TAG_NPR= "nama_perusahaan";
    public final static String TAG_TGLBT= "tgl_buat";
    public final static String TAG_EXP= "exp_date";


    String tag_json_obj = "json_obj_req";

   // SharedPreferences sharedpreferences;
    //Boolean session = false;
    String no_sertifikat,jenis,nama_produk,nama_perusahaan,tgl_buat,exp_date;
  //  public static final String my_shared_preferences = "my_shared_preferences";
    //public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);

/**
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "Cek Koneksi Anda",
                        Toast.LENGTH_LONG).show();
            }
        }
 */
        //adapter view
/**
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        no_sertifikat = sharedpreferences.getString(TAG_NOS, null);
        no_sertifikat= sharedpreferences.getString(TAG_USERNAME, null);

        if (session) {
            Intent intent = new Intent(Scan.this, MainActivity.class);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_USERNAME, username);
            finish();
            startActivity(intent);
        }
**/




        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }
/**
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(Scan.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
 */
    @Override
    public void handleResult(Result result) {
        final String myResult = result.getText();
        Log.d("QRCodeScanner", result.getText());
        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());





        Scan.this.no_sertifikat= result.getText().toString();
        //String password = txt_password.getText().toString();
        checkHasil();
        new AlertDialog.Builder(Scan.this).setMessage(no_sertifikat).show();
        //Toast.makeText(Scan.this,no_sertifikat,Toast.LENGTH_LONG);






/**
 AlertDialog.Builder builder = new AlertDialog.Builder(this);
 builder.setTitle("Scan Result");
 builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
scannerView.resumeCameraPreview(Scan.this);
}
});
 builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myResult));
startActivity(browserIntent);
}
});

 builder.setMessage(result.getText());
 AlertDialog alert1 = builder.create();
 alert1.show();
 }


 */
    }



    private void checkHasil() {
        Intent intent;
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
     //   pDialog.setIcon(R.drawable.logo);
        pDialog.setMessage("Cek dan deskripsi data Ke server  ...");
        pDialog.setMax(500);
        showDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Data Response: " + response.toString());
              //  Toast.makeText(Scan.this, response, Toast.LENGTH_LONG).show();

                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);
                   success = jObj.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        MediaPlayer music = MediaPlayer.create(Scan.this, R.raw.bip);
                        music.start();

                        String no_sertifikat = jObj.getString(TAG_NOS);
                        String jenis = jObj.getString(TAG_JENIS);
                        String nama_produk = jObj.getString(TAG_NP);
                        String nama_perusahaan = jObj.getString(TAG_NPR);
                        String tgl_buat = jObj.getString(TAG_TGLBT);
                        String exp_date = jObj.getString(TAG_EXP);

                        Log.e("Data Halal Valid!", jObj.toString());

                       // Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                        // Memanggil main activity
                        Intent intent = new Intent(Scan.this, HasilScan.class);
                        intent.putExtra(TAG_NOS, no_sertifikat);
                        intent.putExtra(TAG_JENIS, jenis);
                        intent.putExtra(TAG_NP, nama_produk);
                        intent.putExtra(TAG_NPR, nama_perusahaan);
                        intent.putExtra(TAG_TGLBT, tgl_buat);
                        intent.putExtra(TAG_EXP, exp_date);
                        finish();
                        startActivity(intent);
                    }
                    else {
                        //Toast.makeText(getApplicationContext(),jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        MediaPlayer music2 = MediaPlayer.create(Scan.this, R.raw.beep4);
                        //music.start();
                        music2.start();

                        AlertDialog alertDialog = new AlertDialog.Builder(Scan.this).create();
                        alertDialog.setTitle("Hasil Scan");
                        alertDialog.getActionBar();
                        alertDialog.setIcon(R.drawable.nonhalal);
                        alertDialog.setMessage(jObj.getString(TAG_MESSAGE));
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Scan Lagi",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        scannerView.resumeCameraPreview(Scan.this);
                                        pDialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Data Tidak Valid: " + error.getMessage());
                           Toast.makeText(getApplicationContext(),  error.getMessage(), Toast.LENGTH_LONG).show();

                          hideDialog();






            }
        })


        {


            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("no_sertifikat",Scan.this.no_sertifikat);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);




    }




    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}