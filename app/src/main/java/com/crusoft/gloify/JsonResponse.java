package com.crusoft.gloify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {
    private AndroidModel[] android;

    public AndroidModel[] getAndroid() {
        return android;
    }
}
