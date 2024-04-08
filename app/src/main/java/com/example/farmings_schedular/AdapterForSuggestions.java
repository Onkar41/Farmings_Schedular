package com.example.farmings_schedular;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import java.util.ArrayList;


public class AdapterForSuggestions extends RecyclerView.Adapter<AdapterForSuggestions.MyViewHolder> {
    private final Context context;
    private final  ArrayList<ListSuggestions> list;

    public AdapterForSuggestions(ArrayList<ListSuggestions> list,Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterForSuggestions.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestionsist,null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForSuggestions.MyViewHolder holder, int position) {
        ListSuggestions suggestions = list.get(position);
        holder.Cname.setText(suggestions.getC_id());
        holder.Disease.setText(suggestions.getDisease());
        holder.Precaution.setText(suggestions.getPrecaution());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView Cname,Disease,Precaution;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Cname=itemView.findViewById(R.id.c_namelist);
            Disease=itemView.findViewById(R.id.disease1);
            Precaution=itemView.findViewById(R.id.prec);

        }
    }
}
