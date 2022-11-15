package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.Team;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collection;


public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) { return true; }

        if (command.getName().equalsIgnoreCase("start")){

            //Team Creation
            Team impostors = new Team("Impostors");
            Team crewmates = new Team("Crewmates");

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

            //Display teamnanme on the screen of all the players
            crewmates.displayTeam(false,args[0]);
            impostors.displayTeam(true, args[0]);

            //Empty the team to restart the game (this will be moved in the event that stops the game when de ender dragon is killed
            crewmates.emptyTeam();
            impostors.emptyTeam();
        }
        /*Random rand = new Random();

        for(Player player : Bukkit.getOnlinePlayers()){
            int x = -1000 + (int)(rand.nextDouble() * (1000 - -1000 + 1));
            int height = 0;
            int z = -1000 + (int)(rand.nextDouble() * (1000 - -1000 + 1));
            Location loc = new Location(player.getWorld(), x, height, z);
            player.teleportAsync(loc.toHighestLocation());
        }*/
        return true;
    }
}
