package amonglegendminecraft.amonglegendminecraft.listeners;

import amonglegendminecraft.amonglegendminecraft.commands.MeetingCommand;
import amonglegendminecraft.amonglegendminecraft.commands.StartCommand;
import amonglegendminecraft.amonglegendminecraft.handlers.CrewmateTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.ImpostorTeam;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.BLUE;

public class CommonListeners implements Listener {

    private ImpostorTeam impostors;
    private CrewmateTeam crewmates;
    private MeetingCommand meetingCommand;
    private boolean hasStarted;

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public MeetingCommand getMeetingCommand() {
        return meetingCommand;
    }

    public void setMeetingCommand(MeetingCommand meetingCommand) {
        this.meetingCommand = meetingCommand;
    }

    public ImpostorTeam getImpostors() {
        return impostors;
    }

    public void setImpostors(ImpostorTeam impostors) {
        this.impostors = impostors;
    }

    public CrewmateTeam getCrewmates() {
        return crewmates;
    }

    public void setCrewmates(CrewmateTeam crewmates) {
        this.crewmates = crewmates;
    }

    public CommonListeners(){

    }



    @EventHandler
    public void ArmorStandPlayerEvent(PlayerArmorStandManipulateEvent event){

            final Player player = event.getPlayer();
            ArmorStand armorStand = event.getRightClicked();

            for (int i = 0; i < meetingCommand.getArmorStands().size(); i++){
                if (armorStand == meetingCommand.getArmorStands().get(i)){
                    meetingCommand.setPlayerWhoReport(player);
                    ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                    for (int k = 0; k < playersArray.size(); k++){
                        if (armorStand.getEquipment().getHelmet().getItemMeta().getDisplayName().compareToIgnoreCase(playersArray.get(k).getName()) == 0){
                            meetingCommand.setPlayerWhoIsReported(playersArray.get(k));
                            meetingCommand.setPlayerWhoReport(player);
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"meeting");

                        }
                    }
                }
            }

    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){


            final Player p = event.getPlayer();

            Location deathLocation = p.getLocation();
            ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM,1,(short) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwner(p.getName());
            meta.setDisplayName(p.getName());
            skull.setItemMeta(meta);

            ArmorStand armorStand = p.getPlayer().getWorld().spawn(deathLocation, ArmorStand.class);
            armorStand.getEquipment().setHelmet(skull);
            armorStand.setInvulnerable(true);
            meetingCommand.getArmorStands().add(armorStand);




            if (impostors.isFromTeam(p)){           //Si le joueur est impostor
                impostors.removePlayer(p);          //On retire le joueur de la team impostor
            }else if (crewmates.isFromTeam(p)){     //Sinon
                crewmates.removePlayer(p);          //On retire le joueur de la team crewmate
            }

            if (impostors.getPlayerArrayList().isEmpty()){                                          //S'il n'y a plus d'impostor (les crewmates ont win)
                ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());  //On créer un arraylist de tout les joueurs connecté
                for(Player player : playersArray){                                                  //On parcours tout les joueurs en ligne
                    //On affiche à tout les joueurs que les crewmates ont win
                    player.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + crewmates.getPlayerArrayList().size()+ "crewmates");
                }

                //On vide l'arraylist des crewmates
                crewmates.emptyTeam();
                impostors.emptyTeam();
                setHasStarted(false);



            }else if (crewmates.getPlayerArrayList().isEmpty()){                                    //S'il n'y a plus de crewmates (les impostors ont win)
                ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());  //On créer un arraylist de tout les joueurs connecté
                for(Player player : playersArray){                                                  //On parcours tout les joueurs en ligne
                    //On affiche à tout les joueurs que les impostors ont win
                    player.sendTitle(RED + "Les impostors ont gagné !", "Il reste " + impostors.getPlayerArrayList().size()+ "impostors");
                }
                //On vide l'arraylist des impostors
                impostors.emptyTeam();
                crewmates.emptyTeam();
                setHasStarted(false);
            }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) throws Exception {
        if (hasStarted){
            final Player player = event.getEntity().getKiller();        //On récupère le joueur qui a tué l'entité
            ChatUtilities.broadcast("Une entity est mort");
            if (event.getEntityType().getName().compareToIgnoreCase("Zombie") == 0){      //Si l'entity est un zombie
                questEntity(player,"Zombie");                                       //On lance la méthode "questEntity"
            }else if(event.getEntityType().getName().compareToIgnoreCase("Enderman") == 0){     //Si c'est un enderman
                questEntity(player,"Enderman");                                           //On lance la méthode "questEntity"
            }
        }

    }


    public void questEntity(Player player, String nameEntity) throws Exception {
        if (hasStarted){
            if (player.getScoreboard().getObjective("Quest").getScore(nameEntity).getScore() > 1){      //Si le score du joueur est supérieur à 1
                player.getScoreboard().getObjective("Quest").getScore(nameEntity).setScore(player.getScoreboard().getObjective("Quest").getScore(nameEntity).getScore()-1); //On lui décrémente son nombre d'entité à tuer
            }else{ //Sinon, le joueur à réussit la quête
                if (crewmates.isFromTeam(player)){
                    if (!crewmates.getQuestList(player).getQuest(nameEntity).isDone()){
                        crewmates.getQuestList(player).getQuest(nameEntity).setDone(true);
                        player.sendMessage("Vous avez terminé la quête "+nameEntity+" !");
                        player.getScoreboard().getObjective("Quest").getScore(nameEntity).resetScore();
                    }
                }else{
                    if (!impostors.getQuestList(player).getQuest(nameEntity).isDone()){
                        impostors.getQuestList(player).getQuest(nameEntity).setDone(true);
                        player.sendMessage("Vous avez terminé la quête "+nameEntity+" !");
                        player.getScoreboard().getObjective("Quest").getScore(nameEntity).resetScore();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerEarnExp(PlayerExpChangeEvent event){
        if (hasStarted){
            final Player player = event.getPlayer();

            if (crewmates.isFromTeam(player)){
                    ChatUtilities.broadcast("toutes les quêtes finito? " + crewmates.allQuestDone());
                if (crewmates.allQuestDone()){
                    ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                    for(Player p : playersArray){
                        p.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + crewmates.getPlayerArrayList().size()+ " crewmates");
                    }
                    crewmates.emptyTeam();
                    impostors.emptyTeam();

                }
            }
        }
    }


}
