package com.example.farmings_schedular.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmings_schedular.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class AdapterForSuggestions extends RecyclerView.Adapter<AdapterForSuggestions.MyViewHolder> {
    private final Context context;
    private final  ArrayList<ListSuggestions> list;
    private DatabaseReference databaseReference;

    public AdapterForSuggestions(ArrayList<ListSuggestions> list, Context context) {
        this.context = context;
        this.list = list;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Suggestions");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestionlistadmin,null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListSuggestions suggestions = list.get(position);
        holder.Cname.setText(suggestions.getC_id());
        holder.Disease.setText(suggestions.getDisease());
        holder.Precaution.setText(suggestions.getPrecaution());
        String id= String.valueOf(suggestions.getId());

        holder.deletesuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(id).removeValue();

                list.remove(suggestions);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView Cname,Disease,Precaution;
        private final ImageView deletesuggestion;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Cname=itemView.findViewById(R.id.c_namelistadmin);
            Disease=itemView.findViewById(R.id.disease1admin);
            Precaution=itemView.findViewById(R.id.precadmin);
            deletesuggestion=itemView.findViewById(R.id.deleteS);
        }
    }
}
