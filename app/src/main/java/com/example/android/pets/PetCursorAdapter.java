package com.example.android.pets;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;

public class PetCursorAdapter extends RecyclerView.Adapter<PetCursorAdapter.PetHolder> {

    private Context mContext;
    private Cursor mCursor;
    private View mEmptyView;
    private OnClickListener mListener;

    public PetCursorAdapter(Context context, View emptyView, OnClickListener listener) {
        mContext = context;
        mEmptyView = emptyView;
        mListener = listener;
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();

        mCursor = newCursor;
        if (newCursor == null || newCursor.getCount() == 0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new PetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        mCursor.moveToPosition(position);

        int indexName = mCursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int indexBreed = mCursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);

        holder.nameTextView.setText(mCursor.getString(indexName));
        holder.summaryTextView.setText(mCursor.getString(indexBreed));
    }

    @Override
    public int getItemCount() {
        if(mCursor ==null) return 0;
        return mCursor.getCount();
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
                mCursor.moveToPosition(position);
                int indexId = mCursor.getColumnIndex(PetContract.PetEntry._ID);
                mListener.onItemClick(mCursor.getInt(indexId));
            }
        }
    }

    public interface OnClickListener {
        void onItemClick(int id);
    }

}
