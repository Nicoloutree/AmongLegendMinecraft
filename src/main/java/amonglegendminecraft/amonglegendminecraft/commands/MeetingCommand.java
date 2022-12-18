package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import amonglegendminecraft.amonglegendminecraft.handlers.PlayerVoted;
import amonglegendminecraft.amonglegendminecraft.handlers.VotePlayers;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class MeetingCommand implements CommandExecutor {

    private StartCommand gameData;
    private int duree;
    private boolean meetingActivate = false;
    private ArrayList<VotePlayers> votePlayers = new ArrayList<>();
    private ArrayList<PlayerVoted> playerVoteds = new ArrayList<>();


    public ArrayList<PlayerVoted> getPlayerVoteds() {
        return playerVoteds;
    }

    public void setPlayerVoteds(ArrayList<PlayerVoted> playerVoteds) {
        this.playerVoteds = playerVoteds;
    }

    public boolean isMeetingActivate() {
        return meetingActivate;
    }

    public ArrayList<VotePlayers> getVotePlayers() {
        return votePlayers;
    }

    public void setVotePlayers(ArrayList<VotePlayers> votePlayers) {
        this.votePlayers = votePlayers;
    }

    public StartCommand getGameData() {
        return gameData;
    }

    public void setGameData(StartCommand gameData) {
        this.gameData = gameData;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (command.getName().equalsIgnoreCase("meeting") && !meetingActivate) {

            meetingActivate = true;
            int taillebase = 10;
            int taillewall = 10;

            Location meetingLocation = new Location(Bukkit.getWorld("world"),0, 150,0);
            ArrayList<Location> locations = new ArrayList<>();
            ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());


            for (int i = 0; i < playersArray.size(); i++){
                votePlayers.add(i, new VotePlayers(playersArray.get(i)));
                playerVoteds.add(i, new PlayerVoted(playersArray.get(i)));
            }

            for (int i = 0; i < playersArray.size(); i++){
                locations.add(playersArray.get(i).getLocation());
            }

            LocationUtilities.teleportAllPlayersToLocation(playersArray, meetingLocation);
            LocationUtilities.createPlatform(meetingLocation,taillebase, taillewall, Material.OAK_LOG,Material.COBBLESTONE);

            Bukkit.getWorld("world").setPVP(false);


            duree = 15;
            ChatUtilities.title("Meeting","Vous avez 1min30 pour voter !");
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask(AmongLegendMinecraft.plugin, new Runnable() {
                @Override
                public void run() {
                    if (duree == 60){
                        ChatUtilities.title("Meeting","Il vous reste 60 secondes !");
                    }else if(duree == 30){
                        ChatUtilities.title("Meeting", "Il vous reste 30 secondes !");
                    }else if (duree == 0){

                        int k = 0;
                        int compteur = 0;
                        for (int i = 0; i < playerVoteds.size() && compteur != -1; i++){
                            if (compteur < playerVoteds.get(i).getNbVotes()){
                                compteur = playerVoteds.get(i).getNbVotes();
                                k = i;
                            }else if(compteur == playerVoteds.get(i).getNbVotes() && compteur != 0){
                                compteur = -1;
                                ChatUtilities.title("Personne est éjecté", "Deux personnes ont le même nombre de votes");
                            }
                        }
                        if (compteur == 0){
                            ChatUtilities.title("Personne est éjecté", "Personne a voté");
                        }else if (compteur != -1){
                            playerVoteds.get(k).getPlayerVoted().setHealth(0);
                            ChatUtilities.title(playerVoteds.get(k).getPlayerVoted().getName() + " est éjecté", "Il a obtenu" + compteur + " votes");

                        }


                        playerVoteds.clear();
                        votePlayers.clear();
                        meetingActivate = false;
                        Bukkit.getWorld("world").setPVP(true);
                        scheduler.cancelTasks(AmongLegendMinecraft.plugin);

                        for (int i = 0; i < playersArray.size(); i++){
                            playersArray.get(i).teleport(locations.get(i));
                        }

                        LocationUtilities.removePlatform(meetingLocation,10,10);




                    }

                    duree--;
                    ChatUtilities.broadcast("temps restant : "+duree);
                }
            }, 0L,20L); //20 tick = 1 seconde
        }
        return true;
    }
}
