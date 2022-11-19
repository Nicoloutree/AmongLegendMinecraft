package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;

public class TimeEvent {
    private ArrayList<Player> playerArrayList;
    private ArrayList<Player> crewmateArrayList;
    private ArrayList<Player> impostorArrayList;

    public TimeEvent(ArrayList<Player> playerArrayList, ArrayList<Player> crewmateArrayList, ArrayList<Player> impostorArrayList) {
        this.playerArrayList = playerArrayList;
        this.crewmateArrayList = crewmateArrayList;
        this.impostorArrayList = impostorArrayList;
    }

    public void timerIncrement(Score time) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(AmongLegendMinecraft.plugin, new Runnable() {
            @Override
            public void run() {
                time.setScore(time.getScore() + 1);
                detectEvent(time.getScore());


            }
        }, 0L, 20L);
    }
    public void detectEvent(int timeInt){
        if (timeInt == 10) {
            giveSword(this.impostorArrayList);
        }
    }
    /*
    Les méthodes ci dessous, sont des events qui se déclenchent en fonction du temps
     */
    @Deprecated
    public void giveSword(ArrayList<Player> playerArrayList)
    {
        for (Player player : playerArrayList) {
            Inventory inv = player.getInventory();
            ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
            ItemMeta metaSword = sword.getItemMeta();
            metaSword.setDisplayName("§4Épée Sus");
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 7);
            sword.addEnchantment(Enchantment.DURABILITY, 10);
            sword.setItemMeta(metaSword);
            player.sendMessage("Vous avez reçu votre épée d'imposteur");
            inv.addItem(sword);
        }
    }
}