package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.Quest;
import amonglegendminecraft.amonglegendminecraft.listeners.CommonListeners;
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
    private static boolean senderValidate = false;
    private static PlayerTeam playerValidate;

    public static boolean isSenderValidate() {
        return senderValidate;
    }

    public static void setSenderValidate(boolean senderValidate) {
        ValidateQuest.senderValidate = senderValidate;
    }

    public static PlayerTeam getPlayerValidate() {
        return playerValidate;
    }

    public static void setPlayerValidate(PlayerTeam playerValidate) {
        ValidateQuest.playerValidate = playerValidate;
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
            senderValidate = true;
        }

        if (command.getName().equalsIgnoreCase("quest")) {

            if (!senderValidate){

                Player player = ((Player) sender).getPlayer();
                if (args.length > 0){

                    int k = 0;

                    ChatUtilities.broadcast("size de getPlayerTeamArrayList : " + gameData.getPlayerTeamArrayList().size());

                    for (int i = 0; i < gameData.getPlayerTeamArrayList().size(); i++){ //joueur qui a fait la commande
                        if (gameData.getPlayerTeamArrayList().get(i).getPlayer() == player){
                            k = i;
                        }
                    }

                    ChatUtilities.broadcast("Joueur qui fait la commande /quest");
                    ChatUtilities.broadcast("Team du joueur : " + gameData.getPlayerTeamArrayList().get(k).getTeam().getTeamName());
                    ChatUtilities.broadcast("Joueur : " + gameData.getPlayerTeamArrayList().get(k).getPlayer().getName());
                    ChatUtilities.broadcast("nbQuest : " + gameData.getPlayerTeamArrayList().get(k).getNbQuests());

                    for (int i = 0; i < gameData.getPlayerTeamArrayList().get(k).getQuests().size(); i++){
                        if (args[0].compareToIgnoreCase(gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestName()) == 0){
                            if (gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestType().compareToIgnoreCase("collect") == 0){
                                try {
                                    questMaterial(gameData.getPlayerTeamArrayList().get(k),gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getMaterial(),gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else if(gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestType().compareToIgnoreCase("entity") == 0){
                                try {
                                    questMobKills(gameData.getPlayerTeamArrayList().get(k),gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }


                    if (gameData.getPlayerTeamArrayList().get(k).getTeam().getTeamName().compareToIgnoreCase("Impostors") == 0){
                        if (gameData.getPlayerTeamArrayList().get(k).nbQuestDone() == 2){
                            SwordUtilities.giveImpostorSword(gameData.getPlayerTeamArrayList().get(k).getPlayer());
                            SwordUtilities.giveCompass(gameData.getPlayerTeamArrayList().get(k).getPlayer());
                        }else if (gameData.getPlayerTeamArrayList().get(k).allQuestDone()) {
                            gameData.getPlayerTeamArrayList().get(k).setWallet(gameData.getPlayerTeamArrayList().get(k).getWallet() + 10);
                        }

                    }

                    /*
                    if (gameData.getCrewmates().allPlayerQuestDone()){

                        ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                        for(Player p : playersArray){
                            p.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + gameData.getCrewmates().getPlayerArrayList().size()+ " crewmates");
                        }
                        gameData.getCrewmates().emptyTeam();
                        gameData.getImpostors().emptyTeam();
                        gameData.setGameStarted(false);
                    }*/

                }else{
                    int y = 0;
                    for (int i = 0; i < gameData.getPlayerTeamArrayList().size(); i++){
                        if (gameData.getPlayerTeamArrayList().get(i).getPlayer() == player){
                            y = i;
                        }
                    }

                    player.sendMessage("Liste des quests : (commande /quest nameQuest)");
                    for (int i = 0; i < gameData.getPlayerTeamArrayList().get(y).getQuests().size(); i++){
                        player.sendMessage("   - " + gameData.getPlayerTeamArrayList().get(y).getQuests().get(i).getQuestName());
                    }
                }

            }else{

                try {
                    questMobKills(playerValidate, args[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ChatUtilities.broadcast("Joueur qu'on check : " + playerValidate.getPlayer().getName());
                ChatUtilities.broadcast("Team : " + playerValidate.getTeam().getTeamName());
                ChatUtilities.broadcast("nbQuest done : " + playerValidate.nbQuestDone());

                if (playerValidate.getTeam().getTeamName().compareToIgnoreCase("Impostors") == 0){
                    ChatUtilities.broadcast("1");
                    if (playerValidate.getNbQuests() == 2){
                        ChatUtilities.broadcast("2");
                        SwordUtilities.giveImpostorSword(playerValidate.getPlayer());
                        SwordUtilities.giveCompass(playerValidate.getPlayer());
                    }else if (playerValidate.allQuestDone()) {
                        ChatUtilities.broadcast("3");
                        playerValidate.setWallet(playerValidate.getWallet() + 10);
                    }

                }
                playerValidate = null;
                senderValidate = false;
            }

        }


        return true;
    }

  public void questMobKills(PlayerTeam player, String mobName) throws Exception {
      if (!player.isQuestDone(mobName)){
          if (senderValidate){
              player.getQuestElement(mobName).setDone(true);
              player.setWallet(player.getWallet()+2);
              player.getPlayer().sendMessage("Vous avez terminé la quête "+mobName+" !");
              player.getPlayer().getScoreboard().getObjective("Quest").getScore(mobName).resetScore();
          }else{
              player.getPlayer().sendMessage("Quête : " + mobName);
              player.getPlayer().sendMessage("Description : " + player.getQuestElement(mobName).getQuestDescription());
          }
      }else{
          player.getPlayer().sendMessage("T'as déjà fait la quête bouffon !");
      }
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









}







