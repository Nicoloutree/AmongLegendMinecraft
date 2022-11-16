package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuestList {

    //private ArrayList<Quest> questArrayList;
    private HashMap<Quest, Player> questPlayerHashMap;
    public QuestList(){
        this.questArrayList = new ArrayList<>();
    }


    public void initialiseQuestsPerPlayers(ArrayList<Quest> questArrayList, int nbQuest) throws Exception {

        Collections.shuffle(questArrayList); //On mélange l'arraylist
        for (int i = 0; i < nbQuest; i++){
            this.questArrayList.add(questArrayList.get(i)); //On ajoute dans l'attribut quetes les quêtes à ajouter
        }
    }

    public void assignQuests(Player player){

    }

    public ArrayList<Quest> createQuest(){
        ArrayList<Quest> questsArrayList = new ArrayList<>();

        Quest logQuest = new Quest("Bois","Avoir 32 bûche de chêne", false);
        Quest cobblestoneQuest = new Quest("Cobblestone","Avoir 4 stacks de cobblestone", false);
        Quest zombieQuest = new Quest("Zombie", "Tuer 10 zombies", false);
        Quest endermanQuest = new Quest("Enderman", "Tuer 2 Enderman", false);

        questsArrayList.add(logQuest);
        questsArrayList.add(cobblestoneQuest);
        questsArrayList.add(zombieQuest);
        questsArrayList.add(endermanQuest);

        return questsArrayList;
    }

}
