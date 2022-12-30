package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.Sabotage;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.SwordUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static org.bukkit.ChatColor.RED;

public class SabotageCommand implements CommandExecutor {

    private StartCommand gameData;
    private MeetingCommand meetingCommand;

    public MeetingCommand getMeetingCommand() {
        return meetingCommand;
    }

    public void setMeetingCommand(MeetingCommand meetingCommand) {
        this.meetingCommand = meetingCommand;
    }

    public StartCommand getGameData() {
        return gameData;
    }

    public void setGameData(StartCommand gameData) {
        this.gameData = gameData;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        if (command.getName().equalsIgnoreCase("sabotage")) {

            final Player player = ((Player) sender).getPlayer();
            if (args.length > 0){
                int k = 0;
                for (int i = 0; i < gameData.getImpostors().getPlayerArrayList().size(); i++){
                    if (gameData.getImpostors().getPlayerArrayList().get(i).getPlayer() == player){
                        k = i;
                    }
                }


                if (args[0].compareToIgnoreCase("Light") == 0) {

                    Sabotage light = gameData.getImpostors().getSabotageElementFromName("Light");
                    if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= light.getPrice()){

                        effectTransaction(new PotionEffect(PotionEffectType.BLINDNESS,10*20,10,false,false),
                                gameData.getCrewmates().getPlayerArrayList(),gameData.getImpostors().getPlayerArrayList().get(k), light.getPrice());

                    }else{
                        int res = gameData.getImpostors().getPlayerArrayList().get(k).getWallet()- light.getPrice();
                        Sabotage.afficheSabotage(light,gameData.getImpostors().getPlayerArrayList().get(k));
                        gameData.getImpostors().getPlayerArrayList().get(k).getPlayer().sendMessage("T'as pas assez d'argent, il te manque "+res);
                    }
                }else if (args[0].compareToIgnoreCase("Mining") == 0) {
                    Sabotage mining = gameData.getImpostors().getSabotageElementFromName("Mining");
                    if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= mining.getPrice()){
                        effectTransaction(new PotionEffect(PotionEffectType.SLOW_DIGGING,10*20,10,false,false),
                                gameData.getCrewmates().getPlayerArrayList(),gameData.getImpostors().getPlayerArrayList().get(k), mining.getPrice());

                    }else{
                        int res = gameData.getImpostors().getPlayerArrayList().get(k).getWallet()- mining.getPrice();
                        Sabotage.afficheSabotage(mining,gameData.getImpostors().getPlayerArrayList().get(k));
                        gameData.getImpostors().getPlayerArrayList().get(k).getPlayer().sendMessage("T'as pas assez d'argent, il te manque "+res);
                    }
                }else if (args[0].compareToIgnoreCase("Ralentissement") == 0) {
                    Sabotage ralentissement = gameData.getImpostors().getSabotageElementFromName("Ralentissement");

                    if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= ralentissement.getPrice()) {
                        effectTransaction(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10 * 20, 10, false, false),
                                gameData.getCrewmates().getPlayerArrayList(),gameData.getImpostors().getPlayerArrayList().get(k), ralentissement.getPrice());
                    }else{
                        int res = gameData.getImpostors().getPlayerArrayList().get(k).getWallet()- ralentissement.getPrice();
                        Sabotage.afficheSabotage(ralentissement,gameData.getImpostors().getPlayerArrayList().get(k));
                        gameData.getImpostors().getPlayerArrayList().get(k).getPlayer().sendMessage("T'as pas assez d'argent, il te manque "+res);
                    }
                }else if (args[0].compareToIgnoreCase("Octogone") == 0){
                    Sabotage octogone = gameData.getImpostors().getSabotageElementFromName("Octogone");

                    if(gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= octogone.getPrice()){
                        if (!meetingCommand.isMeetingActivate()){
                            gameData.getImpostors().getPlayerArrayList().get(k).setWallet(gameData.getImpostors().getPlayerArrayList().get(k).getWallet() - octogone.getPrice());
                            ArrayList<PlayerTeam> temp = gameData.getCrewmates().getPlayerArrayList();
                            Collections.shuffle(temp);
                            Location location =  new Location(temp.get(0).getPlayer().getWorld(), 50, 150,50);
                            ArrayList<Location> stockLoc = new ArrayList<>();
                            LocationUtilities.createPlatform(location,20,20,Material.COBBLESTONE,Material.COBBLESTONE);
                            stockLoc.add(gameData.getImpostors().getPlayerArrayList().get(k).getPlayer().getLocation());
                            stockLoc.add(temp.get(0).getPlayer().getLocation());
                            LocationUtilities.teleportDuoToOctogone(gameData.getImpostors().getPlayerArrayList().get(k).getPlayer(), temp.get(0).getPlayer(), location,20);
                            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                            gameData.getImpostors().getPlayerArrayList().get(k).getPlayer().sendTitle(RED +"Octogone", "Vous avez 15 secondes");
                            temp.get(0).getPlayer().sendTitle(RED + "Octogone", "Survivez pendant 15 secondes");
                            int finalK = k;
                            scheduler.scheduleSyncRepeatingTask(AmongLegendMinecraft.plugin, new Runnable() {
                            int duree = 15;

                                @Override
                                public void run() {

                                    if (duree == 0){
                                        LocationUtilities.removePlatform(location,20,20);
                                        if (!meetingCommand.isMeetingActivate()){
                                            gameData.getImpostors().getPlayerArrayList().get(finalK).getPlayer().teleport(stockLoc.get(0));
                                            temp.get(0).getPlayer().teleport(stockLoc.get(1));
                                        }
                                        gameData.getImpostors().getPlayerArrayList().get(finalK).getPlayer().sendTitle(RED +"Temps écoulé", "L'octogone est terminé !");
                                        temp.get(0).getPlayer().sendTitle(RED +"Temps écoulé", "L'octogone est terminé !");
                                        temp.clear();
                                        stockLoc.clear();
                                        scheduler.cancelTasks(AmongLegendMinecraft.plugin);
                                    }

                                    duree--;
                                }
                            }, 0L,20L); //20 tick = 1 seconde

                        }else{
                            gameData.getImpostors().getPlayerArrayList().get(k).getPlayer().sendMessage("Vous ne pouvez pas faire d'octogone pendant un meeting !");
                        }
                    }else{
                        int res = gameData.getImpostors().getPlayerArrayList().get(k).getWallet()- octogone.getPrice();
                        Sabotage.afficheSabotage(octogone,gameData.getImpostors().getPlayerArrayList().get(k));
                        gameData.getImpostors().getPlayerArrayList().get(k).getPlayer().sendMessage("T'as pas assez d'argent, il te manque "+res);
                    }

                }
            }else{
                player.sendMessage("Liste des sabotages : (commande /sabotage nameSabotage)");
                for (int i = 0; i < gameData.getImpostors().getSabotageArrayList().size(); i++){
                    player.sendMessage("    -" + gameData.getImpostors().getSabotageArrayList().get(i).getName()+", prix : " + gameData.getImpostors().getSabotageArrayList().get(i).getPrice());
                }
            }


        }
        return true;

    }

    private static void effectTransaction(PotionEffect effect, ArrayList<PlayerTeam> playerTeamArrayList, PlayerTeam playerTeam, int price){

        for (int i = 0; i < playerTeamArrayList.size(); i++) {
            playerTeamArrayList.get(i).getPlayer().addPotionEffect(effect);
        }
        playerTeam.setWallet(playerTeam.getWallet()-price);
    }
}








