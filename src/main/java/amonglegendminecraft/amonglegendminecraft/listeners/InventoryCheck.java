package amonglegendminecraft.amonglegendminecraft.listeners;

import amonglegendminecraft.amonglegendminecraft.handlers.Quest;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryCheck implements Listener {

    private final ChatUtilities chatUtilities = new ChatUtilities();

    @Deprecated
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        final Player p = event.getPlayer();
        Item item = event.getItem();
        check_player(p, item);
    }

    public void check_player(Player p, Item item) {
        if(item.getItemStack().getType() == Material.OAK_LOG) {
            chatUtilities.broadcast("Test");
        }
    }
}