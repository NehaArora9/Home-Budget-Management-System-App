package com.example.cse226ca1;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class P01customadapter extends RecyclerView.Adapter<P01customadapter.MyViewHolder> {

    Context ct;
    ArrayList<P01Item> al;

    P01customadapter(Context ct, ArrayList<P01Item> al) {
        this.ct = ct;
        this.al = al;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(ct);
        View v1 = li.inflate(R.layout.p1recyclerviewlayoutdesign,parent,false);
        return new MyViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        P01Item p = al.get(position);
        holder.tvName.setText(p.getName());
        holder.tvReg.setText(p.getReg());
        holder.img1.setImageResource(p.getImage());

    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvReg;
        ImageView img1,imgRemove;
        LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvnameid);
            tvReg = (TextView) itemView.findViewById(R.id.tvregid);
            img1 = (ImageView) itemView.findViewById(R.id.ivid);
            imgRemove = (ImageView) itemView.findViewById(R.id.ivremoveid);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.llid);
            imgRemove.setVisibility(View.INVISIBLE);

            linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    imgRemove.setVisibility(View.VISIBLE);
                    imgRemove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            delete(getAdapterPosition());
                            imgRemove.setVisibility(View.INVISIBLE);
                        }
                    });
                    return true;
                }
            });

           linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View view) {
                   Intent i =new Intent(ct,P01NextPage.class);
                   i.putExtra("name",tvName.getText());
                   ct.startActivity(i);
                }
            });
        }

        public void delete(int position) {
            Toast.makeText(ct, "deleting position is "+position, Toast.LENGTH_SHORT).show();
            al.remove(position);
            notifyDataSetChanged();
        }
    }
}