package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuestList {

    private ArrayList<Quest> questsForPlayer;
    private Player player;

    public ArrayList<Quest> getQuestsForPlayer() {
        return questsForPlayer;
    }

    public void setQuestsForPlayer(ArrayList<Quest> questsForPlayer) {
        this.questsForPlayer = questsForPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public QuestList(){
        this.questsForPlayer = new ArrayList<>();

    }



    public void createQuest() {

        //Création de quête
        Quest queteBois = new Quest("Bois","Avoir 32 bûche de chêne", false);
        Quest queteCobblestone = new Quest("Cobblestone","Avoir 4 stacks de cobblestone", false);
        Quest queteZombie = new Quest("Zombie", "Tuer 10 zombies", false);
        Quest queteEnderman = new Quest("Enderman", "Tuer 2 Enderman", false);

        //Ajout dans un arrayList de Quest
        questsForPlayer.add(queteBois);
        questsForPlayer.add(queteCobblestone);
        questsForPlayer.add(queteZombie);
        questsForPlayer.add(queteEnderman);

    }


}
