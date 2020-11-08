package com.example.gorevyoneticisi.Models;

import com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper;

import java.util.Date;

public class GorevModel {
    public enum Priority {
        YUKSEK,
        ORTA,
        DUSUK
    }


    private Priority oncelik;
    private String gorev;
    private Date tarih;

    public GorevModel(Priority oncelik, String gorev, Date tarih) {
        this.oncelik = oncelik;
        this.gorev = gorev;
        this.tarih = tarih;
    }

    public Priority getPriority() {
        return oncelik;
    }

    public void setPriority(Priority oncelik) {
        this.oncelik = oncelik;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public boolean isDateExpired(SharedPreferenceHelper.SharedName sharedName) {
        long differenceInMillis = 0;
        switch (sharedName) {
            case AYLIK:
                differenceInMillis = 2629800000L;
                break;
            case HAFTALIK:
                differenceInMillis = 604800000;
                break;
            case GUNLUK:
                differenceInMillis = 86400000;
                break;
        }
        return (new Date().getTime() - tarih.getTime()) >= differenceInMillis;
    }
}
