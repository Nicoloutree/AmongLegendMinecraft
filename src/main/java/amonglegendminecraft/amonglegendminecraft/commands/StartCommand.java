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
            QuestList quest = new QuestList();
            quest.createQuest();
            //ImpostorTeam impostors = new ImpostorTeam("Impostors", new ArrayList<>());
            CrewmateTeam crewmates = new CrewmateTeam("Crewmates", new ArrayList<>(), quest);

            /*----------------------------------------------------------------------------------------------*/

            //Getting all the Online players and putting em in an array
            ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());

            //Randomly picking impostor among the online player according to the args of the command
            //impostors.pickImpostor(playersArray, Integer.parseInt(args[0]));

            //Addding every other player to the crewmate
            for(Player player : playersArray){
                //if(!impostors.isFromTeam(player)){
                    crewmates.addPlayer(player);
                //}
            }
            //Randomly teleport all connected in duos (if possible) players in a square perimeter of 2000block
            locationUtilities.teleportAllDuoToRandomLocation(playersArray, -1000,1000);

            //Display teamnanme on the screen of all the players
            crewmates.displayTeam(args[0]);
            //impostors.displayTeam(args[0]);

            crewmates.initialiseQuestsPerPlayers(quest,3);
            // Creation of a timerEvent
            //TimeEvent timeEvent=new TimeEvent(playersArray,crewmates.getPlayerArrayList(),impostor.getPlayerArrayList());
            //timeEvent.timerIncrement(Score time);

            //Empty the team to restart the game (this will be moved in the event that stops the game when de ender dragon is killed
            crewmates.emptyTeam();
            //impostors.emptyTeam();


            chatUtilities.broadcast("everything executed");
        }
        return true;
    }
}
