package app.lutemon.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.lutemon.Lutemon;
import app.lutemon.R;
import app.lutemon.Storage;


public class FragmentB extends Fragment {


    private ArrayList<Lutemon> lutemonsAtTraining = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    Lutemon lutemon;
    LinearLayout checkBoxContainer;
    TextView textLutemonsToTraining;
    Button buttonMove;
    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);

        lutemonsAtTraining = Storage.getInstance().getLutemons();
        textLutemonsToTraining = view.findViewById(R.id.textViewToTraining1);
        checkBoxContainer = view.findViewById(R.id.checkBoxesB);
        createCheckBoxes();

        buttonMove = view.findViewById(R.id.buttonMoveB);
        buttonMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCheckBoxes();

                StringBuilder stringBuilder = new StringBuilder();

                for (Lutemon lutemon : lutemonsAtTraining) {
                    String name = lutemon.getName();
                    stringBuilder.append(name).append(" ");
                    Storage.getInstance().addLutemonToTrainingArea(lutemon);
                }

                String finalText = stringBuilder.toString().trim();
                textLutemonsToTraining.setText(finalText);
            }
        });

        return view;

    }

    public void createCheckBoxes() {
        ArrayList<Lutemon> copyLutemons = Storage.getInstance().getLutemons();

        for (Lutemon lutemon : copyLutemons) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(lutemon.getName());
            checkBox.setTag(lutemon); //laitetaan tagi, koska ei olla tehty ite, ei oo id.
            checkBoxContainer.addView(checkBox);
            checkBoxes.add(checkBox);
        }
    }

    public void checkCheckBoxes() {
        lutemonsAtTraining = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                String lutemonName = checkBox.getText().toString();
                Lutemon lutemon;
                lutemon = Storage.getInstance().getLutemonByName(lutemonName);
                Storage.getInstance().addLutemonToTrainingArea(lutemon);
                lutemonsAtTraining.add(lutemon);
            }
        }
    }
}