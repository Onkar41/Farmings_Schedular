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

public class MyUsersAdapter extends RecyclerView.Adapter<MyUsersAdapter.MyViewHolder>{
    private final Context context;
  private final  ArrayList<Farmers> list;
    private DatabaseReference databaseReference;

    public MyUsersAdapter( ArrayList<Farmers> list,Context context) {
        this.context = context;
        this.list = list;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("farmers");
    }

    @NonNull
    @Override
    public MyUsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users,null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUsersAdapter.MyViewHolder holder, int position) {
        Farmers farmer = list.get(position);
        holder.un.setText(farmer.getUn());
        holder.pno.setText(farmer.getPno());
        holder.email.setText(farmer.getEmail());
        String id= String.valueOf(farmer.getUn());

        holder.deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(id).removeValue();

                list.remove(farmer);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView un,pno,email;
        private final ImageView deleteuser;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            un=itemView.findViewById(R.id.un);
            pno=itemView.findViewById(R.id.pno);
            email=itemView.findViewById(R.id.email);

            deleteuser=itemView.findViewById(R.id.deleteF);

        }
    }
}