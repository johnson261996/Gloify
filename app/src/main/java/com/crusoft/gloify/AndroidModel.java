package com.crusoft.gloify;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AndroidModel {

    private String ver;

    public String name;

    private String api;

   public String getVer() {
        return ver;
    }


    public void setVer(String ver) {
        this.ver = ver;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

}
