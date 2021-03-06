package com.example.gorevyoneticisi.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper;
import com.example.gorevyoneticisi.Models.GorevModel;
import com.example.gorevyoneticisi.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<GorevModel> mDataset;
    private SharedPreferenceHelper.SharedName sharedName;

    private Context context;

    onDeleteClickedListener callback;

    public interface onDeleteClickedListener {
        void deleteClicked(int position);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView priorityImage;
        TextView gorevText, tarihText, priorityText;
        Button delete;

        public MyViewHolder(View itemView) {
            super(itemView);

            priorityImage = itemView.findViewById(R.id.priorityImage);
            gorevText = itemView.findViewById(R.id.gorevText);
            tarihText = itemView.findViewById(R.id.tarihText);
            priorityText = itemView.findViewById(R.id.priorityText);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public MyAdapter(Context context, List<GorevModel> myDataset, SharedPreferenceHelper.SharedName mSharedName) {
        this.context = context;
        this.callback = (onDeleteClickedListener) context;
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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(context)
                        .setTitle("Onay")
                        .setMessage("Görevi silmek istediğinize emin misiniz?")
                        .setNeutralButton("İptal", null)
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                callback.deleteClicked(position);
                            }
                        }).show();
            }
        });

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
