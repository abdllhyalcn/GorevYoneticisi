package com.example.gorevyoneticisi.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.gorevyoneticisi.Models.GorevModel;
import com.example.gorevyoneticisi.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class AddDialog extends DialogFragment {

    public interface AddDialogListener {
        void onDialogPositiveClick(GorevModel gorevModel);
    }

    AddDialogListener listener;

    private RadioGroup radioGroup;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;

    FragmentActivity context;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_dialog, null);

        radioGroup = view.findViewById(R.id.radioGroup);
        textInputEditText = view.findViewById(R.id.gorevTextEdit);

        builder.setView(view)
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        GorevModel.Priority priority = getPriority();
                        String gorev = getGorev();
                        GorevModel gorevModel = null;
                        if (getGorev() != null && priority != null) {
                            gorevModel = new GorevModel(priority, gorev, new Date());
                        }

                        listener.onDialogPositiveClick(gorevModel);
                    }
                })
                .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }


    private GorevModel.Priority getPriority() {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        switch (radioButtonID) {
            case R.id.radioDusuk:
                return GorevModel.Priority.DUSUK;
            case R.id.radioOrta:
                return GorevModel.Priority.ORTA;
            case R.id.radioYuksek:
                return GorevModel.Priority.YUKSEK;
        }
        return null;
    }

    private String getGorev() {
        if (textInputEditText.getText().toString().isEmpty()) {
            return null;
        } else {
            return textInputEditText.getText().toString();
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (FragmentActivity) context;
        try {
            listener = (AddDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement AddDialogListener");
        }
    }
}
