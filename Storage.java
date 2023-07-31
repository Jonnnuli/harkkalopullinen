package app.lutemon;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {

    private static Storage storage = null;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();

    private ArrayList<Lutemon> lutemonsAtTrainingArea = new ArrayList<>();

    private ArrayList<Lutemon> lutemonsAtBattleField = new ArrayList<>();
    private ArrayList<Lutemon> lutemonsDead = new ArrayList<>();
    private Storage() {

    }

    public static Storage getInstance() {
        if(storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public Lutemon getLutemonByName(String name) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getName().equals(name)) {
                return lutemon;
            }
        }
        return null;
    }

    public Lutemon getLutemon(int id) {
        return lutemons.get(id);
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
        //lutemonsAtHome.add(lutemon);
    }

    public void removeLutemon(Lutemon lutemon) {
        //lutemons.remove(id);
        lutemons.remove(lutemon);
    }

    public void addLutemonToHome(Lutemon lutemon) {
        lutemonsAtHome.add(lutemon);
    }
    public void addDeadLutemon(Lutemon lutemon) {
        lutemonsDead.add(lutemon);
    }

    public void addLutemonToTrainingArea(Lutemon lutemon) {
        lutemonsAtTrainingArea.add(lutemon);
    }

    public void addLutemonToBattleField(Lutemon lutemon) {
        lutemonsAtBattleField.add(lutemon);
    }
    public ArrayList<Lutemon> getDeadLutemons() {
        return lutemonsDead;
    }

    public ArrayList<Lutemon> getLutemonsFromTrainingArea() {
        return lutemonsAtTrainingArea;
    }

    public ArrayList<Lutemon> getLutemonsFromBattleField() {
        return lutemonsAtBattleField;
    }

    public void saveLutemons(Context context) {
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons1.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();
        } catch (IOException e) {
            System.out.println("Lutemonien tallentaminen ei onnistunut.");
        }
    }

    public void loadLutemons(Context context) {
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons1.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lutemonien lukeminen epäonnistui.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Lutemonien lukeminen epäonnistui.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Lutemonien lukeminen epäonnistui.");
            e.printStackTrace();
        }
    }
}
