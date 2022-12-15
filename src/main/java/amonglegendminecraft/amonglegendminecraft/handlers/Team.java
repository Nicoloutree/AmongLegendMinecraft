package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.ArrayList;

import static java.awt.Color.RED;
import static org.bukkit.ChatColor.BLUE;

public abstract class Team {

    private String teamName;
    private ArrayList<QuestList> quests;

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

    public ArrayList<QuestList> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<QuestList> quests) {
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
        this.quests.clear();
        for (Player player : playerArrayList) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
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
        scoreboard.createBoardForPlayers(quests, nbQuest);
    }

    public void sendTitleToAllPlayers(ArrayList<Player> playerArrayList, String title, String message){
        for(Player player : playerArrayList){
            if (getTeamName().equals("Crewmates")){
                ChatUtilities.broadcast("On est dans le if");
                player.sendTitle(BLUE + title, message);
            }else{
                ChatUtilities.broadcast("On est dans le else");
                player.sendTitle(RED + title, message);
            }
        }
    }

    public boolean questDone(String questName){
        for (QuestList questList : quests) {
            if (questList.isQuestDone(questName)) {
                return true;
            }
        }
        return false;
    }

    public boolean allQuestDone(){

        for(QuestList questlist : getQuests()){
            ChatUtilities.broadcast("Nom du joueur qu'on check : " + questlist.getPlayer());
            for (Quest quest : questlist.getQuestsForPlayer()){
                ChatUtilities.broadcast("Nom de la quête qu'on check : " + quest.getQuestName());
                ChatUtilities.broadcast("état de cette quest : " + quest.isDone());
                if (!quest.isDone()) {
                    return false;
                }
            }
        }
        return true;
    }

    public QuestList getQuestList(Player player) throws Exception {
        for (QuestList questList : quests){
            if (questList.getPlayer() == player){
                return questList;
            }
        }
        throw new Exception("Problème avec le getQuest");
    }

}
