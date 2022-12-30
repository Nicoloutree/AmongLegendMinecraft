package amonglegendminecraft.amonglegendminecraft.utils;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SwordUtilities {

    private static final ItemStack sword = generateImpostorSword();
    private static final ItemStack compass = generateCompass();

    public static ItemStack getCompass() {
        return compass;
    }


    public static void giveCompass(Player player){
        Inventory inv = player.getInventory();
        player.sendMessage("Vous avez reçu votre compass");
        inv.addItem(compass);
    }

    public static void giveImpostorSword(Player player){
        Inventory inv = player.getInventory();
        player.sendMessage("Vous avez reçu votre épée d'imposteur");
        inv.addItem(sword);
    }

    private static ItemStack generateImpostorSword(){
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta metaSword = sword.getItemMeta();
        metaSword.setDisplayName("§4Épée Sus");
        metaSword.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier("generic.attackDamage", 10, AttributeModifier.Operation.ADD_NUMBER));
        sword.setItemMeta(metaSword);
        return sword;
    }

    private static ItemStack generateCompass(){
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta metaCompass = compass.getItemMeta();
        metaCompass.setDisplayName("Trackeur");
        metaCompass.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier("generic.luck",10,AttributeModifier.Operation.ADD_NUMBER));
        compass.setItemMeta(metaCompass);
        return compass;
    }

    public static void removeImpostorSword(Player player){
        Inventory inv = player.getInventory();
        player.sendMessage("Vous n'avez plus votre épée d'imposteur !");
        inv.remove(sword);
    }

}
