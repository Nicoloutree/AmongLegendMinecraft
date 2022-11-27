package amonglegendminecraft.amonglegendminecraft.listeners;

import amonglegendminecraft.amonglegendminecraft.handlers.Quest;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Async;

public class InventoryCheck implements Listener {

    private final ChatUtilities chatUtilities = new ChatUtilities();

    /*
    @Deprecated
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        final Player p = event.getPlayer();//gets the player
        Inventory inventory=p.getInventory();//gets his inventory
        int nbLog=0;
        //Increments the number of logs when the player has a type of log in his inventory
        if(inventory.contains(Material.OAK_LOG))nbLog+=1;
        if(inventory.contains(Material.ACACIA_LOG))nbLog+=1;
        if(inventory.contains(Material.SPRUCE_LOG))nbLog+=1;
        if(inventory.contains(Material.BIRCH_LOG))nbLog+=1;
        if(inventory.contains(Material.DARK_OAK_LOG))nbLog+=1;
        if(inventory.contains(Material.JUNGLE_LOG))nbLog+=1;
        if(inventory.contains(Material.CRIMSON_STEM))nbLog+=1;
        if(inventory.contains(Material.WARPED_STEM))nbLog+=1;
        if(inventory.contains(Material.MANGROVE_LOG))nbLog+=1;
        //If he has more than 6 logs
        if(nbLog>=6){
            chatUtilities.broadcast("Test");

        }





    }

    public boolean check_player(Player p, Item item,Material material) {
        return item.getItemStack().getType() == material;
    }

     */
}