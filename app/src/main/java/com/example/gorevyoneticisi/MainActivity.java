package com.example.gorevyoneticisi;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.example.gorevyoneticisi.Adapters.MyAdapter;
import com.example.gorevyoneticisi.Models.GorevModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    enum SharedName{
        GUNLUK,
        HAFTALIK,
        AYLIK
    }

    private SharedName SHARED_NAME=SharedName.GUNLUK;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        recyclerView = findViewById(R.id.my_recycler_view);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_gunluk:
                        SHARED_NAME=SharedName.GUNLUK;
                        break;
                    case R.id.navigation_haftalik:
                        SHARED_NAME=SharedName.HAFTALIK;
                        break;
                    case R.id.navigation_aylik:
                        SHARED_NAME=SharedName.AYLIK;
                        break;
                }
                return false;
            }
        });

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<GorevModel> gorevModelList = new ArrayList<GorevModel>();

        gorevModelList.add(new GorevModel(GorevModel.Priority.ORTA, "BİRİNCİ", null));
        gorevModelList.add(new GorevModel(GorevModel.Priority.ORTA, "İKİNCİ", null));
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(gorevModelList);
        recyclerView.setAdapter(mAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}