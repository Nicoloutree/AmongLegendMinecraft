package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuestList {

    private ArrayList<Quest> questsForAllPlayers;


    public QuestList(){
        this.questsForAllPlayers = new ArrayList<>();

    }



    public void createQuest() {

        //Création de quête
        Quest queteBois = new Quest("Bois","Avoir 32 bûche de chêne", false);
        Quest queteCobblestone = new Quest("Cobblestone","Avoir 4 stacks de cobblestone", false);
        Quest queteZombie = new Quest("Zombie", "Tuer 10 zombies", false);
        Quest queteEnderman = new Quest("Enderman", "Tuer 2 Enderman", false);

        //Ajout dans un arrayList de Quest
        questsForAllPlayers.add(queteBois);
        questsForAllPlayers.add(queteCobblestone);
        questsForAllPlayers.add(queteZombie);
        questsForAllPlayers.add(queteEnderman);

    }

    public ArrayList<Quest> getQuestsForAllPlayers() {
        return questsForAllPlayers;
    }
}
