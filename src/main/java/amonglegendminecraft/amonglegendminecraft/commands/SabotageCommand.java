package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.Sabotage;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class SabotageCommand implements CommandExecutor {

    private StartCommand gameData;

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
                        Sabotage.afficheSabotage(light,gameData.getImpostors().getPlayerArrayList().get(k));
                    }
                }else if (args[0].compareToIgnoreCase("Mining") == 0) {
                    Sabotage mining = gameData.getImpostors().getSabotageElementFromName("Mining");
                    if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= mining.getPrice()){
                        effectTransaction(new PotionEffect(PotionEffectType.SLOW_DIGGING,10*20,10,false,false),
                                gameData.getCrewmates().getPlayerArrayList(),gameData.getImpostors().getPlayerArrayList().get(k), mining.getPrice());

                    }else{
                        Sabotage.afficheSabotage(mining,gameData.getImpostors().getPlayerArrayList().get(k));
                    }
                }else if (args[0].compareToIgnoreCase("Ralentissement") == 0) {
                    Sabotage ralentissement = gameData.getImpostors().getSabotageElementFromName("Ralentissement");

                    if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= ralentissement.getPrice()) {
                        effectTransaction(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10 * 20, 10, false, false),
                                gameData.getCrewmates().getPlayerArrayList(),gameData.getImpostors().getPlayerArrayList().get(k), ralentissement.getPrice());
                    }else{
                        Sabotage.afficheSabotage(ralentissement,gameData.getImpostors().getPlayerArrayList().get(k));
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








