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
    private boolean gameStarted = false;

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

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

            if (crewmates.getPlayerArrayList().isEmpty() && impostors.getPlayerArrayList().isEmpty()){
                setGameStarted(false);
            }
            if (!gameStarted){
                /*---------------------------------------Declaration--------------------------------------------*/
                questsImpostors.clear();
                questsCrewmates.clear();

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


                QuestList quest2 = new QuestList();

                for (int i = 0; i < crewmates.getPlayerArrayList().size(); i++){
                    questsCrewmates.add(new QuestList());
                }
                for (int i = 0; i < impostors.getPlayerArrayList().size(); i++){
                    questsImpostors.add(new QuestList());
                }


                for (int i = 0; i < questsCrewmates.size(); i++){
                    questsCrewmates.get(i).createQuest(3);
                    questsCrewmates.get(i).setPlayer(crewmates.getPlayerArrayList().get(i));
                }

                crewmates.setQuests(questsCrewmates);

                for (int i = 0; i < questsImpostors.size(); i++){
                    questsImpostors.get(i).createQuest(3);
                    questsImpostors.get(i).setPlayer(impostors.getPlayerArrayList().get(i));
                }

                impostors.setQuests(questsImpostors);


                //Randomly teleport all connected in duos (if possible) players in a square perimeter of 2000block
                locationUtilities.teleportAllDuoToRandomLocation(playersArray, -100,100);

                //Display teamnanme on the screen of all the players
                crewmates.displayTeam(args[0]);
                impostors.displayTeam(args[0]);

                impostors.initialiseQuestsPerPlayers(3);
                crewmates.initialiseQuestsPerPlayers(3);

                commonListeners.setCrewmates(crewmates);
                commonListeners.setImpostors(impostors);

                setGameStarted(true);
                chatUtilities.broadcast("everything executed");

            }else{
                ChatUtilities chatUtilities =  new ChatUtilities();
                chatUtilities.broadcast("Une partie est déjà en cours d'exécution !");
                return false;
            }
        }
        return true;
    }
}
