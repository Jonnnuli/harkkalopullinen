package app.lutemon.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class FragmentC extends Fragment {

    private ArrayList<Lutemon> lutemonsAtBattleField = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    Lutemon lutemon;
    LinearLayout checkBoxContainer;
    TextView textLutemonsToBattleField;
    Button buttonMove;
    public FragmentC() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c, container, false);

    lutemonsAtBattleField = Storage.getInstance().getLutemons();
    textLutemonsToBattleField = view.findViewById(R.id.textViewToBattleField1);
    checkBoxContainer = view.findViewById(R.id.checkBoxesC);
    createCheckBoxes();

    buttonMove = view.findViewById(R.id.buttonMoveC);
        buttonMove.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkCheckBoxes();
            StringBuilder stringBuilder = new StringBuilder();

            for (Lutemon lutemon : lutemonsAtBattleField) {
                String name = lutemon.getName();
                stringBuilder.append(name).append(" ");
            }

            String finalText = stringBuilder.toString().trim();
            textLutemonsToBattleField.setText(finalText);
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
        lutemonsAtBattleField = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                String lutemonName = checkBox.getText().toString();
                Lutemon lutemon;
                lutemon = Storage.getInstance().getLutemonByName(lutemonName);
                Storage.getInstance().addLutemonToBattleField(lutemon);
                lutemonsAtBattleField.add(lutemon);
            }
        }
    }
}