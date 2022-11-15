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
            Team impostors = new Team("Impostors");
            Team crewmates = new Team("Crewmates");
            Collection<? extends Player> participants = Bukkit.getOnlinePlayers();
            ArrayList<Player> playersArray = new ArrayList<Player>(participants);
            impostors.pickImpostor(playersArray,1);
            for(Player player : playersArray){
                if(!impostors.isFromTeam(player)){
                    crewmates.addPlayer(player);
                }
            }
            crewmates.displayTeam(false);
            impostors.displayTeam(true);
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
