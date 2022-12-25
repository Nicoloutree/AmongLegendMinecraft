package amonglegendminecraft.amonglegendminecraft.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SwordUtilities {


    public static void giveImpostorSword(Player player){

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
