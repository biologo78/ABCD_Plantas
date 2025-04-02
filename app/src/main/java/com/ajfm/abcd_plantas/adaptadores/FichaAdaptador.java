package com.ajfm.abcd_plantas.adaptadores;


import static android.view.Gravity.CENTER_VERTICAL;
import static com.ajfm.abcd_plantas.MainActivity.heightImg;
import static com.ajfm.abcd_plantas.MainActivity.widthImg;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.modelos.planta;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FichaAdaptador extends RecyclerView.Adapter<FichaAdaptador.FichaAdaptadorHolder> {
    private List<planta> allPlantas;
    public static int fichas = 0;


    public FichaAdaptador(List<planta> allPlantas) {
        this.allPlantas = allPlantas;
    }

    @NonNull
    @Override
    public FichaAdaptadorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.unaficha, parent, false);
          //v = LayoutInflater.from(parent.getContext()).inflate(R.layout.unaficha, parent, false);
        return new FichaAdaptadorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FichaAdaptadorHolder holder, int position) {
            holder.imprimir(allPlantas.get(position));
    }

    @Override
    public int getItemCount() {
        fichas = allPlantas.size();
        return allPlantas.size();
    }

    public static class FichaAdaptadorHolder extends RecyclerView.ViewHolder {
        private TextView nCient, nVulgar, nFamilia, nGrupo, tV1;
        private ImageView nAA, nBB;

        public FichaAdaptadorHolder(View itemView) {
            super(itemView);

            this.nCient = (TextView) itemView.findViewById(R.id.cient);
            this.nVulgar = (TextView) itemView.findViewById(R.id.vulgar);
            this.nFamilia = (TextView) itemView.findViewById(R.id.fami);
            this.nGrupo = (TextView) itemView.findViewById(R.id.grupo);
            this.nAA = (ImageView) itemView.findViewById(R.id.iV_AA);
            this.nBB = (ImageView) itemView.findViewById(R.id.iV_BB);
            this.tV1 = (TextView) itemView.findViewById(R.id.tV1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fListener.OnItemClick(v, getAdapterPosition());
                }
            });
        }

        public void imprimir(final planta estaPlanta) {
            Log.d("imprimir", estaPlanta.getmId() + "  " + estaPlanta.getmNombreCientifico());
            nCient.setGravity(CENTER_VERTICAL);
            nVulgar.setGravity(CENTER_VERTICAL);
            nFamilia.setGravity(CENTER_VERTICAL);
            nGrupo.setGravity(CENTER_VERTICAL);
            tV1.setGravity(CENTER_VERTICAL);
            int poso = getAdapterPosition() + 1;

            StringBuilder sb = new StringBuilder();
            sb.append("Ficha Id:").append(estaPlanta.getmId()).append(" Núm:").append(poso).append(" de ").append(fichas);

            this.nCient.setText(estaPlanta.getmNombreCientifico());
            this.nVulgar.setText(estaPlanta.getmNobreVulgar());
            this.nFamilia.setText(estaPlanta.getmFamilia());
            this.nGrupo.setText(estaPlanta.getmGrupo());
            this.tV1.setText(sb.toString());

            try {
                Picasso.get().load(estaPlanta.getmImgAA()).resize(widthImg, widthImg).centerCrop().into(this.nAA);
                Picasso.get().load(estaPlanta.getmImgBB()).resize(widthImg, widthImg).centerCrop().into(this.nBB);
            } catch (Exception e) {
                //errores ++;
                //btn1.setText(errores);
                Log.d("adap", "Error en identificador " + estaPlanta.getmFotoBB() + "  " + e.toString());
            }

            this.nBB.setVisibility(View.INVISIBLE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (nBB.getVisibility() == View.INVISIBLE) {
                        nBB.setVisibility(View.VISIBLE);
                        nAA.setVisibility(View.INVISIBLE);
                    } else {
                        nAA.setVisibility(View.VISIBLE);
                        nBB.setVisibility(View.INVISIBLE);
                    }
                    fListener.OnItemClick(v, getAdapterPosition());

                }
            });
        }
    }


    public interface OnItemClickListener {
        void OnItemClick(View v, int position);
    }

    public static OnItemClickListener fListener;

    public void setOnItemClickListener(OnItemClickListener onItemClick) {
        fListener = onItemClick;
    }
}
