package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
import amonglegendminecraft.amonglegendminecraft.handlers.Quest;
import amonglegendminecraft.amonglegendminecraft.handlers.createScoreboard;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.SwordUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static org.bukkit.ChatColor.BLUE;

public class ValidateQuest implements CommandExecutor {

    private StartCommand gameData;
    private static boolean senderValidate = false;
    private static PlayerTeam playerValidate;
    private static boolean lastrun = false;
    private final Quest finalQuest = Quest.pickFinalQuest();

    public static boolean isLastrun() {
        return lastrun;
    }

    public static void setLastrun(boolean lastrun) {
        ValidateQuest.lastrun = lastrun;
    }

    public Quest getFinalQuest() {
        return finalQuest;
    }

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
                    String nameQuest = args[0];
                    if (args.length>1){
                        for (int i = 1; i < args.length; i++){
                            nameQuest = nameQuest + " " + args[i];
                        }
                    }


                    int k = 0;


                    for (int i = 0; i < gameData.getPlayerTeamArrayList().size(); i++){ //joueur qui a fait la commande
                        if (gameData.getPlayerTeamArrayList().get(i).getPlayer() == player){
                            k = i;
                        }
                    }



                    for (int i = 0; i < gameData.getPlayerTeamArrayList().get(k).getQuests().size(); i++){
                        if (nameQuest.compareToIgnoreCase(gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestName()) == 0){
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
                            else if (gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestType().compareToIgnoreCase("advancements") == 0){

                                try {
                                    questAdvancements(gameData.getPlayerTeamArrayList().get(k), gameData.getPlayerTeamArrayList().get(k).getQuests().get(i).getQuestName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }


                    if (gameData.getPlayerTeamArrayList().get(k).getTeam().getTeamName().compareToIgnoreCase("Impostors") == 0){
                        if (gameData.getPlayerTeamArrayList().get(k).nbQuestDone() == 2 && !gameData.getPlayerTeamArrayList().get(k).isHasImpoSword() ){
                            SwordUtilities.giveImpostorSword(gameData.getPlayerTeamArrayList().get(k).getPlayer());
                            SwordUtilities.giveCompass(gameData.getPlayerTeamArrayList().get(k).getPlayer());
                            gameData.getPlayerTeamArrayList().get(k).setHasImpoSword(true);
                        }else if (gameData.getPlayerTeamArrayList().get(k).allQuestDone() && !gameData.getPlayerTeamArrayList().get(k).isHasExtraCash()) {
                            gameData.getPlayerTeamArrayList().get(k).setWallet(gameData.getPlayerTeamArrayList().get(k).getWallet() + 10);
                            gameData.getPlayerTeamArrayList().get(k).setHasExtraCash(true);
                        }

                    }


                    if (gameData.getCrewmates().allPlayerQuestDone()){
                        for (int i = 0; i < gameData.getPlayerTeamArrayList().size(); i++){
                            gameData.getPlayerTeamArrayList().get(i).addQuest(finalQuest);
                            createScoreboard.addObjectiveForAPlayer(gameData.getPlayerTeamArrayList().get(i), finalQuest);
                        }
                        gameData.getCrewmates().sendMessage("Dernière quête !","Il vous reste une dernière quête pour gagner la partie !");
                        gameData.getImpostors().sendMessage("Dernière quête !","Empêchez les crewmates d'accomplir la dernière quête !");
                        this.lastrun = true;
                    }

                    if (lastrun){
                        for (int i = 0; i < gameData.getCrewmates().getPlayerArrayList().size(); i++){
                            if (gameData.getCrewmates().getPlayerArrayList().get(i).isQuestDone(finalQuest.getQuestName())){
                                ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());  //On créer un arraylist de tout les joueurs connecté
                                for(Player players : playersArray){                                                  //On parcours tout les joueurs en ligne
                                    //On affiche à tout les joueurs que les crewmates ont win
                                    players.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + gameData.getCrewmates().getPlayerArrayList().size()+ "crewmates");
                                }
                                //On vide l'arraylist des crewmates
                                gameData.getCrewmates().emptyTeam();
                                gameData.getImpostors().emptyTeam();
                                gameData.setGameStarted(false);
                            }
                        }
                    }


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
                String nameQuest = args[0];
                if (args.length>1){
                    for (int i = 1; i < args.length; i++){
                        nameQuest = nameQuest + " " + args[i];
                    }
                }



                if (playerValidate.getQuestElement(nameQuest) != null && playerValidate.getQuestElement(nameQuest).getQuestType().compareToIgnoreCase("entity") == 0){
                    questMobKills(playerValidate, nameQuest);
                }
                else if (playerValidate.getQuestElementFromKey(nameQuest) != null && playerValidate.getQuestElementFromKey(nameQuest).getKey().compareToIgnoreCase(nameQuest) == 0 && playerValidate.getQuestElementFromKey(nameQuest).getQuestType().compareToIgnoreCase("advancements") == 0){
                    questAdvancements(playerValidate,playerValidate.getQuestElementFromKey(nameQuest).getQuestName());
                }


                if (playerValidate.getTeam().getTeamName().compareToIgnoreCase("Impostors") == 0){

                    if (playerValidate.nbQuestDone() == 2 && !playerValidate.isHasImpoSword()){
                        SwordUtilities.giveImpostorSword(playerValidate.getPlayer());
                        SwordUtilities.giveCompass(playerValidate.getPlayer());
                        playerValidate.setHasImpoSword(true);
                    }else if (playerValidate.allQuestDone() && !playerValidate.isHasExtraCash()) {
                        playerValidate.setWallet(playerValidate.getWallet() + 10);
                        playerValidate.setHasExtraCash(true);
                    }

                }
                playerValidate = null;
                senderValidate = false;

                if (gameData.getCrewmates().allPlayerQuestDone()){

                    for (int i = 0; i < gameData.getPlayerTeamArrayList().size(); i++){
                        gameData.getPlayerTeamArrayList().get(i).addQuest(finalQuest);
                        createScoreboard.addObjectiveForAPlayer(gameData.getPlayerTeamArrayList().get(i), finalQuest);
                    }
                    gameData.getCrewmates().sendMessage("Dernière quête !","Il vous reste une dernière quête pour gagner la partie !");
                    gameData.getImpostors().sendMessage("Dernière quête !","Empêchez les crewmates d'accomplir la dernière quête !");
                    lastrun = true;
                }

                if (lastrun){
                    for (int i = 0; i < gameData.getCrewmates().getPlayerArrayList().size(); i++){
                        if (gameData.getCrewmates().getPlayerArrayList().get(i).isQuestDone(finalQuest.getQuestName())){
                            ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());  //On créer un arraylist de tout les joueurs connecté
                            for(Player players : playersArray){                                                  //On parcours tout les joueurs en ligne
                                //On affiche à tout les joueurs que les crewmates ont win
                                players.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + gameData.getCrewmates().getPlayerArrayList().size()+ "crewmates");
                            }
                            //On vide l'arraylist des crewmates
                            gameData.getCrewmates().emptyTeam();
                            gameData.getImpostors().emptyTeam();
                            gameData.setGameStarted(false);
                        }
                    }
                }
            }

        }


        return true;
    }

    public void questAdvancements(PlayerTeam player, String nameAdvancement) {
        if (!player.isQuestDone(nameAdvancement)){
            if (senderValidate){
                player.getQuestElement(nameAdvancement).setDone(true);
                player.setWallet(player.getWallet()+4);
                player.getPlayer().sendMessage("Vous avez terminé la quête "+nameAdvancement+" !");
                player.getPlayer().getScoreboard().getObjective("Quest").getScore(nameAdvancement).resetScore();
            }else{
                player.getPlayer().sendMessage("Quête : " + nameAdvancement);
                player.getPlayer().sendMessage("Description : " + player.getQuestElement(nameAdvancement).getQuestDescription());
            }
        }else{
            player.getPlayer().sendMessage("T'as déjà fait la quête bouffon !");
        }
    }


  public void questMobKills(PlayerTeam player, String mobName) {
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







