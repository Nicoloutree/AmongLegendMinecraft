package amonglegendminecraft.amonglegendminecraft.utils;

import jdk.tools.jlink.internal.Platform;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class LocationUtilities {
    private static final Location spawnLocation = new Location(
            Bukkit.getWorld("world"),0, 0,0).toHighestLocation();

    public static void teleportToSpawn(Player player) {
        player.teleport(spawnLocation);
    }

    public static void teleportAllToSpawn() {
        for (Player p : Bukkit.getOnlinePlayers())
            teleportToSpawn(p);
    }
    //Teleporte un joueur aléatoirement dans le perimetre min max
    public static void teleportToRandomLocation(Player player,int min, int max){
        int x = randomNum(min, max);
        int height = 0;
        int z = randomNum(min, max);
        Location loc = new Location(player.getWorld(), x, height, z);
        player.teleport(loc.toHighestLocation());

    }
    //Téléporte deux joueurs au meme endroit aléatoirement dans un perimetre min max
    public static void teleportDuoToRandomLocation(Player player,Player player2, int min, int max){
        int x = randomNum(min, max);
        int height = 0;
        int z = randomNum(min, max);
        Location loc = new Location(player.getWorld(), x, height, z);
        player.teleport(loc.toHighestLocation());
        player2.teleport(loc.toHighestLocation());
    }

    //Téléporte tout les joueurs en duo, si il y a un nombre impair de joueurs, le dernier joueur est seul
    public static void teleportAllDuoToRandomLocation(ArrayList<Player> playerArrayList,int min,int max){
        Collections.shuffle(playerArrayList);
        for (int i=0;i<playerArrayList.size();i+=2){
            if(i+1<playerArrayList.size())
            teleportDuoToRandomLocation(playerArrayList.get(i),playerArrayList.get(i+1),min, max);
            else teleportToRandomLocation(playerArrayList.get(i),min,max);
        }
    }

    private static int randomNum(Integer lownum, Integer highnum) {
        Random rand = new Random();
        return lownum + (int) (rand.nextDouble() * ((highnum - lownum) + 1));
    }

    public static void createPlatform(Location location, int tailleBase, int tailleWall, Material base, Material wall){

        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();


        for (double i = 0; i < tailleBase; i++){
            location.set(x+i,y,z).getBlock().setType(base);
            for (double k = 0; k < tailleBase; k++){
                location.set(x+i,y,z+k).getBlock().setType(base);
            }
        }

        //Construction des murs
        for(double i = 1;i<=tailleWall;i++){
            for(double j=0;j<tailleBase;j++){
                location.set(x+j,y+i,z).getBlock().setType(wall);
                location.set(x+j,y+i,z+tailleBase).getBlock().setType(wall);
                location.set(x,y+i,z+j).getBlock().setType(wall);
                location.set(x+tailleBase,y+i,z+j).getBlock().setType(wall);
            }
        }
        location.set(x,y,z);
    }

    public static void removePlatform(Location location, int tailleBase, int tailleWall){
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        for (int i = 0; i < tailleBase; i++){
            location.set(x+i,y,z).getBlock().setType(Material.AIR);
            for (int k = 0; k < tailleBase; k++){
                location.set(x+i,y,z+k).getBlock().setType(Material.AIR);
            }
        }
        location.set(x,y,z);
    }

    public static void teleportAllPlayersToLocationForMeeting(ArrayList<Player> players, Location location, float taillebase){
        double x = location.getX()+(taillebase/2);
        double y = location.getY()+1;
        double z = location.getZ()+(taillebase/2);

        Location location2 = new Location(Bukkit.getWorld("world"),x,y,z);

        for (Player player : players) {
            player.teleport(location2);
        }
    }
}
