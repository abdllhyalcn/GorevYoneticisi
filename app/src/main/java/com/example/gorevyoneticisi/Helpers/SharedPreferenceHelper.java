package com.example.gorevyoneticisi.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gorevyoneticisi.Models.GorevModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceHelper {

    public enum SharedName {
        GUNLUK,
        HAFTALIK,
        AYLIK
    }

    private SharedName sharedName;
    private SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private Context context;

    public SharedPreferenceHelper(Context context, SharedName sharedName) {
        this.context = context;
        sharedName = sharedName;
        setSharedPref(sharedName);
        editor = sharedPreferences.edit();
    }

    public void setSharedPref(SharedName sharedName) {
        this.sharedName = sharedName;
        sharedPreferences = context.getSharedPreferences(sharedName.name(), Context.MODE_PRIVATE);
    }

    public <GorevModel> void setList(List<GorevModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(sharedName.name(), json);
    }

    private void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public List<GorevModel> getList() {
        List<GorevModel> arrayItems = new ArrayList<GorevModel>();

        String serializedObject = sharedPreferences.getString(sharedName.name(), null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<GorevModel>>() {
            }.getType();

            arrayItems = gson.fromJson(serializedObject, type);
        }
        return arrayItems;

    }


}
