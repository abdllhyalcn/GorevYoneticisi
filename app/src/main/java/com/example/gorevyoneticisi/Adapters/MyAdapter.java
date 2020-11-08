package com.example.gorevyoneticisi.Adapters;

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
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<GorevModel> mDataset;
    private SharedPreferenceHelper.SharedName sharedName;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        MaterialCardView cardView;
        ImageView imageView, priorityImage;
        TextView gorevText, tarihText, priorityText;
        Button delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);
            priorityImage = itemView.findViewById(R.id.priorityImage);
            gorevText = itemView.findViewById(R.id.gorevText);
            tarihText = itemView.findViewById(R.id.tarihText);
            priorityText = itemView.findViewById(R.id.priorityText);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<GorevModel> myDataset, SharedPreferenceHelper.SharedName mSharedName) {
        mDataset = myDataset;
        sharedName = mSharedName;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.gorevText.setText(mDataset.get(position).getGorev());
        holder.tarihText.setText(null);
        holder.priorityText.setText("High");


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
