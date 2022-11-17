package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Team {

    private String teamName;
    private QuestList quests;

    private ArrayList<Player> playerArrayList;
    public Team(String teamName,ArrayList<Player> playerArrayList) {
        this.teamName = teamName;
        this.playerArrayList = playerArrayList;
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public void setPlayerArrayList(ArrayList<Player> playerArrayList) {
        this.playerArrayList = playerArrayList;
    }

    public QuestList getQuests() {
        return quests;
    }

    public void setQuests(QuestList quests) {
        this.quests = quests;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    //Return true if the input player is in the team object preciding it
    public boolean isFromTeam(Player player){
        return this.playerArrayList.contains(player);
    }

    public void addPlayer(Player player){
        this.playerArrayList.add(player);
    }

    public void removePlayer(Player player){
        this.playerArrayList.remove(player);
    }

    public void emptyTeam(){
        this.playerArrayList.clear();
    }

    public String playerArrayListToString() { //TO DO Check the toString methods from java to see if it's usable in this case
        String res = "";
        for (Player player : this.playerArrayList){
            res = res + " " + player.getName();
        }
        return res;
    }

    public void initialiseQuestsPerPlayers(int nbQuest){
        createScoreboard scoreboard = new createScoreboard();
        scoreboard.createBoardForPlayers(getPlayerArrayList(),quests, nbQuest);
    }

}
