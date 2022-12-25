package amonglegendminecraft.amonglegendminecraft.listeners;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import amonglegendminecraft.amonglegendminecraft.commands.MeetingCommand;
import amonglegendminecraft.amonglegendminecraft.commands.StartCommand;
import amonglegendminecraft.amonglegendminecraft.handlers.CrewmateTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.ImpostorTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.SwordUtilities;
import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
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
import org.bukkit.scheduler.BukkitScheduler;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.BLUE;

public class CommonListeners implements Listener {

    private ImpostorTeam impostors;
    private CrewmateTeam crewmates;
    private boolean hasSwordOnce = false;


    private ArrayList<PlayerTeam> playerTeamArrayList;
    private MeetingCommand meetingCommand;
    private boolean hasStarted;

    public boolean isHasStarted() {
        return hasStarted;
    }
    public ArrayList<PlayerTeam> getPlayerTeamArrayList() {
        return playerTeamArrayList;
    }

    public void setPlayerTeamArrayList(ArrayList<PlayerTeam> playerTeamArrayList) {
        this.playerTeamArrayList = playerTeamArrayList;
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

            int y=-1;
            int k=-1;




        //--------------------------Récupération de la tête du joueur----------------------------//
            Location deathLocation = p.getLocation();
            ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM,1,(short) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwner(p.getName());
            meta.setDisplayName(p.getName());
            skull.setItemMeta(meta);

        //--------------------------Création du armorStand quand le joueur meurt----------------------------//

        ArmorStand armorStand = p.getPlayer().getWorld().spawn(deathLocation, ArmorStand.class);
            armorStand.getEquipment().setHelmet(skull);
            armorStand.setInvulnerable(true);
            meetingCommand.getArmorStands().add(armorStand);

        //--------------------------Récupération de la tête du joueur----------------------------//

        int h = 0;
            //k = imposteur
            //y = crewmates
        for (int i = 0; i < playerTeamArrayList.size(); i++){
            if (playerTeamArrayList.get(i).getPlayer() == p){
                h = i;
            }
        }

        if (playerTeamArrayList.get(h).getTeam().getTeamName().compareToIgnoreCase("Crewmates") == 0){
            crewmates.removePlayer(playerTeamArrayList.get(h));
        }else{
            impostors.removePlayer(playerTeamArrayList.get(h));
        }

        int z = 0;
        for (int i = 0; i < impostors.getPlayerArrayList().size(); i++){
            if (p.getKiller() == impostors.getPlayerArrayList().get(i).getPlayer()){
                z = i;
            }
        }
        if(p.getKiller() == impostors.getPlayerArrayList().get(z).getPlayer()){
            impostors.getPlayerArrayList().get(z).setCanKill(false);
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            int finalZ = z;
            SwordUtilities.removeImpostorSword(impostors.getPlayerArrayList().get(finalZ).getPlayer());
            scheduler.scheduleSyncRepeatingTask(AmongLegendMinecraft.plugin, new Runnable() {
            int cd = impostors.getPlayerArrayList().get(finalZ).getCooldown();

                @Override
                public void run() {

                    if (cd == 0){
                        impostors.getPlayerArrayList().get(finalZ).setCanKill(true);
                        SwordUtilities.giveImpostorSword(impostors.getPlayerArrayList().get(finalZ).getPlayer());
                        scheduler.cancelTasks(AmongLegendMinecraft.plugin);
                    }

                    cd--;
                }
            }, 0L,20L); //20 tick = 1 seconde
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
        ChatUtilities.broadcast("etat hastarted : " + hasStarted);
        if (hasStarted){
            final Player player = event.getEntity().getKiller();        //On récupère le joueur qui a tué l'entité
            for (int i = 0; i < playerTeamArrayList.size(); i++){
                if (playerTeamArrayList.get(i).getPlayer() == player){
                    if (event.getEntityType().getName().compareToIgnoreCase("Zombie") == 0){      //Si l'entity est un zombie
                        if (!playerTeamArrayList.get(i).isQuestDone("Zombie")) {
                            ChatUtilities.broadcast("Le joueur" + playerTeamArrayList.get(i).getPlayer().getName());
                            questEntity(playerTeamArrayList.get(i), "Zombie");                                       //On lance la méthode "questEntity"
                        }else{
                                playerTeamArrayList.get(i).getPlayer().sendMessage("T'as déjà fait la quête");
                        }
                    }else if(event.getEntityType().getName().compareToIgnoreCase("Enderman") == 0){     //Si c'est un enderman
                        if (!playerTeamArrayList.get(i).isQuestDone("Enderman")){
                            questEntity(playerTeamArrayList.get(i),"Enderman");                  //On lance la méthode "questEntity"
                        }else{
                            playerTeamArrayList.get(i).getPlayer().sendMessage("T'as déjà fait la quête");
                        }
                    }

                }

            }

        }

    }




    public void questEntity(PlayerTeam player, String nameEntity) throws Exception {

        ChatUtilities.broadcast("Le joueur" + player.getPlayer().getName());
        if (hasStarted){
            ChatUtilities.broadcast("On vérifie si le score > 1");
            if (player.getPlayer().getScoreboard().getObjective("Quest").getScore(nameEntity).getScore() > 1){ //Si le score du joueur est supérieur à 1
                //On lui décrémente son nombre d'entité à tuer
                ChatUtilities.broadcast("On décrémente le score");
                player.getPlayer().getScoreboard().getObjective("Quest").getScore(nameEntity).setScore(player.getPlayer().getScoreboard().getObjective("Quest").getScore(nameEntity).getScore()-1); //On lui décrémente son nombre d'entité à tuer
            }else{
                if(!player.getQuestElement(nameEntity).isDone()){
                    player.getQuestElement(nameEntity).setDone(true);
                    player.setWallet(player.getWallet()+2);
                    player.getPlayer().sendMessage("Vous avez terminé la quête "+nameEntity+" !");
                    player.getPlayer().getScoreboard().getObjective("Quest").getScore(nameEntity).resetScore();
                    if (player.getTeam().getTeamName().compareToIgnoreCase("Impostors") == 0 && player.allQuestDone()){
                        player.setWallet(player.getWallet() + 10);
                    }
                    if (!hasSwordOnce && player.nbQuestDone() == 2){
                        SwordUtilities.giveImpostorSword(player.getPlayer());
                        hasSwordOnce = true;
                    }
                }
            }

        }
    }

    @EventHandler
    public void onPlayerEarnExp(PlayerExpChangeEvent event){
        if (hasStarted){
            final Player player = event.getPlayer();

            for (int i = 0; i < playerTeamArrayList.size(); i++) {
                if (playerTeamArrayList.get(i).getPlayer() == player) {
                    if (playerTeamArrayList.get(i).getTeam().getTeamName().compareToIgnoreCase("Crewmates") == 0) {
                        if (crewmates.allPlayerQuestDone()) {
                            ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                            for (Player p : playersArray) {
                                p.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + crewmates.getPlayerArrayList().size() + " crewmates");
                            }
                            crewmates.emptyTeam();
                            impostors.emptyTeam();
                        }
                    }
                }
            }

        }
    }


}
