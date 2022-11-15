package amonglegendminecraft.amonglegendminecraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class LocationUtilities {
    private final Location spawnLocation = new Location(
            Bukkit.getWorld("world"),0, 0,0).toHighestLocation();

    public void teleportToSpawn (Player player) {
        player.teleport(spawnLocation);
    }

    public void teleportAllToSpawn(){
        for (Player p : Bukkit.getOnlinePlayers())
            teleportToSpawn(p);
    }
}
