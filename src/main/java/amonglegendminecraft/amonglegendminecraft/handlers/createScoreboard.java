package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collections;

public class createScoreboard {

    private Scoreboard scoreboard;
    private Objective objective;

    public createScoreboard(){

    }

    public void createBoard(){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("Quest","dummy");
        objective.setDisplayName("Quête");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void addObjective(int nbQuestToAdd, Quest quest){
        //Ce que j'appelle "score" c'est le truc qui est display à droite de l'écran
        //Genre y'aura écrit par exemple "Bois" ou "Cobblestone" c'est le nom de la quête

        Score scorename;
        for (int i = 0; i < nbQuestToAdd; i++){
            scorename = objective.getScore(quest.getQuestName());   //On ajoute la quête au score
            scorename.setScore(0);                                  //On set le score à 0 (étape obligatoire sinon elle s'affiche pas, merci bukkit)
        }
    }

    public void createBoardForPlayers(ArrayList<Player> players, QuestList questList, int nbQuest){

        for (Player player : players) {                                     //Parcours de tout les joueurs
            Collections.shuffle(questList.getQuestsForAllPlayers());        //On shuffle la liste de quête à chaque fois
            createBoard();                                                  //On créer le board (1 seul par joueur)
            for (int k = 0; k < nbQuest; k++) {                             //Parcours du nombre de quest à attribuer par joueur
                addObjective(nbQuest, questList.getQuestsForAllPlayers().get(k)); //On ajoute chaque quête pour chaque joueur
            }
            player.setScoreboard(scoreboard);                               //On attribue le bon board au joueur
        }
    }

}
