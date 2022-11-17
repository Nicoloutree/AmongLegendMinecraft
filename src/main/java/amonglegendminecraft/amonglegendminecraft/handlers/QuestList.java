package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuestList {

    private ArrayList<Quest> questArrayListPerPlayer;


    public QuestList(){
        this.questArrayListPerPlayer = new ArrayList<>();

    }



    public void createQuest() {

        //Création de quête
        Quest queteBois = new Quest("Bois","Avoir 32 bûche de chêne", false);
        Quest queteCobblestone = new Quest("Cobblestone","Avoir 4 stacks de cobblestone", false);
        Quest queteZombie = new Quest("Zombie", "Tuer 10 zombies", false);
        Quest queteEnderman = new Quest("Enderman", "Tuer 2 Enderman", false);

        //Ajout des quêtes dans un ArrayList
        ArrayList<Quest> queteToAdd = new ArrayList<>();

        queteToAdd.add(queteBois);
        queteToAdd.add(queteCobblestone);
        queteToAdd.add(queteZombie);
        queteToAdd.add(queteEnderman);



        this.questArrayListPerPlayer = queteToAdd;

    }

    public void setQuestArrayListPerPlayer(ArrayList<Quest> questArrayListPerPlayer) {
        this.questArrayListPerPlayer = questArrayListPerPlayer;
    }

    public ArrayList<Quest> getQuestArrayListPerPlayer() {
        return questArrayListPerPlayer;
    }
}
