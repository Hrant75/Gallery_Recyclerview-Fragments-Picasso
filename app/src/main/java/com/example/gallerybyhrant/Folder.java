package com.example.gallerybyhrant;

/**
 * Created by Hrant on 5/30/2017.
 */

public class Folder {
    private String name;
    private String path;
    private boolean isAfolder;

    public Folder() {
    }

    public Folder(String name, String path, boolean isAfolder){
        this.name = name;
        this.path = path;
        this.isAfolder = isAfolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIfAFolder() {
        return isAfolder;
    }

    public void setIfAFolder(boolean isAfolder) {
        this.isAfolder = isAfolder;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toString(){
        String str = getIfAFolder()?"folder":"file";
        return "path: " + getPath() + " name: " + getName() + " " + str;
    }
}
