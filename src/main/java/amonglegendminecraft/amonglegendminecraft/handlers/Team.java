package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Collections;
import static org.bukkit.ChatColor.*;

public class Team {

    private String teamName;

    private ArrayList<Player> playerArrayList;
    public Team(String teamName, ArrayList<Player> playerArrayList) {
        this.teamName = teamName;
        this.playerArrayList = playerArrayList;
    }

    public Team(String teamName) {
        this.teamName = teamName;
        this.playerArrayList = new ArrayList<Player>();
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public void setPlayerArrayList(ArrayList<Player> playerArrayList) {
        this.playerArrayList = playerArrayList;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    //Return true if the input player is in the team object preciding it
    public boolean isFromTeam(Player player){
        if (this.playerArrayList.contains(player))
            return true;
        return false;
    }

    public void addPlayer(Player player){
        this.playerArrayList.add(player);
    }

    public void removePlayer(Player player){
        this.playerArrayList.remove(player);
    }
    //Takes and arrayList as input, shuffle it and then place an nbImpo impostor in the impostor team
    //this way we randomize the impostor everytime we start a game
    public void pickImpostor(ArrayList<Player> playerArrayList, int nbImpo){
        Collections.shuffle(playerArrayList);
        for (int i = 0 ; i <= nbImpo-1; i++){
            this.addPlayer(playerArrayList.get(i));
        }
    }

    public void emptyTeam(){
        this.playerArrayList.clear();
    }
    //display the team name on the screen of the player
    public void displayTeam(boolean b, String nbImpo){
        if(b){
            for(Player player : this.playerArrayList){
                player.sendTitle(RED + this.teamName,RED + playerArrayListToString());
            }
        }else{
            for(Player player : this.playerArrayList){
                player.sendTitle(BLUE + this.teamName,BLUE + "There are "+ nbImpo +" among us");
            }
        }
    }

    public String playerArrayListToString() { //TO DO Check the toString methods from java to see if it's usable in this case
        String res = "";
        for (Player player : this.playerArrayList){
            res = res + " " + player.getName();
        }
        return res;
    }
}
