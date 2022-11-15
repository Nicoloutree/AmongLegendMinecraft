package amonglegendminecraft.amonglegendminecraft.listeners;

import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        LocationUtilities locationUtilities = new LocationUtilities();
        locationUtilities.teleportToSpawn(player);
    }
}