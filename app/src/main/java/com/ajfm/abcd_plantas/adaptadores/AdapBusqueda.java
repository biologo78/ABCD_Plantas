package com.ajfm.abcd_plantas.adaptadores;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.modelos.planta;

import java.util.List;

public class AdapBusqueda extends RecyclerView.Adapter<AdapBusqueda.AdapBusquedaHolder> {
    private final List<planta> plantasToas;

    public AdapBusqueda(List<planta> plantasToas) {
        this.plantasToas = plantasToas;
    }

    @NonNull
    @Override
    public AdapBusquedaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.unitem, parent, false);
        AdapBusquedaHolder vh = new AdapBusquedaHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapBusquedaHolder holder, int position) {
            holder.imprimir(plantasToas.get(position));
    }

    @Override
    public int getItemCount() {
        return plantasToas.size();
    }

    public static class AdapBusquedaHolder extends RecyclerView.ViewHolder {

        private final TextView tVN1, tVN2, tVN3, tVN4;

        public AdapBusquedaHolder(@NonNull View itemView) {
            super(itemView);
            this.tVN1 = (TextView) itemView.findViewById(R.id.tVN1);
            this.tVN2 = (TextView) itemView.findViewById(R.id.tVN2);
            this.tVN3 = (TextView) itemView.findViewById(R.id.tVN3);
            this.tVN4 = (TextView) itemView.findViewById(R.id.tVN4);

           itemView.setOnClickListener(new View.OnClickListener() {
           @Override
                public void onClick(View v) {

               gListener.OnItemClick(v,getAdapterPosition());
                }
            });
        }

        public void imprimir(planta planta) {
            tVN1.setGravity(Gravity.CENTER_VERTICAL);
            tVN2.setGravity(Gravity.CENTER_VERTICAL);
            tVN3.setGravity(Gravity.CENTER_VERTICAL);
            tVN4.setGravity(Gravity.CENTER_VERTICAL);

            this.tVN1.setText(planta.getmNombreCientifico());
            this.tVN2.setText(planta.getmNobreVulgar());
            this.tVN3.setText(planta.getmFamilia());
            this.tVN4.setText(planta.getmGrupo());
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View v, int position);
    }

    private static OnItemClickListener gListener;

    public void setOnItemClickListener(OnItemClickListener onItemClick) {
       gListener = onItemClick;
    }
}
