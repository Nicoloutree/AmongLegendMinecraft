package amonglegendminecraft.amonglegendminecraft.listeners;

import amonglegendminecraft.amonglegendminecraft.commands.StartCommand;
import amonglegendminecraft.amonglegendminecraft.handlers.CrewmateTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.ImpostorTeam;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.BLUE;

public class CommonListeners implements Listener {

    private final ChatUtilities chatUtilities = new ChatUtilities();
    private ImpostorTeam impostors;
    private CrewmateTeam crewmates;


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
    public void onPlayerDeath(PlayerDeathEvent event){

        final Player p = event.getPlayer();

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


        }else if (crewmates.getPlayerArrayList().isEmpty()){                                    //S'il n'y a plus de crewmates (les impostors ont win)
            ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());  //On créer un arraylist de tout les joueurs connecté
            for(Player player : playersArray){                                                  //On parcours tout les joueurs en ligne
                //On affiche à tout les joueurs que les impostors ont win
                player.sendTitle(RED + "Les impostors ont gagné !", "Il reste " + impostors.getPlayerArrayList().size()+ "impostors");
            }
            //On vide l'arraylist des impostors
            impostors.emptyTeam();
            crewmates.emptyTeam();
        }

    }
/*
    @Deprecated
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event){
        chatUtilities.broadcast("OnItemPickup");
        final Player p = event.getPlayer();


        if (event.getItem().getItemStack().getType() == Material.OAK_LOG){
            for (int i = 0; i < event.getItem().getItemStack().getAmount(); i++){

                chatUtilities.broadcast("Le joueur " + p.getName() +"a ramassé du bois de boulot");
            }
        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){




    }
*/















}
