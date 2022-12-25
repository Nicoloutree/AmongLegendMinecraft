package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.*;
import amonglegendminecraft.amonglegendminecraft.listeners.CommonListeners;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class StartCommand implements CommandExecutor {

    private MeetingCommand meetingData;
    int nbQuest = 3;
    private CommonListeners commonListeners = new CommonListeners();
    private ImpostorTeam impostors;
    private CrewmateTeam crewmates;

    private ArrayList<PlayerTeam> playerTeamArrayList = new ArrayList<>();

    public ArrayList<PlayerTeam> getPlayerTeamArrayList() {
        return playerTeamArrayList;
    }

    private boolean gameStarted = false;

    public MeetingCommand getMeetingData() {
        return meetingData;
    }

    public void setMeetingData(MeetingCommand meetingData) {
        this.meetingData = meetingData;
    }

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
                impostors.emptyTeam();
                crewmates.emptyTeam();
                /*---------------------------------------Declaration--------------------------------------------*/


                impostors = new ImpostorTeam("Impostors", new ArrayList<>());
                crewmates = new CrewmateTeam("Crewmates", new ArrayList<>());


                /*----------------------------------------------------------------------------------------------*/

                //Getting all the Online players and putting em in an array
                ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                for(Player player : playersArray){
                    playerTeamArrayList.add(new PlayerTeam(player,nbQuest));
                }



                //Randomly picking impostor among the online player according to the args of the command
                impostors.pickImpostor(playerTeamArrayList, Integer.parseInt(args[0]));

                //Addding every other player to the crewmate
                for(PlayerTeam player : playerTeamArrayList){
                    if(!impostors.isFromTeam(player)){
                        crewmates.addPlayer(player);
                    }
                }

                ChatUtilities.broadcast("Joueur dans playerTeamArrayList : ");
                for (int i = 0; i < playerTeamArrayList.size(); i++){
                    ChatUtilities.broadcast("Joueur : " + playerTeamArrayList.get(i).getPlayer().getName());
                    ChatUtilities.broadcast("nbQuest : " + playerTeamArrayList.get(i).getNbQuests());
                }
                //Initialisation des quêtes

                //Randomly teleport all connected in duos (if possible) players in a square perimeter of 2000block
                LocationUtilities.teleportAllDuoToRandomLocation(playersArray, -100,100);
                //Display teamnanme on the screen of all the players
                crewmates.displayTeam(args[0]);
                impostors.displayTeam(args[0]);


                setGameStarted(true);

                commonListeners.setCrewmates(crewmates);
                commonListeners.setImpostors(impostors);
                commonListeners.setPlayerTeamArrayList(playerTeamArrayList);
                commonListeners.setMeetingCommand(meetingData);
                commonListeners.setHasStarted(true);

                createScoreboard.createBoardForPlayers(playerTeamArrayList);


                ChatUtilities.broadcast("everything executed");

            }else{

                ChatUtilities.broadcast("Une partie est déjà en cours d'exécution !");
                return false;
            }
        }

        return true;
    }

}
