package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collections;

public class createScoreboard {

    private static Scoreboard scoreboard;
    private static Objective objective;

    public createScoreboard(){

    }

    private static void createBoard(){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("Quest","dummy");
        objective.setDisplayName("Quest");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public static void addObjective(ArrayList<Quest> quests){
        //Ce que j'appelle "score" c'est le truc qui est display à droite de l'écran
        //Genre y'aura écrit par exemple "Bois" ou "Cobblestone" c'est le nom de la quête

        Score scorename;

        for (int i = 0; i < quests.size(); i++){
            scorename = objective.getScore(quests.get(i).getQuestName());   //On ajoute la quête au score
            scorename.setScore(quests.get(i).getCompteur());                                  //On set le score au compteur (étape obligatoire sinon elle s'affiche pas, merci bukkit)
        }
    }

    public static void createBoardForPlayers(ArrayList<PlayerTeam> player){


        for (int i = 0; i < player.size(); i++) {//Parcours de tout les joueurs

            createBoard();                                                  //On créer le board (1 seul par joueur)
            ChatUtilities.broadcast("Nom du joueur auquel on attribue les quêtes : " + player.get(i).getPlayer().getName());

            addObjective(player.get(i).getQuests());

            player.get(i).getPlayer().setScoreboard(scoreboard);  //On attribue le bon board au joueur

        }
    }

}
