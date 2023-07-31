package app.lutemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        holder.name.setText(lutemons.get(position).getName() + " (" + lutemons.get(position).getColor() + ")");
        holder.attack.setText("Hyökkäys: " + String.valueOf(lutemons.get(position).getAttack()));
        holder.defense.setText("Puolustus: " + String.valueOf(lutemons.get(position).getDefense()));
        holder.health.setText("Elämä: " + String.valueOf(lutemons.get(position).getHealth()));
        holder.experience.setText("Kokemus: " + String.valueOf(lutemons.get(position).getExperience()));
        holder.imageView.setImageResource(lutemons.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return lutemons.size(); //kuinka monta elementtiä recyclerviewiin tulee näkyviin
    }
}
