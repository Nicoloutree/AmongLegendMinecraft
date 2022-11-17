package amonglegendminecraft.amonglegendminecraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class LocationUtilities {
    private final Location spawnLocation = new Location(
            Bukkit.getWorld("world"), 0, 0, 0).toHighestLocation();

    public void teleportToSpawn(Player player) {
        player.teleport(spawnLocation);
    }

    public void teleportAllToSpawn() {
        for (Player p : Bukkit.getOnlinePlayers())
            teleportToSpawn(p);
    }

    //Teleporte un joueur aléatoirement dans le perimetre min max
    public void teleportToRandomLocation(Player player, int min, int max) {
        int x = randomNum(min, max);
        int height = 0;
        int z = randomNum(min, max);
        Location loc = new Location(player.getWorld(), x, height, z);
        player.teleport(loc.toHighestLocation());

    }

    //Téléporte deux joueurs au meme endroit aléatoirement dans un perimetre min max
    public void teleportDuoToRandomLocation(Player player, Player player2, int min, int max) {
        int x = randomNum(min, max);
        int height = 0;
        int z = randomNum(min, max);
        Location loc = new Location(player.getWorld(), x, height, z);
        player.teleport(loc.toHighestLocation());
        player2.teleport(loc.toHighestLocation());
    }

    //Téléporte tout les joueurs en duo, si il y a un nombre impair de joueurs, le dernier joueur est seul
    public void teleportAllDuoToRandomLocation(ArrayList<Player> playerArrayList, int min, int max) {
        Collections.shuffle(playerArrayList);
        for (int i = 0; i < playerArrayList.size(); i += 2) {
            if (i + 1 < playerArrayList.size())
                teleportDuoToRandomLocation(playerArrayList.get(i), playerArrayList.get(i + 1), min, max);
            else teleportToRandomLocation(playerArrayList.get(i), min, max);
        }
    }

    private int randomNum(Integer lownum, Integer highnum) {
        Random rand = new Random();
        return lownum + (int) (rand.nextDouble() * ((highnum - lownum) + 1));
    }
}