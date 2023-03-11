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
        ArrayList<Quest> tempCollect = new ArrayList<>();
        ArrayList<Quest> tempEntity = new ArrayList<>();
        ArrayList<Quest> tempAdvancement = new ArrayList<>();
        ArrayList<Quest> temp = new ArrayList<>();

        //Collect type :
        tempCollect.add(new Quest("Golden Apple","Obtenir 4 Pomme dorées",false,4,"collect",Material.GOLDEN_APPLE));
        tempCollect.add(new Quest("Cake","Crafter un Gateau",false,1,"collect",Material.CAKE));
        tempCollect.add(new Quest("Egg","Obtenir 15 Oeufs",false,15,"collect",Material.EGG));
        tempCollect.add(new Quest("Blaze Rod","Obtenir 10 Batons de blaze",false,10,"collect",Material.BLAZE_ROD));
        tempCollect.add(new Quest("Pink Dye","Obtenir 20 Colorants Rose",false,10,"collect",Material.PINK_DYE));
        tempCollect.add(new Quest("Eye of Ender","Obtenir 4 Oeil de l'end",false,4,"collect",Material.ENDER_EYE));
        tempCollect.add(new Quest("Fermented Spider Eye","Obtenir 4 Oeil d'araignée fermenté",false,4,"collect",Material.FERMENTED_SPIDER_EYE));
        tempCollect.add(new Quest("Ghast Tear","Obtenir 2 larmes de ghast",false,2,"collect",Material.GHAST_TEAR));
        tempCollect.add(new Quest("Gold Ingot","Obtenir un stack d'or",false,64,"collect",Material.GOLD_INGOT));
        tempCollect.add(new Quest("Ink Sac","Obtenir 32 Encres de Poulpes",false,32,"collect",Material.INK_SAC));
        tempCollect.add(new Quest("Nether Wart","Obtenir un stack de verrues du nether",false,64,"collect",Material.NETHER_WART));
        tempCollect.add(new Quest("FireWork Rocket","Obtenir un stack de feu d'artifices",false,64,"collect",Material.FIREWORK_ROCKET));
        tempCollect.add(new Quest("Leather","Obtenir 32 Cuir",false,32,"collect",Material.LEATHER));
        tempCollect.add(new Quest("Heart of the Sea","Trouver un coeur de la mer dans un coffre au trésor",false,1,"collect",Material.HEART_OF_THE_SEA));
        tempCollect.add(new Quest("Quartz","Récolter un stack de Quartz",false,64,"collect",Material.QUARTZ));
        tempCollect.add(new Quest("Diamond","Recolter 15 diamants",false,15,"collect",Material.DIAMOND));
        tempCollect.add(new Quest("Book","Obtenir 20 Livres",false,15,"collect",Material.BOOK));
        tempCollect.add(new Quest("HAY BLOCK","Obtenir 9 Blocs de foins",false,9,"collect",Material.HAY_BLOCK));
        tempCollect.add(new Quest("TNT","Obtenir 10 TNT",false,10,"collect",Material.TNT));
        tempCollect.add(new Quest("Obsidian","Obtenir 10 Obsidinnes",false,10,"collect",Material.OBSIDIAN));
        tempCollect.add(new Quest("Bookshelves","Obtenir 10 Bibliothèques",false,10,"collect",Material.BOOKSHELF));

/*
        //MULTICOLLECTES
        temp.add(new Quest("Bois","Obtenir 5 types de bois",false,5,"MultiCollect"));
        temp.add(new Quest("Laines","Obtenir 6 types de laines",false,6,"MultiCollect"));
        temp.add(new Quest("Fleurs","Obtenir 6 fleurs différentes",false,6,"MultiCollect"));
*/


        //Entity type :
        tempEntity.add(new Quest("Zombie", "Tuer 15 zombies", false, 15, "entity"));
        tempEntity.add(new Quest("Enderman", "Tuer 4 enderman", false, 4, "entity"));
        tempEntity.add(new Quest("Creeper", "Tuer 6 creeper", false, 6, "entity"));
        tempEntity.add(new Quest("Zombified Piglin", "Tuer 10 Zombified Piglin", false, 10, "entity"));
        tempEntity.add(new Quest("Villager", "Tuer 7 villager", false, 7, "entity"));
        tempEntity.add(new Quest("Bee", "Tuer 2 bee", false, 2, "entity"));
        tempEntity.add(new Quest("Hoglin", "Tuer 5 hoglin", false, 5, "entity"));
        tempEntity.add(new Quest("Magma Cube", "Tuer 15 lavaSlime", false, 15, "entity"));

        //Advancements type :
        tempAdvancement.add(new Quest("Sticky Situation", "Arreter sa chute avec un bloc de miel",false,1,"advancements","adventure/honey_block_slide"));
        tempAdvancement.add(new Quest("Zombie Doctor","Soigner un zombie villageois à l'aide d'une potion de weakness et d'une pomme dorée",false,1,"advancements","story/cure_zombie_villager"));
        tempAdvancement.add(new Quest("Cover me with diamonds","Avoir n'importe quel morceau d'armure en diamant equipé",false,1,"advancements","story/shiny_gear"));
        tempAdvancement.add(new Quest("Return to Sender","Tuer un ghast en lui renvoyant sa boule de feu",false,1,"advancements","nether/return_to_sender"));
        tempAdvancement.add(new Quest("A Terrible Fortress","Trouver une forteresse dans le nether",false,1,"advancements","nether/find_fortress"));
        tempAdvancement.add(new Quest("Not Quite Nine Lives","Charger une respawn anchor au maximum",false,1,"advancements","nether/charge_respawn_anchor"));
        tempAdvancement.add(new Quest("Is it a Balloon ?","Regarder un ghast à travers une longue-vue",false,1,"advancements","adventure/spyglass_at_ghast"));
        tempAdvancement.add(new Quest("Caves & cliffs","Tomber d'au moins y=319 jusqu'à au moins y=-59 pour une distance vertical d'au moins 379 blocs",false,1,"advancements", "adventure/fall_from_world_height"));
        tempAdvancement.add(new Quest("Hired Help","Invoquer un Iron golem",false,1,"advancements","adventure/summon_iron_golem"));
        tempAdvancement.add(new Quest("Sniper Duel","Tuer un squelette de plus de 50 mètres de distance",false,1,"advancements", "adventure/sniper_duel"));
        tempAdvancement.add(new Quest("Bullseye","Toucher le centre d'un Target Block d'au moins 30 mètres de distance",false,1,"advancements","adventure/bullseye"));
        tempAdvancement.add(new Quest("Tactical Fishing","Attraper un poisson avec un sceau",false,1,"advancements","husbandry/tactical_fishing"));

        Collections.shuffle(tempCollect);
        Collections.shuffle(tempAdvancement);
        Collections.shuffle(tempEntity);
        if(nbQuest == 3){
            temp.add(tempCollect.get(0));
            temp.add(tempEntity.get(0));
            temp.add(tempAdvancement.get(0));
        }else if(nbQuest > 3){
            int i = 0;
            while(temp.size() < nbQuest){
                temp.add(tempCollect.get(i));
                temp.add(tempEntity.get(i));
                temp.add(tempAdvancement.get(i));
                i++;
            }
        }


        return temp;
    }

    public static Quest pickFinalQuest(){
        ArrayList<Quest> temp = new ArrayList<>();
        temp.add(new Quest("EnderDragon","C'est votre dernière quête, tuer l'EnderDragon pour gagner !",false,1,"entity"));
        temp.add(new Quest("Raid","C'est votre dernière quête, défendez un raid pour gagner !",false,1,"advancements","adventure/hero_of_the_village"));
        Collections.shuffle(temp);
        return temp.get(0);
    }


    public Quest(){

    }

}
