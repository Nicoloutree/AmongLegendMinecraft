package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ValidateQuest implements CommandExecutor {

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

        if (command.getName().equalsIgnoreCase("quest")) {
            Player player = ((Player) sender).getPlayer();
            ChatUtilities chatUtilities = new ChatUtilities();


            if (args[0].compareToIgnoreCase("bois") == 0) {
                try {
                    questBois(player);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return true;
    }


    public boolean materialQuestIsValidate(Player player, Material material, int amount) {
        return player.getInventory().contains(material, amount);
    }

    public void questBois(Player player) throws Exception {

        int res = 0;

        if (gameData.getCrewmates().isFromTeam(player)){
            if (!gameData.getCrewmates().questDone("bois")) {               //Le joueur n'a pas effectué la quête
                if (materialQuestIsValidate(player, Material.OAK_LOG, 10)) {                          //Est-ce que le player a 10 oak log
                    gameData.getCrewmates().getQuestList(player).questValidate("bois");
                    player.getScoreboard().getObjective("Quest").getScore("Bois").resetScore();
                    player.sendMessage("T'as réussit la quête bg des ténèbres");
                }
                else{ //Il n'a pas 10 bois
                    player.sendMessage("Quête : bois");
                    player.sendMessage("Description : " + gameData.getCrewmates().getQuestList(player).getQuest("bois").getQuestDescription());
                    if (player.getInventory().contains(Material.OAK_LOG)){
                        res = gameData.getCrewmates().getQuestList(player).getQuest("bois").getCompteur() - player.getInventory().getItem(player.getInventory().first(Material.OAK_LOG)).getAmount();
                        player.sendMessage("Il vous manque "+ res + "bois");
                    }else{
                        player.sendMessage("Il vous manque " + gameData.getCrewmates().getQuestList(player).getQuest("bois").getCompteur() + "bois");
                    }
                }

            }else{
                player.sendMessage("T'as déjà fait la quête bouffon/enculé");
            }
        }else{
            if (!gameData.getImpostors().questDone("bois")) {               //Le joueur n'a pas effectué la quête
                if (materialQuestIsValidate(player, Material.OAK_LOG, 10)) {                          //Est-ce que le player a 10 oak log
                    gameData.getImpostors().getQuestList(player).questValidate("bois");
                    player.getScoreboard().getObjective("Quest").getScore("Bois").resetScore();
                    player.sendMessage("T'as réussit la quête bg des ténèbres");
                }
                else{ //Il n'a pas 10 bois
                    player.sendMessage("Quête : bois");
                    player.sendMessage("Description : " + gameData.getImpostors().getQuestList(player).getQuest("bois").getQuestDescription());
                    if (player.getInventory().contains(Material.OAK_LOG)){
                        res = gameData.getImpostors().getQuestList(player).getQuest("bois").getCompteur() - player.getInventory().getItem(player.getInventory().first(Material.OAK_LOG)).getAmount();
                        player.sendMessage("Il vous manque "+ res + "bois");
                    }else{
                        player.sendMessage("Il vous manque " + gameData.getImpostors().getQuestList(player).getQuest("bois").getCompteur() + "bois");
                    }
                }

            }else{
                player.sendMessage("T'as déjà fait la quête bouffon/enculé");
            }
        }

    }

}







