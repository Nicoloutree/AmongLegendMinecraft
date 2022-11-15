package amonglegendminecraft.amonglegendminecraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class LocationUtilities {

    private Location randomLocation;
    private Location spawnLocation = new Location(
            Bukkit.getWorld("Lobby"),0, 64,0);

    public void teleportToSpawn (Player player) {
        player.teleport(spawnLocation);
    }

    public void teleportAllToSpawn(){
        for (Player p : Bukkit.getOnlinePlayers())
            teleportToSpawn(p);
    }
}
