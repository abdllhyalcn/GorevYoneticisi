package com.example.gorevyoneticisi.Models;

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
}
