package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuestList {

    private ArrayList<Quest> questsForPlayer;
    private Player player;

    public QuestList(){
        this.questsForPlayer = new ArrayList<>();

    }

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

    public boolean isQuestDone(String questName){
        for (Quest quest : questsForPlayer) {
            if (quest.getQuestName().compareToIgnoreCase(questName) == 0) {
                if (quest.isDone()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Quest getQuest(String name) throws Exception {
        for (Quest quest : questsForPlayer){
            if (quest.getQuestName().compareToIgnoreCase(name) == 0){
                return quest;
            }
        }
        throw new Exception("Il n'y a pas cette quête");
    }


    public void questValidate(String quest){


        for (Quest questToCheck : questsForPlayer) {
            if (quest.compareToIgnoreCase(questToCheck.getQuestName()) == 0) {
                if (quest.compareToIgnoreCase("bois") == 0){
                    questToCheck.setDone(true);

                }

            }
        }

    }


    public void createQuest() {

        //Création de quête
        Quest queteBois = new Quest("Bois","Avoir 10 bûche de chêne", false, 10);
        Quest queteCobblestone = new Quest("Cobblestone","Avoir 4 stacks de cobblestone",false, 10);
        Quest queteZombie = new Quest("Zombie", "Tuer 10 zombies", false, 10);
        Quest queteEnderman = new Quest("Enderman", "Tuer 2 Enderman", false, 10);

        //Ajout dans un arrayList de Quest
        questsForPlayer.add(queteBois);
        questsForPlayer.add(queteCobblestone);
        questsForPlayer.add(queteZombie);
        questsForPlayer.add(queteEnderman);

    }


}
