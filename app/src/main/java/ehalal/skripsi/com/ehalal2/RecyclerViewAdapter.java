package ehalal.skripsi.com.ehalal2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sulistiyanto on 07/12/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.textViewNOS.setText(result.getNo_sertifikat());
        holder.textViewJN.setText(result.getJenis());
        holder.textViewNP.setText(result.getNama_produk());
        holder.textViewNPR.setText(result.getNama_perusahaan());
        holder.textViewTB.setText(result.getTgl_buat());
        holder.textViewED.setText(result.getExp_date());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.textNOS)
        TextView textViewNOS;
        @BindView(R.id.textJN)
        TextView textViewJN;
        @BindView(R.id.textNP)
        TextView textViewNP;
        @BindView(R.id.textNPR)
        TextView textViewNPR;
        @BindView(R.id.textTB)
        TextView textViewTB;
        @BindView(R.id.textED)
        TextView textViewED;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
/**
        @Override
        public void onClick(View view) {
            String no_sertifikat = textViewNOS.getText().toString();
            String jenis = textViewNP.getText().toString();
            String nama_produk = textViewNP.getText().toString();
            String nama_perusahaan = textViewNPR.getText().toString();
            String tgl_buat = textViewTB.getText().toString();
            String exp_date = textViewED.getText().toString();

            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("no_sertifikat", no_sertifikat);
            i.putExtra("jenis", jenis);
            i.putExtra("nama_produk", nama_produk);
            i.putExtra("nama_perusahaan", nama_perusahaan);
            i.putExtra("tgl_buat", tgl_buat);
            i.putExtra("exp_date", exp_date);
            context.startActivity(i);
        }
        **/

    }
}
