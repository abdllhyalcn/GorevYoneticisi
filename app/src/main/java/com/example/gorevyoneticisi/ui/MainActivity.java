package com.example.gorevyoneticisi.ui;


import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gorevyoneticisi.Adapters.MyAdapter;
import com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper;
import com.example.gorevyoneticisi.Models.GorevModel;
import com.example.gorevyoneticisi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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

import static com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper.SharedName.AYLIK;
import static com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper.SharedName.GUNLUK;
import static com.example.gorevyoneticisi.Helpers.SharedPreferenceHelper.SharedName.HAFTALIK;

public class MainActivity extends AppCompatActivity implements AddDialog.AddDialogListener, MyAdapter.onDeleteClickedListener {

    Logger logger = Logger.getLogger("Log");

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private List<GorevModel> gorevGunlukList;
    private List<GorevModel> gorevHaftalikList;
    private List<GorevModel> gorevAylikList;

    private SharedPreferenceHelper.SharedName sharedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        FloatingActionButton addButton = findViewById(R.id.addButton);

        gorevGunlukList = getList(GUNLUK);
        gorevHaftalikList = getList(HAFTALIK);
        gorevAylikList = getList(AYLIK);


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
                        sharedName = GUNLUK;
                        mAdapter = new MyAdapter(MainActivity.this, gorevGunlukList, sharedName);
                        recyclerView.setAdapter(mAdapter);
                        break;
                    case R.id.navigation_haftalik:
                        sharedName = HAFTALIK;
                        mAdapter = new MyAdapter(MainActivity.this, gorevHaftalikList, sharedName);
                        recyclerView.setAdapter(mAdapter);
                        break;
                    case R.id.navigation_aylik:
                        sharedName = AYLIK;
                        mAdapter = new MyAdapter(MainActivity.this, gorevAylikList, sharedName);
                        recyclerView.setAdapter(mAdapter);
                        break;

                }

                return true;
            }
        });


    }

    @Override
    public void onDialogPositiveClick(GorevModel gorevModel) {
        if (gorevModel == null) {
            Toast.makeText(MainActivity.this, "Görev Alınamadı!", Toast.LENGTH_LONG).show();
        } else {
            switch (sharedName) {
                case GUNLUK:
                    gorevGunlukList.add(gorevModel);
                    break;
                case HAFTALIK:
                    gorevHaftalikList.add(gorevModel);
                    break;
                case AYLIK:
                    gorevAylikList.add(gorevModel);
                    break;
            }
            mAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void deleteClicked(int position) {
        switch (sharedName) {
            case GUNLUK:
                gorevGunlukList.remove(position);
                break;
            case HAFTALIK:
                gorevHaftalikList.remove(position);
                break;
            case AYLIK:
                gorevAylikList.remove(position);
                break;
        }
        mAdapter.notifyDataSetChanged();
    }

    private List<GorevModel> getList(SharedPreferenceHelper.SharedName sharedName) {
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this, sharedName);
        return sharedPreferenceHelper.getList();
    }

    private void saveList(List<GorevModel> data, SharedPreferenceHelper.SharedName sharedName) {
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this, sharedName);
        sharedPreferenceHelper.setList(data);
    }

    private void initRecylerView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sharedName = GUNLUK;
        mAdapter = new MyAdapter(MainActivity.this, gorevGunlukList, sharedName);


        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveList(gorevGunlukList, GUNLUK);
        saveList(gorevHaftalikList, HAFTALIK);
        saveList(gorevAylikList, AYLIK);
    }
}