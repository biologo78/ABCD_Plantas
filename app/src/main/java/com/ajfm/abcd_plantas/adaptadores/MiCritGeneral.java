package com.ajfm.abcd_plantas.adaptadores;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.modelos.criterioGeneral;

import java.util.List;

public class MiCritGeneral extends RecyclerView.Adapter<MiCritGeneral.ViewHolder> {

    private OnItemClickListener listener; ;
    private List<criterioGeneral> allSelectos;
    private Fragment fragment;

    public MiCritGeneral(List<criterioGeneral> allSelectos,
                         final OnItemClickListener listener,
                         final Fragment fragment) {
        this.allSelectos = allSelectos;
        this.listener = listener;
        this.fragment = fragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tVN4, tVN5, tVN6;

        public ViewHolder(@NonNull View itemView,
                          final OnItemClickListener listener
                          ) {
            super(itemView);
            this.tVN4 = (TextView) itemView.findViewById(R.id.grupo);
            this.tVN5 = (TextView) itemView.findViewById(R.id.familia);
            this.tVN6 = (TextView) itemView.findViewById(R.id.criterio);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(allSelectos.get(position), position);
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.uncriterio, parent, false);
        return new ViewHolder(itemView, listener);
    }

    public int getItemCount() {
        return allSelectos.size();
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        criterioGeneral crit = allSelectos.get(position);
        holder.tVN4.setText(crit.getGrupo());
        holder.tVN5.setText(crit.getFamilia());
        holder.tVN6.setText(crit.getCriterio());

        holder.tVN4.setGravity(Gravity.CENTER_VERTICAL);
        holder.tVN5.setGravity(Gravity.CENTER_VERTICAL);
        holder.tVN6.setGravity(Gravity.CENTER_VERTICAL);
    }

    public interface OnItemClickListener {
        void OnItemClick(criterioGeneral crit, int position);
    }

    public static MiCritGeneral.OnItemClickListener listenerb;

    public void setOnItemClickListener(MiCritGeneral.OnItemClickListener onItemClick) {
        listener = onItemClick;
    }
}

