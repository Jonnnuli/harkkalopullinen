package app.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.lutemon.fragments.FragmentD;

public class BattleFieldActivity extends AppCompatActivity {

    LinearLayout checkBoxContainer;
    TextView fightingLutemon1, textViewAttack1, textViewDefence1, textViewHealth1, textViewExperience1;
    TextView fightingLutemon2, textViewAttack2, textViewDefence2, textViewHealth2, textViewExperience2, textViewBattleText;
    ImageView imageViewL1, imageViewL2, imageFight1, imageFight2;
    private ArrayList<Lutemon> lutemonsStartFight = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    Button buttonPickTwoLutemons, buttonAttack1, buttonAttack2;
    Storage storage;
    boolean isDead = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        checkBoxContainer = findViewById(R.id.linearLayoutBoxes);

        fightingLutemon1 = findViewById(R.id.textLutemon1);
        textViewAttack1 = findViewById(R.id.textViewAttack1);
        textViewDefence1 = findViewById(R.id.textViewDefense1);
        textViewHealth1 = findViewById(R.id.textViewHealth1);
        textViewExperience1 = findViewById(R.id.textViewExperience1);
        imageViewL1 = findViewById(R.id.imageViewL1);
        imageFight1 = findViewById(R.id.imageBattleL1);

        fightingLutemon2 = findViewById(R.id.textLutemon2);
        textViewAttack2 = findViewById(R.id.textViewAttack2);
        textViewDefence2 = findViewById(R.id.textViewDefense2);
        textViewHealth2 = findViewById(R.id.textViewHealth2);
        textViewExperience2 = findViewById(R.id.textViewExperience2);
        imageViewL2 = findViewById(R.id.imageViewL2);
        imageFight2 = findViewById(R.id.imageBattleL2);

        lutemonsStartFight = Storage.getInstance().getLutemons();

        createCheckBoxes();
        buttonAttack1 = findViewById(R.id.buttonAttack1);
        buttonAttack2 = findViewById(R.id.buttonAttack2);

        buttonPickTwoLutemons = findViewById(R.id.buttonPickTwoLutemons);
        buttonPickTwoLutemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCheckBoxes();

                Lutemon lutemon1 = lutemonsStartFight.get(0);
                Lutemon lutemon2 = lutemonsStartFight.get(1);

                fightingLutemon1.setText(lutemon1.getName());

                textViewAttack1.setText("Hyökkäys:" + String.valueOf(lutemon1.getAttack()));
                textViewDefence1.setText("Puolustus: " + String.valueOf(lutemon1.getDefense()));
                textViewHealth1.setText("Elämä: " + String.valueOf(lutemon1.getHealth()) + "/" + String.valueOf(lutemon1.getMaxHealth()));
                textViewExperience1.setText("Kokemus: " + String.valueOf(lutemon1.getExperience()));
                imageViewL1.setImageResource(lutemon1.getImage());

                fightingLutemon2.setText(lutemon2.getName());
                textViewAttack2.setText("Hyökkäys:" + String.valueOf(lutemon2.getAttack()));
                textViewDefence2.setText("Puolustus: " + String.valueOf(lutemon2.getDefense()));
                textViewHealth2.setText("Elämä: " + String.valueOf(lutemon2.getHealth()) + "/" + String.valueOf(lutemon2.getMaxHealth()));
                textViewExperience2.setText("Kokemus: " + String.valueOf(lutemon2.getExperience()));
                imageViewL2.setImageResource(lutemon2.getImage());
            }
        });

    }

    public void createCheckBoxes() {
        ArrayList<Lutemon> copyLutemons = Storage.getInstance().getLutemonsFromBattleField();

        for (Lutemon lutemon : copyLutemons) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(lutemon.getName());
            checkBox.setTag(lutemon); //laitetaan tagi, koska ei olla tehty ite, ei oo id.
            checkBoxContainer.addView(checkBox);
            checkBoxes.add(checkBox);
        }


    }

    public void checkCheckBoxes() {
        lutemonsStartFight = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                String lutemonName = checkBox.getText().toString();
                Lutemon lutemon;
                lutemon = Storage.getInstance().getLutemonByName(lutemonName);
                lutemonsStartFight.add(lutemon);
            }
        }
    }

    public void startBattle(View view) {

        Lutemon lutemon1 = lutemonsStartFight.get(0);
        Lutemon lutemon2 = lutemonsStartFight.get(1);

        textViewBattleText = findViewById(R.id.textViewBattleText);

        textViewBattleText.setText("Taistelu alkaa: " + lutemon1.getName() + " (" + lutemon1.getHealth() + "/" + lutemon1.getMaxHealth() + ") ja " + lutemon2.getName() + " (" + lutemon2.getHealth() + "/" + lutemon2.getMaxHealth() + ")");

        buttonAttack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lutemon1.setImageAttack(lutemon1);
                lutemon2.setImageAttacked(lutemon2);
                imageFight1.setImageResource(lutemon1.getImage());
                imageFight2.setImageResource(lutemon2.getImage());
                lutemon1.attack(lutemon2, lutemon1.getAttack()); //ensimmäinen hyökkäys
                lutemon2.defense(lutemon2.getDefense()); // ensimmäisen hyökkäyksen puolustus
                String text1 = textViewBattleText.getText().toString();
                textViewBattleText.setText(text1 + "\n" + lutemon1.getName() + " (" + lutemon1.getHealth() + "/" + lutemon1.getMaxHealth() + ") hyökkäsi " + lutemon2.getName() + " (" + lutemon2.getHealth() + "/" + lutemon2.getMaxHealth() + ")");
                isDead = lutemon2.isLutemonDead(lutemon2.getHealth());
                //if (isDead == true) lutemonDied(lutemon2, lutemon1);
                checkIfLutemonDied(lutemon1, lutemon2);
            }
        });

        buttonAttack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lutemon2.setImageAttack(lutemon2);
                lutemon1.setImageAttacked(lutemon1);
                imageFight1.setImageResource(lutemon1.getImage());
                imageFight2.setImageResource(lutemon2.getImage());
                lutemon2.attack(lutemon1, lutemon2.getAttack()); //toinen hyökkäys
                lutemon1.defense(lutemon1.getDefense()); //toisen hyökkäyksen puolustus
                String text2 = textViewBattleText.getText().toString();
                textViewBattleText.setText(text2 + "\n" + lutemon2.getName() + " (" + lutemon2.getHealth() + "/" + lutemon2.getMaxHealth() + ") hyökkäsi " + lutemon1.getName() + " (" + lutemon1.getHealth() + "/" + lutemon1.getMaxHealth() + ")");
                isDead = lutemon1.isLutemonDead(lutemon1.getHealth());
                checkIfLutemonDied(lutemon1, lutemon2);
            }
        });
    }

    public void checkIfLutemonDied(Lutemon lutemon1, Lutemon lutemon2) {
         if (lutemon2.getHealth() <= 0) {
             String textDead = textViewBattleText.getText().toString();
             textViewBattleText.setText(textDead + "\n" + lutemon2.getName() + " kuoli ja " + lutemon1.getName() + " voitti!");
             lutemon1.setHealthToMax(lutemon1.getMaxHealth()); //voittajan elämät maxiin
             lutemon1.setExperience(lutemon1.getExperience() + 2); //voittajalle 2 kokemuspistettä
             //lutemon1.setAttack(lutemon1.getExperience()); //voittajalle lisätään kokemuspisteet hyökkäykseen
             String textString = textViewBattleText.getText().toString();
             textViewBattleText.setText(textString + "\n" + "voittaja " + lutemon1.getName() + " saa 2 kokemuspistettä!");
             Storage.getInstance().addDeadLutemon(lutemon2);
             lutemon2.setImageDead(lutemon2);
             imageFight2.setImageResource(lutemon2.getImage());
             Storage.getInstance().removeLutemon(lutemon2);
         }
        if (lutemon1.getHealth() <= 0) {
            String textDead = textViewBattleText.getText().toString();
            textViewBattleText.setText(textDead + "\n" + lutemon1.getName() + " kuoli ja " + lutemon2.getName() + " voitti!");
            lutemon2.setHealthToMax(lutemon2.getMaxHealth()); //voittajan elämät maxiin
            lutemon2.setExperience(lutemon2.getExperience() + 2); //voittajalle 2 kokemuspistettä
            //lutemon2.setAttack(lutemon2.getExperience()); //voittajalle lisätään kokemuspisteet hyökkäykseen
            String textString = textViewBattleText.getText().toString();
            textViewBattleText.setText(textString + "\n" + "voittaja " + lutemon2.getName() + " saa 2 kokemuspistettä!");
            Storage.getInstance().addDeadLutemon(lutemon1);
            lutemon1.setImageDead(lutemon1);
            imageFight1.setImageResource(lutemon1.getImage());
            Storage.getInstance().removeLutemon(lutemon1);
        }
    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}