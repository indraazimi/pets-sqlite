package com.example.android.pets;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetHolder> {

    private Context mContext;
    private View mEmptyView;
    private OnClickListener mListener;
    private List<PetEntity> pets = new ArrayList<>();

    public PetAdapter(Context context, View emptyView, OnClickListener listener) {
        mContext = context;
        mEmptyView = emptyView;
        mListener = listener;
    }

    public void setPets(List<PetEntity> pets) {
        Log.d("jumlah","jumlah: "+pets.size());

        this.pets = pets;
        if (pets == null || pets.size() == 0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);
        notifyDataSetChanged();
    }


    @Override
    public PetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new PetHolder(view);
    }

    @Override
    public void onBindViewHolder(PetHolder holder, int position) {
        PetEntity currentPet = pets.get(position);
        holder.nameTextView.setText(currentPet.getName());
        holder.summaryTextView.setText(currentPet.getBreed());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    class PetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nameTextView;
        final TextView summaryTextView;

        PetHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            summaryTextView = itemView.findViewById(R.id.summary);

            itemView.setClickable(true);
            itemView.setFocusable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mListener.onItemClick(position);
            }
        }
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }

}
