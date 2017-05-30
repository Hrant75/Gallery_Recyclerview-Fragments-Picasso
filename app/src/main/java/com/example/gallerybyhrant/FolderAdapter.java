package com.example.gallerybyhrant;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by andranikh on 5/2/17.
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderViewHolder> {

    private List<Folder> data;

    private FolderViewHolder.FolderItemClickListener clickListener;

    public FolderAdapter(List<Folder> data) {
        this.data = data;

    }

    public FolderAdapter(List<Folder> data, FolderViewHolder.FolderItemClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    @Override
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.folder, parent, false);
        return new FolderViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(FolderViewHolder holder, int position) {
        Log.e("testt", "onBindViewHolder " + data.size() + " " + position + data.get(position).toString());
        holder.bind(data.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
