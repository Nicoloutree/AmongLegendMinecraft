package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.Quest;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.SwordUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static org.bukkit.ChatColor.BLUE;

public class ValidateQuest implements CommandExecutor {

    private StartCommand gameData;

    public StartCommand getGameData() {
        return gameData;
    }

    public void setGameData(StartCommand gameData) {
        this.gameData = gameData;
    }
    private boolean hasSwordOnce = false;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (command.getName().equalsIgnoreCase("quest")) {

            Player player = ((Player) sender).getPlayer();
            int k = 0;

            ChatUtilities.broadcast("size de getPlayerTeamArrayList : " + gameData.getPlayerTeamArrayList().size());

            for (int i = 0; i < gameData.getPlayerTeamArrayList().size(); i++){
                if (gameData.getPlayerTeamArrayList().get(i).getPlayer() == player){
                    k = i;
                }
            }

            ChatUtilities.broadcast("Joueur qui fait la commande /quest");
            ChatUtilities.broadcast("Joueur : " + gameData.getPlayerTeamArrayList().get(k).getPlayer().getName());
            ChatUtilities.broadcast("nbQuest : " + gameData.getPlayerTeamArrayList().get(k).getNbQuests());


            if (args[0].compareToIgnoreCase("bois") == 0) {
                try {
                    questMaterial(gameData.getPlayerTeamArrayList().get(k),Material.OAK_LOG,"Bois");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (args[0].compareToIgnoreCase("Cobblestone") == 0) {
                try {
                    questMaterial(gameData.getPlayerTeamArrayList().get(k),Material.COBBLESTONE,"Cobblestone");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (args[0].compareToIgnoreCase("zombie") == 0){
                try {
                    questMobKills(gameData.getPlayerTeamArrayList().get(k), "Zombie");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (args[0].compareToIgnoreCase("enderman") == 0){
                try {
                    questMobKills(gameData.getPlayerTeamArrayList().get(k), "enderman");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!hasSwordOnce && gameData.getImpostors().getPlayerArrayList().get(k).nbQuestDone() == 2){
                SwordUtilities.giveImpostorSword(gameData.getImpostors().getPlayerArrayList().get(k).getPlayer());
                hasSwordOnce = true;
            }


            if (gameData.getCrewmates().allPlayerQuestDone()){

                ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                for(Player p : playersArray){
                    p.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + gameData.getCrewmates().getPlayerArrayList().size()+ " crewmates");
                }
                gameData.getCrewmates().emptyTeam();
                gameData.getImpostors().emptyTeam();
                gameData.setGameStarted(false);
            }

            if (gameData.getPlayerTeamArrayList().get(k).getTeam().getTeamName().compareToIgnoreCase("Impostors") == 0 && gameData.getPlayerTeamArrayList().get(k).allQuestDone()){
                gameData.getPlayerTeamArrayList().get(k).setWallet(gameData.getPlayerTeamArrayList().get(k).getWallet()+10);
            }

        }


        return true;
    }


    public boolean materialQuestIsValidate(Player player, Material material, int amount) {
        return player.getInventory().contains(material, amount);
    }

    public void questMaterial(PlayerTeam player, Material material, String nameQuest) throws Exception {
        int res;

        ChatUtilities.broadcast("Nom du joueur qu'on check :" + player.getPlayer().getName());
        ChatUtilities.broadcast("Etat de la quête :" + player.isQuestDone(nameQuest));


        if (!player.isQuestDone(nameQuest)){
            if (materialQuestIsValidate(player.getPlayer(), material, player.getQuestElement(nameQuest).getCompteur())){
                player.getQuestElement(nameQuest).setDone(true);
                player.getPlayer().getScoreboard().getObjective("Quest").getScore(nameQuest).resetScore();
                player.getPlayer().giveExp(1);
                player.setWallet(player.getWallet()+2);
                player.getPlayer().sendMessage("T'as réussi la quête bg des ténèbres !");
            }else{
                player.getPlayer().sendMessage("Quête : " + nameQuest);
                player.getPlayer().sendMessage("Description : " + player.getQuestElement(nameQuest).getQuestDescription());
                if (player.getPlayer().getInventory().contains(material)){
                    res = player.getQuestElement(nameQuest).getCompteur() - player.getPlayer().getInventory().getItem(player.getPlayer().getInventory().first(material)).getAmount();
                    player.getPlayer().getScoreboard().getObjective("Quest").getScore(nameQuest).setScore(res);
                    player.getPlayer().sendMessage("Il vous manque "+ res + " "+ material.name());
                }else{
                    player.getPlayer().sendMessage("Il vous manque " + player.getQuestElement(nameQuest).getCompteur() + " " + material.name());
                }
            }
        }else{
            player.getPlayer().sendMessage("T'as déjà fait la quête bouffon/enculé");
        }

    }




  public void questMobKills(PlayerTeam player, String mobName) throws Exception {
      if (!player.isQuestDone(mobName)){
          player.getPlayer().sendMessage("Quête : " + mobName);
          player.getPlayer().sendMessage("Description : " + player.getQuestElement(mobName).getQuestDescription());
      }else{
          player.getPlayer().sendMessage("T'as déjà fait la quête bouffon !");
      }
  }





}







