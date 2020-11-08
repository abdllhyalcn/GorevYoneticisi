package com.example.gorevyoneticisi.ui;


import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.example.gorevyoneticisi.Adapters.MyAdapter;
import com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper;
import com.example.gorevyoneticisi.Models.GorevModel;
import com.example.gorevyoneticisi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    Logger logger = Logger.getLogger("Log");

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private List<GorevModel> gorevModelList;

    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        FloatingActionButton addButton = findViewById(R.id.addButton);

        sharedPreferenceHelper = new SharedPreferenceHelper(this, SharedPreferenceHelper.SharedName.GUNLUK);

        initRecylerView();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new AddDialog();
                newFragment.show(getSupportFragmentManager(), "AddDialog");
            }
        });

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_gunluk:
                        storeData();
                        sharedPreferenceHelper.setSharedPref(SharedPreferenceHelper.SharedName.GUNLUK);
                        setmAdapter();
                        return true;
                    case R.id.navigation_haftalik:
                        storeData();
                        sharedPreferenceHelper.setSharedPref(SharedPreferenceHelper.SharedName.HAFTALIK);
                        setmAdapter();
                        return true;
                    case R.id.navigation_aylik:
                        storeData();
                        sharedPreferenceHelper.setSharedPref(SharedPreferenceHelper.SharedName.AYLIK);
                        setmAdapter();
                        return true;
                }

                return false;
            }
        });


    }

    private void initRecylerView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        gorevModelList = new ArrayList<GorevModel>();

        mAdapter = new MyAdapter(gorevModelList);

        recyclerView.setAdapter(mAdapter);

        setmAdapter();
    }

    private void setmAdapter() {
        gorevModelList = sharedPreferenceHelper.getList();
        mAdapter.notifyDataSetChanged();
    }

    private void storeData() {
        sharedPreferenceHelper.setList(gorevModelList);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}