package com.ciktech.portalcovid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ciktech.portalcovid.R;
import com.ciktech.portalcovid.models.Prov;

import java.util.ArrayList;

public class RecyclerReg extends RecyclerView.Adapter<RecyclerReg.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Prov> results;

    public RecyclerReg(Context context, ArrayList<Prov> results) {
        inflater = LayoutInflater.from(context);
        this.results = results;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardprov, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.provinsi.setText(results.get(position).getProvinsi());
        holder.positift.setText(results.get(position).getKasus_Posi());
        holder.sembuht.setText(results.get(position).getKasus_Semb());
        holder.matit.setText(results.get(position).getKasus_Meni());
    }

    @Override
    public int getItemCount() {

        return results.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView provinsi,sembuht,matit,positift;

        public ViewHolder(View itemView) {
            super(itemView);


            provinsi = (TextView)itemView.findViewById(R.id.prov);
            sembuht = (TextView)itemView.findViewById(R.id.sembuh);
            matit =(TextView)itemView.findViewById(R.id.mati);
            positift=(TextView)itemView.findViewById(R.id.positif);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
