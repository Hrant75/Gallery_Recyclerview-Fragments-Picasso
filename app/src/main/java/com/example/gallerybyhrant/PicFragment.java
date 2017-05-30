package com.example.gallerybyhrant;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gallerybyhrant.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicFragment extends Fragment {
    private View view;
    private String mParamFolderName, mParamFolderPath;
    TextView textView;
    ImageView imageView;
    Target target;
    private Folder folder;

    public PicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamFolderName = getArguments().getString("folder_name");
            mParamFolderPath = getArguments().getString("folder_path");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pic, container, false);
        textView = (TextView) view.findViewById(R.id.pic_name);
        imageView = (ImageView) view.findViewById(R.id.pic_imageView);

        target = new Target() {
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setImageBitmap(bitmap);
            }

            public void onBitmapFailed(Drawable errorDrawable) {
                imageView.setImageDrawable(errorDrawable);
            }

            public void onPrepareLoad(Drawable placeHolderDrawable) {
                imageView.setImageDrawable(placeHolderDrawable);
            }
        };

        textView.setText(mParamFolderName);
        Picasso.with(view.getContext())
                .load(new File(mParamFolderPath))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.errorstop)
                .into(target);


        return view;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }
}
