package com.example.farmings_schedular;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {
    private Context context;
    private ArrayList t,d,time,f_id;

    public myadapter(Context context, ArrayList t, ArrayList d, ArrayList time,ArrayList f_id) {
        this.context = context;
        this.t = t;
        this.d = d;
        this.time = time;
        this.f_id=f_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.t.setText(String.valueOf(t.get(position)));
        holder.d.setText(String.valueOf(d.get(position)));
        holder.time.setText(String.valueOf(time.get(position)));
        String id= String.valueOf(f_id.get(position));
        holder.updateremindersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), UpdateReminders.class);
                intent.putExtra("rid",id);
                context.startActivity(intent);
            }
        });
        holder.deletereminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), DeleteRemainders.class);
                intent.putExtra("rid",id);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return t.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t,d,time;
        ImageView updateremindersbtn,deletereminders;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.task);
            d=itemView.findViewById(R.id.date_update);
            time=itemView.findViewById(R.id.time_update);
            updateremindersbtn=itemView.findViewById(R.id.updateremindersbtn);
            deletereminders=itemView.findViewById(R.id.delete);
        }
    }
}
