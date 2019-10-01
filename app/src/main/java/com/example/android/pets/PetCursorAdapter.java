package com.example.android.pets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PetCursorAdapter extends RecyclerView.Adapter<PetCursorAdapter.PetHolder> {

    private Context mContext;
    private View mEmptyView;
    private OnClickListener mListener;

    public PetCursorAdapter(Context context, View emptyView, OnClickListener listener) {
        mContext = context;
        mEmptyView = emptyView;
        mListener = listener;
    }


    @Override
    public PetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new PetHolder(view);
    }

    @Override
    public void onBindViewHolder(PetHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
        void onItemClick(int id);
    }

}
