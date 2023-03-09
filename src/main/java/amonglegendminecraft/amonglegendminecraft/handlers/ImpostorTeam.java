package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

import static org.bukkit.ChatColor.BLUE;
import static org.bukkit.ChatColor.RED;

public class ImpostorTeam extends Team{

    private ArrayList<Sabotage> sabotageArrayList;
    public ImpostorTeam(String teamName, ArrayList<PlayerTeam> playerArrayList) {
        super(teamName, playerArrayList);
        this.sabotageArrayList = Sabotage.initSabotage();
    }


    //Takes and arrayList as input, shuffle it and then place an nbImpo impostor in the impostor team
    //this way we randomize the impostor everytime we start a game
    public void pickImpostor(ArrayList<PlayerTeam> playerArrayList, int nbImpo){
        Collections.shuffle(playerArrayList);
        for (int i = 0 ; i <= nbImpo-1; i++){
            this.addPlayer(playerArrayList.get(i));
            playerArrayList.get(i).setTeam(this);
        }
    }
    public ArrayList<Sabotage> getSabotageArrayList() {
        return sabotageArrayList;
    }

    public void setSabotageArrayList(ArrayList<Sabotage> sabotageArrayList) {
        this.sabotageArrayList = sabotageArrayList;
    }

    //display the team name on the screen of the player
    public void displayTeam(String nbImpo){
        for(PlayerTeam player : this.getPlayerArrayList()){
            player.getPlayer().sendTitle(RED + this.getTeamName(),RED + playerArrayListToString());
        }
    }
    public Sabotage getSabotageElementFromName( String nameSabotage){
        for (int i = 0; i < sabotageArrayList.size(); i++){
            if (sabotageArrayList.get(i).getName().compareToIgnoreCase(nameSabotage) == 0){
                return sabotageArrayList.get(i);
            }
        }
        return null;
    }

    public void sendMessage(String title, String message){
        for(PlayerTeam player : this.getPlayerArrayList()){
            player.getPlayer().sendTitle(RED + title, RED + message);
        }
    }
}
