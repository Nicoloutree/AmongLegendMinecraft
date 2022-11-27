package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
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
            scorename.setScore(quest.getCompteur());                                  //On set le score à 0 (étape obligatoire sinon elle s'affiche pas, merci bukkit)
        }
    }

    public void createBoardForPlayers(ArrayList<QuestList> questList, int nbQuest){

        ChatUtilities chatUtilities = new ChatUtilities();

        for (int i = 0; i < questList.size(); i++) {//Parcours de tout les joueurs
            chatUtilities.broadcast(questList.get(i).getPlayer().getName());
            chatUtilities.broadcast(String.valueOf(questList.size()));
            Collections.shuffle(questList.get(i).getQuestsForPlayer());        //On shuffle la liste de quête à chaque fois
            createBoard();                                                  //On créer le board (1 seul par joueur)
            for (int k = 0; k < nbQuest; k++) {                             //Parcours du nombre de quest à attribuer par joueur
                addObjective(nbQuest, questList.get(i).getQuestsForPlayer().get(k)); //On ajoute chaque quête pour chaque joueur
            }
            questList.get(i).getPlayer().setScoreboard(scoreboard);  //On attribue le bon board au joueur

        }
    }

}
