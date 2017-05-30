package com.example.gallerybyhrant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

/**
 * Created by andranikh on 5/2/17.
 */

public class FolderViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    private View rootV;
    private ImageView folderImageView;
    private TextView folderTextView;
    private Target target;
    private Folder folder;

    private FolderItemClickListener itemClickListener;

    public FolderViewHolder(View itemView, FolderItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;

        rootV = itemView.findViewById(R.id.folder_main);

        folderImageView = (ImageView) itemView.findViewById(R.id.folder_imageView);
        folderTextView = (TextView) itemView.findViewById(R.id.folder_name);

        rootV.setOnClickListener(this);
    }

    public void bind(Folder folder){

        target = new Target() {
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                folderImageView.setImageBitmap(bitmap);
            }

            public void onBitmapFailed(Drawable errorDrawable) {
                folderImageView.setImageDrawable(errorDrawable);
            }

            public void onPrepareLoad(Drawable placeHolderDrawable) {
                folderImageView.setImageDrawable(placeHolderDrawable);
            }
        };

        this.folder = folder;
        folderTextView.setText(folder.getName());
        if(folder.getIfAFolder()){
            folderImageView.setImageResource(R.drawable.folder);
        }
        else{
            if(isPic(folder.getPath())){
                Log.e("testt", folder.getPath().substring(folder.getPath().length() - 3));

                Picasso.with(rootV.getContext())
//                    .load(folder.getPath())
                        .load(new File(folder.getPath()))
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.errorstop)
                        .into(target);
            }

        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.folder_main)
                notifyUserItemClicked();
    }

    private void notifyUserItemClicked(){
        if(itemClickListener != null){
            itemClickListener.onFolderItemClickListener(folder);
        }
    }

    public interface FolderItemClickListener{
        void onFolderItemClickListener(Folder folder);
    }

    public boolean isPic(String path){
        int pathLength = path.length();
        String extension = path.substring(pathLength - 3);
        return( extension.equals("jpg")
                || extension.equals("png")
                || extension.equals("peg")
                || extension.equals("bmp")
                );
    }
}
