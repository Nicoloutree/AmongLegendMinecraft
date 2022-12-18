package amonglegendminecraft.amonglegendminecraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class ChatUtilities {

    public static void broadcast(String msg){
        for(Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(starter() + msg);
    }

    private static String starter() {
        return DARK_GRAY + "[" + RED + "AmongMinecraftLegend" + DARK_GRAY + "] "
                + WHITE;
    }

    public static void title(String titre, String subtitle){
        for(Player player : Bukkit.getOnlinePlayers())
            player.sendTitle(titre,subtitle);
    }
}
