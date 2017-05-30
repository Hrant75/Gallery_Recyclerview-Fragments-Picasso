package com.example.gallerybyhrant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FolderViewHolder.FolderItemClickListener{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bundle = new Bundle();
        bundle.putString("folder_name", "");
        fragment = new FoldersFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment.setArguments(bundle);
//        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.add(R.id.main_container, fragment, "f"); //fragment.getTag());
        fragmentTransaction.commit();
//        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() == 0 ) finish();
        else fragmentManager.popBackStack();
    }

    public void onFolderItemClickListener(Folder folder) {
        bundle = new Bundle();
        bundle.putString("folder_name", folder.getName());
        bundle.putString("folder_path", folder.getPath());
        fragment = new FoldersFragment();
        if(!folder.getIfAFolder()) fragment = new PicFragment();
        fragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(folder.getPath());
        fragmentTransaction.add(R.id.main_container, fragment, folder.getName());
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }
}
