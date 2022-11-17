package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.bukkit.ChatColor.BLUE;

public class CrewmateTeam extends Team{
    private QuestList questList;
    public CrewmateTeam(String teamName, ArrayList<Player> playerArrayList, QuestList questList) {
        super(teamName, playerArrayList);
        this.questList = questList;
    }

    //display the team name on the screen of the player
    public void displayTeam(String nbImpo){
        for(Player player : this.getPlayerArrayList()){
            player.sendTitle(BLUE + this.getTeamName(),BLUE + "There are "+ nbImpo +" impostors among us");
        }
    }

    public void initialiseQuestsPerPlayers(QuestList questList, int nbQuest){
        createScoreboard scoreboard = new createScoreboard();
        int k = 0;

        for (Player player : this.getPlayerArrayList()){
            Collections.shuffle(questList.getQuestArrayListPerPlayer()); //On mélange l'arraylist
            scoreboard.createBoardForPlayer(player, String.valueOf(k));
            k++;
            for (int i = 0; i < nbQuest; i++){
                scoreboard.addObjective(player, questList.getQuestArrayListPerPlayer().get(i));

            }
            player.setScoreboard(scoreboard.getScoreboard());
        }
    }
}
