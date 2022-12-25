package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.bukkit.ChatColor.BLUE;

public class CrewmateTeam extends Team{

    public CrewmateTeam(String teamName, ArrayList<PlayerTeam> playerArrayList) {
        super(teamName, playerArrayList);

    }

    //display the team name on the screen of the player
    public void displayTeam(String nbImpo){
        for(PlayerTeam player : this.getPlayerArrayList()){
            player.getPlayer().sendTitle(BLUE + this.getTeamName(),BLUE + "There are "+ nbImpo +" impostors among us");
        }
    }



}
