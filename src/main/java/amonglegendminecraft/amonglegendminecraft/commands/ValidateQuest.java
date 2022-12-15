package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.Quest;
import amonglegendminecraft.amonglegendminecraft.handlers.QuestList;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (command.getName().equalsIgnoreCase("quest")) {

            Player player = ((Player) sender).getPlayer();



            if (args[0].compareToIgnoreCase("bois") == 0) {
                try {
                    questMaterial(player,Material.OAK_LOG,"Bois");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (args[0].compareToIgnoreCase("Cobblestone") == 0) {
                try {
                    questMaterial(player,Material.COBBLESTONE,"Cobblestone");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (args[0].compareToIgnoreCase("zombie") == 0){
                try {
                    questMobKills(player, "Zombie");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (args[0].compareToIgnoreCase("enderman") == 0){
                try {
                    questMobKills(player, "enderman");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            if (gameData.getCrewmates().allQuestDone()){

                ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                for(Player p : playersArray){
                    p.sendTitle(BLUE + "Les crewmates ont gagné !", "Il reste " + gameData.getCrewmates().getPlayerArrayList().size()+ " crewmates");
                }
                gameData.getCrewmates().emptyTeam();
                gameData.getImpostors().emptyTeam();
                gameData.setGameStarted(false);
            }

        }


        return true;
    }


    public boolean materialQuestIsValidate(Player player, Material material, int amount) {
        return player.getInventory().contains(material, amount);
    }

    public void questMaterial(Player player, Material material, String nameQuest) throws Exception { //Méthode permettant de check une quête de type Material
        int res;
        if (gameData.getCrewmates().isFromTeam(player)){                                            //Le joueur est crewmate?
            if (!gameData.getCrewmates().getQuestList(player).isQuestDone(nameQuest)){              //Le joueur a déjà fait la quête?
                if (materialQuestIsValidate(player,material,gameData.getCrewmates().getQuestList(player).getQuest(nameQuest).getCompteur())){   //Est-ce que le joueur a remplit les critères de la quête
                    gameData.getCrewmates().getQuestList(player).questValidate(nameQuest);                    //On valide la quête (setTrue le boolean de la quête)
                    player.getScoreboard().getObjective("Quest").getScore(nameQuest).resetScore();      //On retire la quête du scoreboard
                    player.giveExp(1);                                                                  //On donne 1exp
                    player.sendMessage("T'as réussit la quête bg des ténèbres");                                //On envoie un message pour lui dire qu'il a finit la quête
                }else{              //On affiche la description de la quête
                    player.sendMessage("Quête : " + nameQuest);
                    player.sendMessage("Description : " + gameData.getCrewmates().getQuestList(player).getQuest(nameQuest).getQuestDescription());
                    if (player.getInventory().contains(material)){
                        res = gameData.getCrewmates().getQuestList(player).getQuest(nameQuest).getCompteur() - player.getInventory().getItem(player.getInventory().first(material)).getAmount();
                        player.getScoreboard().getObjective("Quest").getScore(nameQuest).setScore(res);
                        player.sendMessage("Il vous manque "+ res + " "+ material.name());
                    }else{
                        player.sendMessage("Il vous manque " + gameData.getCrewmates().getQuestList(player).getQuest(nameQuest).getCompteur() + " " + material.name());
                    }
                }
            }else{
                player.sendMessage("T'as déjà fait la quête bouffon/enculé");
            }
        }else{ //Même truc que les crewmates mais pour les imposteurs
            if (!gameData.getImpostors().getQuestList(player).isQuestDone(nameQuest)){
                if (materialQuestIsValidate(player,material,gameData.getImpostors().getQuestList(player).getQuest(nameQuest).getCompteur())){
                    gameData.getImpostors().getQuestList(player).questValidate(nameQuest);
                    player.getScoreboard().getObjective("Quest").getScore(nameQuest).resetScore();
                    player.giveExp(1);
                    player.sendMessage("T'as réussit la quête bg des ténèbres");
                }else{
                    player.sendMessage("Quête : " + nameQuest);
                    player.sendMessage("Description : " + gameData.getImpostors().getQuestList(player).getQuest(nameQuest).getQuestDescription());
                    if (player.getInventory().contains(material)){
                        res = gameData.getImpostors().getQuestList(player).getQuest(nameQuest).getCompteur() - player.getInventory().getItem(player.getInventory().first(material)).getAmount();
                        player.getScoreboard().getObjective("Quest").getScore(nameQuest).setScore(res);
                        player.sendMessage("Il vous manque "+ res + " "+ material.name());
                    }else{
                        player.sendMessage("Il vous manque " + gameData.getImpostors().getQuestList(player).getQuest(nameQuest).getCompteur() + " " + material.name());
                    }
                }
            }else{
                player.sendMessage("T'as déjà fait la quête bouffon/enculé");
            }
        }

    }

    public void questMobKills(Player player, String mobName) throws Exception { //Méthode qui check une quête de type entity
        if (gameData.getCrewmates().isFromTeam(player)){            //Le joueur est crewmate?
            if (!gameData.getCrewmates().getQuestList(player).isQuestDone(mobName)){ //La quête a déjà été effectué?
                player.sendMessage("Quête : " + mobName);               //On affiche le nom de la quête
                player.sendMessage("Description : " + gameData.getCrewmates().getQuestList(player).getQuest(mobName).getQuestDescription()); //On affiche la description de la quête
            }else{                      //Le joueur a déjà fait la quête
                player.sendMessage("T'as déjà fait la quête bouffon/enculé");
            }
        }else{ //Même truc que les crewmates mais pour les imposteurs
            if (!gameData.getImpostors().getQuestList(player).isQuestDone(mobName)){
                player.sendMessage("Quête : " + mobName);
                player.sendMessage("Description : " + gameData.getImpostors().getQuestList(player).getQuest(mobName).getQuestDescription());
            }else{
                player.sendMessage("T'as déjà fait la quête bouffon/enculé");
            }
        }
    }



}







