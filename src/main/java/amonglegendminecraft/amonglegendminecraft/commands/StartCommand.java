package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.*;
import amonglegendminecraft.amonglegendminecraft.handlers.Team;
import amonglegendminecraft.amonglegendminecraft.listeners.CommonListeners;
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

    private CommonListeners commonListeners = new CommonListeners();
    private ImpostorTeam impostors = new ImpostorTeam("Impostors", new ArrayList<>());
    private CrewmateTeam crewmates = new CrewmateTeam("Crewmates", new ArrayList<>());
    private ArrayList<QuestList> questsCrewmates = new ArrayList<QuestList>();
    private ArrayList<QuestList> questsImpostors = new ArrayList<QuestList>();


    public CommonListeners getCommonListeners() {
        return commonListeners;
    }

    public ImpostorTeam getImpostors() {
        return impostors;
    }

    public CrewmateTeam getCrewmates() {
        return crewmates;
    }



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) { return true; }

        if (command.getName().equalsIgnoreCase("start")){

            /*---------------------------------------Declaration--------------------------------------------*/

            LocationUtilities locationUtilities = new LocationUtilities();
            ChatUtilities chatUtilities =  new ChatUtilities();


            impostors = new ImpostorTeam("Impostors", new ArrayList<>());
            crewmates = new CrewmateTeam("Crewmates", new ArrayList<>());


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

            //Initialisation des quêtes

            QuestList quest = new QuestList();
            QuestList quest2 = new QuestList();
            quest.createQuest();
            quest2.createQuest();
            int i = 0;
            for (Player player : crewmates.getPlayerArrayList()){
                quest.setPlayer(player);
                questsCrewmates.add(quest);

                i++;
            }
            crewmates.setQuests(questsCrewmates);

            for (Player player : impostors.getPlayerArrayList()){
                quest2.setPlayer(player);
                questsImpostors.add(quest2);
            }
            impostors.setQuests(questsImpostors);



            //Randomly teleport all connected in duos (if possible) players in a square perimeter of 2000block
            locationUtilities.teleportAllDuoToRandomLocation(playersArray, -1000,1000);

            //Display teamnanme on the screen of all the players
            crewmates.displayTeam(args[0]);
            impostors.displayTeam(args[0]);

            impostors.initialiseQuestsPerPlayers(3);
            crewmates.initialiseQuestsPerPlayers(3);

            commonListeners.setCrewmates(crewmates);
            commonListeners.setImpostors(impostors);


            chatUtilities.broadcast("everything executed");
        }
        return true;
    }
}
