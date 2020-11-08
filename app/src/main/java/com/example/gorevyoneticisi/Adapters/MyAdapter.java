package com.example.gorevyoneticisi.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper;
import com.example.gorevyoneticisi.Models.GorevModel;
import com.example.gorevyoneticisi.R;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<GorevModel> mDataset;
    private SharedPreferenceHelper.SharedName sharedName;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, priorityImage;
        TextView gorevText, tarihText, priorityText;
        Button delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            priorityImage = itemView.findViewById(R.id.priorityImage);
            gorevText = itemView.findViewById(R.id.gorevText);
            tarihText = itemView.findViewById(R.id.tarihText);
            priorityText = itemView.findViewById(R.id.priorityText);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public MyAdapter(List<GorevModel> myDataset, SharedPreferenceHelper.SharedName mSharedName) {
        mDataset = myDataset;
        sharedName = mSharedName;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.gorevText.setText(mDataset.get(position).getGorev());

        holder.tarihText.setText(getTime(mDataset.get(position).getTarih()));
        if (mDataset.get(position).isDateExpired(sharedName)) {
            holder.tarihText.setTextColor(holder.tarihText.getContext().getResources().getColor(R.color.red));
        } else {
            holder.tarihText.setTextColor(holder.tarihText.getContext().getResources().getColor(R.color.green));
        }
        switch (mDataset.get(position).getPriority()) {
            case DUSUK:
                holder.priorityText.setText("DÜŞÜK");
                holder.priorityImage.setBackgroundResource(R.drawable.ic_action_downward);
                break;
            case ORTA:
                holder.priorityText.setText("ORTA");
                holder.priorityImage.setBackgroundResource(R.drawable.ic_action_right);
                break;
            case YUKSEK:
                holder.priorityText.setText("YÜKSEK");
                holder.priorityImage.setBackgroundResource(R.drawable.ic_action_upward);
                break;
        }
    }

    private String getTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yy HH:mm", Locale.getDefault());
        return df.format(date);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
