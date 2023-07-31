package app.lutemon;

import java.io.Serializable;

public class Lutemon implements Serializable {

    private String name;
    private String color;

    private int attack;
    private int defense;
    private int health;

    private int maxHealth;
    private int experience;

    private int image;

    public Lutemon(String name, String color, int attack, int defense, int health, int maxHealth, int experience, int image) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.maxHealth = maxHealth;
        this.experience = experience;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int experience) {
        this.attack = attack + experience;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealthToMax(int max) {
        this.health = max;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getImage() {
        return image;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public void setNewHealth(int a) {
        this.health = health - a; //elämä miinus hyökkäys
    }

    public void setImageDead(Lutemon lutemon) {
        if (lutemon.getColor().equals("Valkoinen")) image = R.drawable.whitedead;
        if (lutemon.getColor().equals("Musta")) image = R.drawable.blackdead;
        if (lutemon.getColor().equals("Oranssi")) image = R.drawable.orangedead;
        if (lutemon.getColor().equals("Vihreä")) image = R.drawable.greendead;
        if (lutemon.getColor().equals("Pinkki")) image = R.drawable.pinkdead;
    }

    public void setImageAttack(Lutemon lutemon) {
        if (lutemon.getColor().equals("Valkoinen")) this.image = R.drawable.whiteattack;
        if (lutemon.getColor().equals("Musta")) this.image = R.drawable.blackattack;
        if (lutemon.getColor().equals("Oranssi")) this.image = R.drawable.orangeattack;
        if (lutemon.getColor().equals("Vihreä")) this.image = R.drawable.greenattack;
        if (lutemon.getColor().equals("Pinkki")) this.image = R.drawable.pinkattack;
    }

    public void setImageAttacked(Lutemon lutemon) {
        if (lutemon.getColor().equals("Valkoinen")) this.image = R.drawable.whiteattacked;
        if (lutemon.getColor().equals("Musta")) this.image = R.drawable.blackattacked;
        if (lutemon.getColor().equals("Oranssi")) this.image = R.drawable.orangeattacked;
        if (lutemon.getColor().equals("Vihreä")) this.image = R.drawable.greenattacked;
        if (lutemon.getColor().equals("Pinkki")) this.image = R.drawable.pinkattacked;
    }

    public void attack(Lutemon lutemon2, int lutemon1Attack) {
        lutemon2.setNewHealth(lutemon1Attack);
    }

    public void defense(int d) {
        this.health = health + d; //elämä + puolustus
    }

    public boolean isLutemonDead(int health) {
        if (health <= 0) return true;
        return false;
    }
}
