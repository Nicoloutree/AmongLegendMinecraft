package amonglegendminecraft.amonglegendminecraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;


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

    public void teleportToRandomLocation(Player player, int min, int max){
        int x = randomNum(min, max);
        int height = 0;
        int z = randomNum(min, max);
        Location loc = new Location(player.getWorld(), x, height, z);
        player.teleport(loc.toHighestLocation());
    }

    public void teleportAllToRandomLocation(ArrayList<Player> playerArrayList,int min,int max){
        for (Player player : playerArrayList){
            teleportToRandomLocation(player, min, max);
        }
    }
    private int randomNum(Integer lownum, Integer highnum) {
        Random rand = new Random();
        return lownum + (int)(rand.nextDouble() * ((highnum - lownum) + 1));
    }
}
