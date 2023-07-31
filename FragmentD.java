package app.lutemon.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.lutemon.Lutemon;
import app.lutemon.R;
import app.lutemon.Storage;

public class FragmentD extends Fragment {

    TextView textLutemonsDead;
    ImageView imageDeadLutemon;
    Storage storage;

    private ArrayList<Lutemon> deadLutemons = new ArrayList<>();

    public FragmentD() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_d, container, false);

        deadLutemons = Storage.getInstance().getDeadLutemons();

        textLutemonsDead = view.findViewById(R.id.textLutemonsDead);
        imageDeadLutemon = view.findViewById(R.id.imageViewDeadL);

        StringBuilder stringBuilder = new StringBuilder();

        for (Lutemon lutemon : deadLutemons) {
            String name = lutemon.getName();
            stringBuilder.append(name).append(" ");
        }

        String finalText = stringBuilder.toString().trim();
        textLutemonsDead.setText(finalText);

        imageDeadLutemon.setImageResource(deadLutemons.get(0).getImage());

        return view;
    }
}