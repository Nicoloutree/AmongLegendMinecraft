package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Sabotage {

    private String name;
    private String description;
    private int price;


    public Sabotage(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static ArrayList<Sabotage> initSabotage(){
        ArrayList<Sabotage> temp = new ArrayList<>();

        temp.add(new Sabotage("Ralentissement","Applique un ralentissemnt à tout les crewmates", 2));
        temp.add(new Sabotage("Mining", "Applique mining fatigue à tout les crewmates",2));
        temp.add(new Sabotage("Light","Rend aveugle tout les crewmates",4));
        temp.add(new Sabotage("Octogone","Téléporte un crewmate au hasard et toi dans un octogone sans règle",10));

        return temp;
    }

    public static void afficheSabotage(Sabotage sabotage, PlayerTeam player){
        player.getPlayer().sendMessage("Nom : " + sabotage.getName());
        player.getPlayer().sendMessage("Description : " + sabotage.getDescription());

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
