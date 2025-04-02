package com.ajfm.abcd_plantas.adaptadores;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.modelos.genero;

import java.util.List;

public class AdaptadorGenero extends RecyclerView.Adapter<AdaptadorGenero.AdaptadorGeneroHolder> {
    private List<genero> generoSelec;
     public static String gener;

    public AdaptadorGenero(List<genero> generoSelec) {
        this.generoSelec = generoSelec;
    }

    @NonNull
    @Override
    public AdaptadorGeneroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ungenero, parent, false);
        AdaptadorGeneroHolder vh = new AdaptadorGeneroHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorGeneroHolder holder, int position) {
        holder.imprimir(generoSelec.get(position));
    }

    @Override
    public int getItemCount() {
        return generoSelec.size();
    }


    public static class AdaptadorGeneroHolder extends RecyclerView.ViewHolder {
        TextView tGyE;

        public AdaptadorGeneroHolder(View itemView) {
            super(itemView);
            this.tGyE = (TextView) itemView.findViewById(R.id.tGyE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gListener.OnItemClick(v, getAdapterPosition());
                }
            });
        }

        public void imprimir(genero gener) {
            String texto = "";
            if (gener.getgSubespecie() != null) {
                texto = gener.getgGeneroyFamilia() + " " + gener.getgSubespecie();
            } else {
                texto = gener.getgGeneroyFamilia();
            }
            this.tGyE.setText(texto);

            tGyE.setGravity(Gravity.CENTER_VERTICAL);
            tGyE.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gListener.OnItemClick(v,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View v, int position);
    }

    private static OnItemClickListener gListener;

    public void setOnItemClickListener(OnItemClickListener onItemClick) {
        AdaptadorGenero.gListener = onItemClick;
    }

}
