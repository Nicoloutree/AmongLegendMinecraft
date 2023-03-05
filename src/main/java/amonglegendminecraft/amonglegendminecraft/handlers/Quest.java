package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.Collections;

public class Quest {

    private String questName;
    private String questDescription;
    private Material material;
    private int compteur;
    private String questType;
    private boolean done;
    private String key;


    public Quest(String questName, String questDescription, boolean done, int compteur, String questType) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.done = done;
        this.compteur = compteur;
        this.questType = questType;
    }

    public Quest(String questName, String questDescription, boolean done, int compteur, String questType, Material material) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.done = done;
        this.compteur = compteur;
        this.questType = questType;
        this.material = material;
    }

    public Quest(String questName, String questDescription, boolean done, int compteur, String questType, String key) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.done = done;
        this.compteur = compteur;
        this.questType = questType;
        this.material = material;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }


    public void setDone(boolean done) {
        this.done = done;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestName() {
        return questName;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public boolean isDone() {
        return done;
    }

    public static ArrayList<Quest> initializeQuests(int nbQuest){
        ArrayList<Quest> temp = new ArrayList<>();

        //Collect type :
        temp.add(new Quest("Golden Apple","Obtenir 4 Pomme dorées",false,4,"collect",Material.GOLDEN_APPLE));
        temp.add(new Quest("Cake","Crafter un Gateau",false,1,"collect",Material.CAKE));
        temp.add(new Quest("Egg","Obtenir 15 Oeufs",false,15,"collect",Material.EGG));
        temp.add(new Quest("Blaze Rod","Obtenir 10 Batons de blaze",false,10,"collect",Material.BLAZE_ROD));
        temp.add(new Quest("Pink Dye","Obtenir 20 Colorants Rose",false,10,"collect",Material.PINK_DYE));
        temp.add(new Quest("Eye of Ender","Obtenir 4 Oeil de l'end",false,4,"collect",Material.ENDER_EYE));
        temp.add(new Quest("Fermented Spider Eye","Obtenir 4 Oeil d'araignée fermenté",false,4,"collect",Material.FERMENTED_SPIDER_EYE));
        temp.add(new Quest("Ghast Tear","Obtenir 2 larmes de ghast",false,2,"collect",Material.GHAST_TEAR));
        temp.add(new Quest("Gold Ingot","Obtenir un stack d'or",false,64,"collect",Material.GOLD_INGOT));
        temp.add(new Quest("Ink Sac","Obtenir 32 Encres de Poulpes",false,32,"collect",Material.INK_SAC));
        temp.add(new Quest("Nether Wart","Obtenir un stack de verrues du nether",false,64,"collect",Material.NETHER_WART));
        temp.add(new Quest("FireWork Rocket","Obtenir un stack de feu d'artifices",false,64,"collect",Material.FIREWORK_ROCKET));
        temp.add(new Quest("Leather","Obtenir 32 Cuir",false,32,"collect",Material.LEATHER));
        temp.add(new Quest("Heart of the Sea","Trouver un coeur de la mer dans un coffre au trésor",false,1,"collect",Material.HEART_OF_THE_SEA));
        temp.add(new Quest("Quartz","Récolter un stack de Quartz",false,64,"collect",Material.QUARTZ));
        temp.add(new Quest("Diamond","Recolter 15 diamants",false,15,"collect",Material.DIAMOND));
        temp.add(new Quest("Book","Obtenir 20 Livres",false,15,"collect",Material.BOOK));
        temp.add(new Quest("HAY BLOCK","Obtenir 9 Blocs de foins",false,9,"collect",Material.HAY_BLOCK));
        temp.add(new Quest("Pumpkin Pie","Obtenir 16 Pumpkin Pie",false,16,"collect",Material.PUMPKIN_PIE));
        temp.add(new Quest("HoneyComb","Obtenir 6 Rayon de Miel",false,6,"collect",Material.HONEYCOMB));
        temp.add(new Quest("Honey Bottle","Obtenir 6 Bouteilles de Miel",false,6,"collect",Material.HONEY_BOTTLE));
        temp.add(new Quest("TNT","Obtenir 10 TNT",false,10,"collect",Material.TNT));
        temp.add(new Quest("Obsidian","Obtenir 10 Obsidinnes",false,10,"collect",Material.OBSIDIAN));
        temp.add(new Quest("Bookshelves","Obtenir 10 Bibliothèques",false,10,"collect",Material.BOOKSHELF));

/*
        //MULTICOLLECTES
        temp.add(new Quest("Bois","Obtenir 5 types de bois",false,5,"MultiCollect"));
        temp.add(new Quest("Laines","Obtenir 6 types de laines",false,6,"MultiCollect"));
        temp.add(new Quest("Fleurs","Obtenir 6 fleurs différentes",false,6,"MultiCollect"));
*/


        //Entity type :
        temp.add(new Quest("Zombie", "Tuer 15 zombies", false, 15, "entity"));
        temp.add(new Quest("Enderman", "Tuer 4 enderman", false, 4, "entity"));
        temp.add(new Quest("Creeper", "Tuer 6 creeper", false, 6, "entity"));
        temp.add(new Quest("Zombified Piglin", "Tuer 10 Zombified Piglin", false, 10, "entity"));
        temp.add(new Quest("Villager", "Tuer 7 villager", false, 7, "entity"));
        temp.add(new Quest("VillagerGolem", "Tuer 1 villagerGolem", false, 1, "entity"));
        temp.add(new Quest("Bee", "Tuer 2 bee", false, 2, "entity"));
        temp.add(new Quest("Hoglin", "Tuer 5 hoglin", false, 5, "entity"));
        temp.add(new Quest("Magma Cube", "Tuer 15 lavaSlime", false, 15, "entity"));

        //Advancements type :
        temp.add(new Quest("Sticky Situation", "Arreter sa chute avec un bloc de miel",false,1,"advancements","adventure/honey_block_slide"));
        temp.add(new Quest("Zombie Doctor","Soigner un zombie villageois à l'aide d'une potion de weakness et d'une pomme dorée",false,1,"advancements","story/cure_zombie_villager"));
        temp.add(new Quest("Cover me with diamonds","Avoir n'importe quel morceau d'armure en diamant equipé",false,1,"advancements","story/shiny_gear"));
        temp.add(new Quest("Return to Sender","Tuer un ghast en lui renvoyant sa boule de feu",false,1,"advancements","nether/return_to_sender"));
        temp.add(new Quest("A Terrible Fortress","Trouver une forteresse dans le nether",false,1,"advancements","nether/find_fortress"));
        temp.add(new Quest("Not Quite Nine Lives","Charger une respawn anchor au maximum",false,1,"advancements","nether/charge_respawn_anchor"));
        temp.add(new Quest("Is it a Balloon ?","Regarder un ghast à travers une longue-vue",false,1,"advancements","adventure/spyglass_at_ghast"));
        temp.add(new Quest("Caves & cliffs","Tomber d'au moins y=319 jusqu'à au moins y=-59 pour une distance vertical d'au moins 379 blocs",false,1,"advancements", "adventure/fall_from_world_height"));
        temp.add(new Quest("Hired Help","Invoquer un Iron golem",false,1,"advancements","adventure/summon_iron_golem"));
        temp.add(new Quest("Sniper Duel","Tuer un squelette de plus de 50 mètres de distance",false,1,"advancements", "adventure/sniper_duel"));
        temp.add(new Quest("Bullseye","Toucher le centre d'un Target Block d'au moins 30 mètres de distance",false,1,"advancements","adventure/bullseye"));
        temp.add(new Quest("Tactical Fishing","Attraper un poisson avec un sceau",false,1,"advancements","husbandry/tactical_fishing"));

        Collections.shuffle(temp);
        while(temp.size() > nbQuest){
            temp.remove(0);
        }
        return temp;
    }



    public Quest(){

    }

}
