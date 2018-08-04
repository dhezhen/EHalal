package ehalal.skripsi.com.ehalal2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sulistiyanto on 07/12/16.
 */

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar(@Field("no_sertifikat") String no_sertifikat,
                       @Field("jenis") String jenis,
                       @Field("nama_produk") String nama_produk,
                       @Field("nama_perusahaan") String nama_perusahaan,
                       @Field("tgl_buat") String tgl_buat,
                       @Field("exp_date") String exp_date);

    @GET("view.php")
    Call<Value> view();

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> ubah(@Field("no_sertifikat") String no_sertifikat,
                     @Field("jenis") String jenis,
                     @Field("nama_produk") String nama_produk,
                     @Field("nama_perusahaan") String nama_perusahaan,
                     @Field("tgl_buat") String tgl_buat,
                     @Field("exp_date") String exp_date);


    @FormUrlEncoded
    @POST("delete.php")
    Call<Value> hapus(@Field("no_sertifikat") String no_sertifikat);

    @FormUrlEncoded
    @POST("search.php")
    Call<Value> search(@Field("search") String search);

    @FormUrlEncoded
    @POST("scan.php")
    Call<Value> scan(@Field("scan") String scan);



}
