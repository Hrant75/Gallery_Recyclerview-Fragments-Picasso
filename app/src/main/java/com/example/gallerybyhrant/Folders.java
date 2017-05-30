package com.example.gallerybyhrant;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Hrant on 5/29/2017.
 */

public class Folders implements Serializable {
    ArrayList<Folder> foldersList;
    String path;

    public Folders(String path){
        this.path = path;
        foldersList = new ArrayList<>();
    }


    public ArrayList<Folder> foldersAndFilesList(){

        Log.e("testt", "Folders - foldersAndFilesList start");
        ArrayList<File> directories = null;
        if(path == null || path.equals("")){
            Log.e("testt", "Folders - foldersAndFilesList if");
            File CameraDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString());
            File PicturesDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
            File[] cameraArray = CameraDirectory.listFiles();
            File[] pictureArray = PicturesDirectory.listFiles();
            directories = new ArrayList(Arrays.asList(cameraArray));
            directories.addAll(Arrays.asList(pictureArray));
            Log.e("testt", "directories size = " + directories.size());

        }
        else {
            File newFile = new File(path);
            if(newFile.isDirectory()) {
                File[] files = newFile.listFiles();
                directories = new ArrayList(Arrays.asList(files));
            }
        }

        for (File currentDirectory : directories) {
            Folder folder = new Folder();
            folder.setName(currentDirectory.getName());
            folder.setIfAFolder(currentDirectory.isDirectory());
            folder.setPath(currentDirectory.getPath());
            foldersList.add(folder);
            Log.e("testt", folder.toString());
        }
        Log.e("testt", "Folders - foldersAndFilesList " + foldersList.size());
        return foldersList;
    }
}
