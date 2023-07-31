package app.lutemon;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name, color, attack, defense, health, experience;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.textViewName);
        //color = itemView.findViewById(R.id.textViewColor);
        attack = itemView.findViewById(R.id.textViewAttack);
        defense = itemView.findViewById(R.id.textViewDefense);
        health = itemView.findViewById(R.id.textViewHealth);
        experience = itemView.findViewById(R.id.textViewExperience);
    }
}
