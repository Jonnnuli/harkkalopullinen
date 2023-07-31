package app.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddLutemonActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lutemon);
    }

    public void addLutemon(View view) {

        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        int radioButtonId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioButtonId);
        String rvColor = radioButton.getText().toString();
        int image = 0;

        if (rvColor.equals("Valkoinen")) {
            image = R.drawable.white;
            Storage.getInstance().addLutemon(new Lutemon(name, rvColor, 5, 4, 20, 20, 0, image));
            Toast.makeText(this, "Lisäsit Lutemonin!", Toast.LENGTH_SHORT).show();
        } else if (rvColor.equals("Oranssi")) {
            image = R.drawable.orange;
            Storage.getInstance().addLutemon(new Lutemon(name, rvColor, 8, 1, 17, 17, 0, image));
            Toast.makeText(this, "Lisäsit Lutemonin!", Toast.LENGTH_SHORT).show();
        } else if (rvColor.equals("Pinkki")) {
            image = R.drawable.pink;
            Storage.getInstance().addLutemon(new Lutemon(name, rvColor, 7, 2, 18, 18, 0, image));
            Toast.makeText(this, "Lisäsit Lutemonin!", Toast.LENGTH_SHORT).show();
        } else if (rvColor.equals("Vihreä")) {
            image = R.drawable.green;
            Storage.getInstance().addLutemon(new Lutemon(name, rvColor, 6, 3, 19, 19, 0, image));
            Toast.makeText(this, "Lisäsit Lutemonin!", Toast.LENGTH_SHORT).show();
        } else {
            image = R.drawable.musta;
            Storage.getInstance().addLutemon(new Lutemon(name, rvColor, 9, 0, 16, 16, 0, image));
            Toast.makeText(this, "Lisäsit Lutemonin!", Toast.LENGTH_SHORT).show();
        }
        
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}