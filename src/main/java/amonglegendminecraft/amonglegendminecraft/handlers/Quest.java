package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.Collections;

public class Quest {

    private String questName;
    private String questDescription;
    private Material material;
    private int compteur;
    private String questType;
    private boolean done;


    public Quest(String questName, String questDescription, boolean done, int compteur, String questType) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.done = done;
        this.compteur = compteur;
        this.questType = questType;
    }

    public Quest(String questName, String questDescription, boolean done, int compteur, String questType, Material material) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.done = done;
        this.compteur = compteur;
        this.questType = questType;
        this.material = material;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }


    public void setDone(boolean done) {
        this.done = done;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestName() {
        return questName;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public boolean isDone() {
        return done;
    }

    public static ArrayList<Quest> initializeQuests(int nbQuest){
        ArrayList<Quest> temp = new ArrayList<>();
        //Création de quête
        temp.add(new Quest("Bois","Avoir 10 bûche de chêne", false, 10,"collect", Material.OAK_LOG));
        temp.add(new Quest("Cobblestone","Avoir 4 stacks de cobblestone",false, 10,"collect", Material.COBBLESTONE));
        temp.add(new Quest("Zombie", "Tuer 10 zombies", false, 10,"entity"));
        temp.add(new Quest("Enderman", "Tuer 2 Enderman", false, 10,"entity"));

        Collections.shuffle(temp);
        while(temp.size() > nbQuest){
            temp.remove(0);
        }
        return temp;
    }



    public Quest(){

    }

}
