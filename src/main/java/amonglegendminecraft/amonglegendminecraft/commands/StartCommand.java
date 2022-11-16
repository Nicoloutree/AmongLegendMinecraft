package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.*;
import amonglegendminecraft.amonglegendminecraft.handlers.Team;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) { return true; }

        if (command.getName().equalsIgnoreCase("start")){

            /*---------------------------------------Declaration--------------------------------------------*/

            LocationUtilities locationUtilities = new LocationUtilities();
            ChatUtilities chatUtilities =  new ChatUtilities();
            ImpostorTeam impostors = new ImpostorTeam("Impostors", new ArrayList<>());
            CrewmateTeam crewmates = new CrewmateTeam("Crewmates", new ArrayList<>());
            final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            final Objective objective = scoreboard.registerNewObjective("General", "dummy");
            QuestList questList = new QuestList();
            Timer timer = new Timer();

            /*----------------------------------------------------------------------------------------------*/

            //Getting all the Online players and putting em in an array
            ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());

            //Randomly picking impostor among the online player according to the args of the command
            impostors.pickImpostor(playersArray, Integer.parseInt(args[0]));

            //Addding every other player to the crewmate
            for(Player player : playersArray){
                if(!impostors.isFromTeam(player)){
                    crewmates.addPlayer(player);
                }
            }
            //Randomly teleport all connected players in a square perimeter of 2000block
            locationUtilities.teleportAllToRandomLocation(playersArray, -1000,1000);

            //Display teamnanme on the screen of all the players
            crewmates.displayTeam(args[0]);
            impostors.displayTeam(args[0]);

            //Empty the team to restart the game (this will be moved in the event that stops the game when de ender dragon is killed
            crewmates.emptyTeam();
            impostors.emptyTeam();

            objective.setDisplayName("Time");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score time = objective.getScore("0:00:01");
            Score test = objective.getScore("test");

            time.setScore(0);
            timer.displayTimer(playersArray,scoreboard);
            //timer.timerIncrement(time);

            for(Player p : playersArray){
                QuestList questList = new QuestList(p);
                questList.
            }

            chatUtilities.broadcast("everything executed");
        }
        return true;
    }
}
