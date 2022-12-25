package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.Sabotage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

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
            int k = 0;
            final Player player = ((Player) sender).getPlayer();
            for (int i = 0; i < gameData.getImpostors().getPlayerArrayList().size(); i++){
                if (gameData.getImpostors().getPlayerArrayList().get(i).getPlayer() == player){
                    k = i;
                }
            }

            if (args[0].compareToIgnoreCase("Light") == 0) {
                Sabotage light = gameData.getImpostors().getSabotageElementFromName("Light");

                if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= light.getPrice()){
                    for (int i = 0; i < gameData.getCrewmates().getPlayerArrayList().size(); i++){
                        effectTransaction(new PotionEffect(PotionEffectType.BLINDNESS,10*20,10,false,false),
                                gameData.getPlayerTeamArrayList().get(k), light.getPrice());
                    }
                }
            }else if (args[0].compareToIgnoreCase("Mining") == 0) {
                Sabotage mining = gameData.getImpostors().getSabotageElementFromName("Mining");

                if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= mining.getPrice()){
                    for (int i = 0; i < gameData.getCrewmates().getPlayerArrayList().size(); i++){
                        effectTransaction(new PotionEffect(PotionEffectType.SLOW_DIGGING,10*20,10,false,false),
                                gameData.getPlayerTeamArrayList().get(k), mining.getPrice());
                    }
                }
            }else if (args[0].compareToIgnoreCase("Ralentissement") == 0) {
                Sabotage ralentissement = gameData.getImpostors().getSabotageElementFromName("Ralentissement");

                if (gameData.getImpostors().getPlayerArrayList().get(k).getWallet() >= ralentissement.getPrice()) {
                    for (int i = 0; i < gameData.getCrewmates().getPlayerArrayList().size(); i++) {
                        effectTransaction(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10 * 20, 10, false, false),
                                gameData.getPlayerTeamArrayList().get(k), ralentissement.getPrice());
                    }
                }
            }


        }
        return true;

    }

    private static void effectTransaction(PotionEffect effect, PlayerTeam playerTeam, int price){
        playerTeam.getPlayer().addPotionEffect(effect);
        playerTeam.setWallet(playerTeam.getWallet()-price);
    }
}








