package com.example.gallerybyhrant;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.net.URISyntaxException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoldersFragment extends Fragment {
    ArrayList<Folder> fileAndFolderList;
    private RecyclerView recyclerView;
    private FolderAdapter adapter;
    LinearLayoutManager mLayoutManager;
    private View view;
    private String mParamFolderName, mParamFolderPath;
    Folders folders;
    Bundle bundle;

    public FoldersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamFolderName = getArguments().getString("folder_name");
            mParamFolderPath = getArguments().getString("folder_path");
        }
        folders = new Folders(mParamFolderPath);
//        bundle = savedInstanceState;
//            Log.e("testt", bundle.getString("folder_name"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.folders_fragment, container, false);

        fileAndFolderList = folders.foldersAndFilesList();
        recyclerView = (RecyclerView) view.findViewById(R.id.folders_rv);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new FolderAdapter(fileAndFolderList, (FolderViewHolder.FolderItemClickListener)getContext());
        recyclerView.setAdapter(adapter);
//        Intent intent = null;
//
//        try {
//             intent = Intent.getIntent("folder_name");
//        }
//        catch (URISyntaxException e){}
//
//        String type = intent.getStringExtra("type");

        return view;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    public static FoldersFragment newInstance(String param1) {
        FoldersFragment fragment = new FoldersFragment();
        Bundle bundle = new Bundle();
        bundle.putString("folder_name", param1);
        fragment.setArguments(bundle);
        return fragment;
    }

}
