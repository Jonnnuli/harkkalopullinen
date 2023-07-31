package app.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.lutemon.fragments.FragmentA;
/*
Lutemon App. Play with Lutemons, you can create them, play with them in training area and fight in battlefield.
Jonna Helaakoski 2023.
 */
public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonMoveLutemons = findViewById(R.id.buttonMoveLutemons);
        //buttonMoveLutemons.setOnClickListener(listener);

        context = MainActivity.this;

    }

    public void switchToAddLutemon(View view) {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemons(View view) {
        Intent intent = new Intent(this, ListLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToMoveLutemons(View view) {
        Intent intent = new Intent(this, MoveLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToBattleField(View view) {
        Intent intent = new Intent(this, BattleFieldActivity.class);
        startActivity(intent);
    }

    public void switchToTrainingArea(View view) {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    public void saveLutemons(View view) {
        Storage.getInstance().saveLutemons(context);
    }

    public void loadLutemons(View view) {
        Storage.getInstance().loadLutemons(context);
    }

}