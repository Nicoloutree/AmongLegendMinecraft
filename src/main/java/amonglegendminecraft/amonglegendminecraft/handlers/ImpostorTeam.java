package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import static org.bukkit.ChatColor.RED;

public class ImpostorTeam extends Team{

    public ImpostorTeam(String teamName, ArrayList<Player> playerArrayList) {
        super(teamName, playerArrayList);
    }

    //Takes and arrayList as input, shuffle it and then place an nbImpo impostor in the impostor team
    //this way we randomize the impostor everytime we start a game
    public void pickImpostor(ArrayList<Player> playerArrayList, int nbImpo){
        Collections.shuffle(playerArrayList);
        for (int i = 0 ; i <= nbImpo-1; i++){
            this.addPlayer(playerArrayList.get(i));
        }
    }

    //display the team name on the screen of the player
    public void displayTeam(String nbImpo){
        for(Player player : this.getPlayerArrayList()){
            player.sendTitle(RED + this.getTeamName(),RED + playerArrayListToString());
        }
    }
}
