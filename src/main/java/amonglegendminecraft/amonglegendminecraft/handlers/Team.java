package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.ArrayList;

import static java.awt.Color.RED;
import static org.bukkit.ChatColor.BLUE;

public abstract class Team {

    private String teamName;
    private ArrayList<PlayerTeam> playerArrayList;
    public Team(String teamName,ArrayList<PlayerTeam> playerArrayList) {
        this.teamName = teamName;
        this.playerArrayList = playerArrayList;
    }

    public ArrayList<PlayerTeam> getPlayerArrayList() {
        return playerArrayList;
    }

    public void setPlayerArrayList(ArrayList<PlayerTeam> playerArrayList) {
        this.playerArrayList = playerArrayList;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    //Return true if the input player is in the team object preciding it
    public boolean isFromTeam(PlayerTeam player){
        return this.playerArrayList.contains(player);
    }

    public void addPlayer(PlayerTeam player){
        this.playerArrayList.add(player);
    }

    public void removePlayer(PlayerTeam player){
        this.playerArrayList.remove(player);
    }

    public void emptyTeam(){
        for (PlayerTeam player : playerArrayList) {
            player.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
        this.playerArrayList.clear();
    }

    public String playerArrayListToString() { //TO DO Check the toString methods from java to see if it's usable in this case
        String res = "";
        for (PlayerTeam player : this.playerArrayList){
            res = res + " " + player.getPlayer().getName();
        }
        return res;
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

    public boolean allPlayerQuestDone(){

        for (int i = 0; i < playerArrayList.size(); i++){
            if (!playerArrayList.get(i).allQuestDone()){
                return false;
            }
        }
        return true;

    }

}
