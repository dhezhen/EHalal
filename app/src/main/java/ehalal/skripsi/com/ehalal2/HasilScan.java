package ehalal.skripsi.com.ehalal2;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HasilScan extends AppCompatActivity{

    Button btn_logout;
    TextView txt_nos,txt_jenis,txt_tglbt,txt_exp,txt_npr,txt_np;
    String no_sertifikat,jenis,nama_produk,nama_perusahaan,tgl_buat,exp_date;
    //SharedPreferences sharedpreferences;

    public final static String TAG_NOS= "no_sertifikat";
    public final static String TAG_JENIS = "jenis";
    public final static String TAG_NP= "nama_produk";
    public final static String TAG_NPR= "nama_perusahaan";
    public final static String TAG_TGLBT= "tgl_buat";
    public final static String TAG_EXP= "exp_date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_scan);

        txt_nos = (TextView) findViewById(R.id.txt_nos);
        txt_jenis = (TextView) findViewById(R.id.txt_jenis);
        txt_np = (TextView) findViewById(R.id.txt_np);
        txt_npr = (TextView) findViewById(R.id.txt_npr);
        txt_tglbt = (TextView) findViewById(R.id.txt_tglbt);
        txt_exp = (TextView) findViewById(R.id.txt_exp);


        //sharedpreferences = getSharedPreferences(Scan.my_shared_preferences, Context.MODE_PRIVATE);

        no_sertifikat = getIntent().getStringExtra(TAG_NOS);
        jenis = getIntent().getStringExtra(TAG_JENIS);
        nama_produk = getIntent().getStringExtra(TAG_NP);
        nama_perusahaan = getIntent().getStringExtra(TAG_NPR);
        tgl_buat = getIntent().getStringExtra(TAG_TGLBT);
        exp_date = getIntent().getStringExtra(TAG_EXP);

        txt_nos.setText("No Sertifikat : " + no_sertifikat);
        txt_jenis.setText(" Jenis: " + jenis);
        txt_np.setText("Nama Produk : " + nama_produk);
        txt_npr.setText("Nama Perusahaan : " + nama_perusahaan);
        txt_tglbt.setText("Tanggal Regis : " + tgl_buat);
        txt_exp.setText("Berlaku Sampai : " + exp_date);
/**
        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Scan.session_status, false);
                editor.putString(TAG_NOS, null);
                editor.putString(TAG_JENIS, null);
                editor.putString(TAG_NP, null);
                editor.putString(TAG_NPR, null);
                editor.putString(TAG_TGLBT, null);
                editor.putString(TAG_EXP, null);
                editor.commit();

                Intent intent = new Intent(HasilScan.this, Scan.class);
                finish();
            }
        });**/

    }
}
