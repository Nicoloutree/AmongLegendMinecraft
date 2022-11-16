package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import static org.bukkit.ChatColor.BLUE;

public class CrewmateTeam extends Team{
    public CrewmateTeam(String teamName, ArrayList<Player> playerArrayList) {
        super(teamName, playerArrayList);
    }

    //display the team name on the screen of the player
    public void displayTeam(String nbImpo){
        for(Player player : this.getPlayerArrayList()){
            player.sendTitle(BLUE + this.getTeamName(),BLUE + "There are "+ nbImpo +" impostors among us");
        }
    }
}
