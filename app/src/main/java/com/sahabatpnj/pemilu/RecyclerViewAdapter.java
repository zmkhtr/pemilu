package com.sahabatpnj.pemilu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List<DataRelawan> dataRelawan;

    public RecyclerViewAdapter(Context mContext, List<DataRelawan> dataRelawan) {
        this.mContext = mContext;
        this.dataRelawan = dataRelawan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_relawan, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataRelawan data = dataRelawan.get(position);
        holder.mNama.setText(data.getNamaRelawan());
        holder.mAlamat.setText(data.getAlamatRelawan());
        holder.mTanggalLahir.setText(data.getTanggalLahir());
        holder.mJenisKelamin.setText(data.getJenisKelamin());
        holder.mPartai.setText(data.getIdPartai());
    }

    @Override
    public int getItemCount() {
        return dataRelawan.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  mNama, mAlamat, mTanggalLahir, mJenisKelamin, mPartai;

        public ViewHolder(View itemView) {
            super(itemView);
            mNama = itemView.findViewById(R.id.textLayoutNama);
            mAlamat = itemView.findViewById(R.id.textLayoutAlamat);
            mTanggalLahir = itemView.findViewById(R.id.textLayoutTanggal);
            mJenisKelamin = itemView.findViewById(R.id.textLayoutJenis);
            mPartai = itemView.findViewById(R.id.textLayoutPartai);
        }
    }
}