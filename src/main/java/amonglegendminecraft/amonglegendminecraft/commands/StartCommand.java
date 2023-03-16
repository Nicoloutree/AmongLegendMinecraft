package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import amonglegendminecraft.amonglegendminecraft.handlers.*;
import amonglegendminecraft.amonglegendminecraft.listeners.CommonListeners;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.*;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class StartCommand implements CommandExecutor {

    private MeetingCommand meetingData;
    private int nbQuest = 3;
    private int timerborder;
    private CommonListeners commonListeners = new CommonListeners();
    private ImpostorTeam impostors = new ImpostorTeam("Impostors", new ArrayList<>());
    private CrewmateTeam crewmates = new CrewmateTeam("Crewmates", new ArrayList<>());

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
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule logAdminCommands false");
                timerborder = 600;
                impostors.emptyTeam();
                crewmates.emptyTeam();
                playerTeamArrayList.clear();

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
                    if(!impostors.isFromTeam(player)) {
                        crewmates.addPlayer(player);
                        player.setTeam(crewmates);
                    }
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
                Bukkit.getWorld("world").setPVP(false);

                WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
                wb.setCenter(0, 0);
                wb.setSize(500);
                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                scheduler.scheduleSyncRepeatingTask(AmongLegendMinecraft.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (timerborder == 300){
                            ChatUtilities.title("Border","La bordure sera retirée dans 5 minutes");
                        }else if(timerborder == 60){
                            ChatUtilities.title("Border", "La bordure sera retirée dans 1 minute");
                        }else if (timerborder == 0){

                            ChatUtilities.title("Border","La bordure est retirée et le pvp activé");
                            Bukkit.getWorld("world").setPVP(true);

                            wb.setCenter(0,0);
                            wb.setSize(1000,1000);

                            scheduler.cancelTasks(AmongLegendMinecraft.plugin);

                        }

                        timerborder--;
                    }
                }, 0L,20L); //20 tick = 1 seconde
                ValidateQuest.setLastrun(false);
                ChatUtilities.broadcast("everything executed");

            }else{

                ChatUtilities.broadcast("Une partie est déjà en cours d'exécution !");
                return false;
            }
        }

        return true;
    }

}
