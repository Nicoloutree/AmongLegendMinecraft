package amonglegendminecraft.amonglegendminecraft.listeners;

import amonglegendminecraft.amonglegendminecraft.commands.StartCommand;
import amonglegendminecraft.amonglegendminecraft.handlers.CrewmateTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.ImpostorTeam;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

import static java.awt.Color.RED;
import static org.bukkit.ChatColor.BLUE;

public class CommonListeners implements Listener {

    private ImpostorTeam impostors;
    private CrewmateTeam crewmates;
    private static CommonListeners instance = new CommonListeners();

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

    public static void setInstance(CommonListeners instance) {
        CommonListeners.instance = instance;
    }
    public static CommonListeners getInstance() {
        return instance;
    }


    public CommonListeners(){

    }




    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        ChatUtilities chatUtilities = new ChatUtilities();



        chatUtilities.broadcast(impostors.getTeamName());
        chatUtilities.broadcast(String.valueOf(impostors.getPlayerArrayList().size()));
        final Player p = event.getPlayer();
        if (impostors.isFromTeam(p)){
            impostors.removePlayer(p);
            chatUtilities.broadcast("Le joueur était impostor");
        }else if (crewmates.isFromTeam(p)){
            crewmates.removePlayer(p);
            chatUtilities.broadcast("Le joueur était crewmate");

        }

        if (impostors.getPlayerArrayList().isEmpty()){
            for(Player player : impostors.getPlayerArrayList()){
                    player.sendTitle(RED + "Vous avez perdu", "Il reste " + crewmates.getPlayerArrayList().size() + "crewmates");
            }
            for(Player player : crewmates.getPlayerArrayList()){
                player.sendTitle(RED + "Vous avez gagné", "Il n'y a plus d'impostors");
            }
            crewmates.emptyTeam();

            chatUtilities.broadcast("oof");
        }else if (crewmates.getPlayerArrayList().isEmpty()){
            for(Player player : crewmates.getPlayerArrayList()){
                player.sendTitle(RED + "Vous avez perdu", "Il reste " + impostors.getPlayerArrayList().size() + "impostors");
            }
            for(Player player : impostors.getPlayerArrayList()){
                player.sendTitle(RED + "Vous avez gagné", "Il n'y a plus de crewmate");
            }
            impostors.emptyTeam();
            chatUtilities.broadcast("croow");
        }

    }


}
