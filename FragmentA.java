package app.lutemon.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.lutemon.Lutemon;
import app.lutemon.R;
import app.lutemon.Storage;

public class FragmentA extends Fragment {

    private Storage storage;

    private ArrayList<String> boxes;

    private ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();

    CheckBox checkBox;
    LinearLayout checkBoxContainer;
    TextView textLutemonsToHome;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    Button buttonMove;

    Lutemon lutemon;

    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);
        lutemonsAtHome = Storage.getInstance().getLutemons();
        textLutemonsToHome = view.findViewById(R.id.textViewToHome1);
        checkBoxContainer = view.findViewById(R.id.checkBoxesA);
        createCheckBoxes();

        buttonMove = view.findViewById(R.id.buttonMoveA);
        buttonMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCheckBoxes();
                System.out.println("klikkaus onnistui");
                int id = R.id.textViewToHome1;
                System.out.println(id);
                StringBuilder stringBuilder = new StringBuilder();

                for (Lutemon lutemon : lutemonsAtHome) {
                    String name = lutemon.getName();
                    stringBuilder.append(name).append(" ");
                }

                String finalText = stringBuilder.toString().trim();
                textLutemonsToHome.setText(finalText);
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
        lutemonsAtHome = new ArrayList<>();
    for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                String lutemonName = checkBox.getText().toString();
                Lutemon lutemon;
                lutemon = Storage.getInstance().getLutemonByName(lutemonName);
                Storage.getInstance().addLutemonToHome(lutemon);
                lutemonsAtHome.add(lutemon);
                //Lutemon lutemon = Storage.getInstance().getLutemonByName(lutemonName);
            }
        }
    }

}