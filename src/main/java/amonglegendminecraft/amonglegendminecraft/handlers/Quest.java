package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;

public class Quest {

    private String questName;
    private String questDescription;
    private int compteur;
    private boolean done;


    public Quest(String questName, String questDescription, boolean done, int compteur) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.done = done;
        this.compteur = compteur;
    }


    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }


    public void setDone(boolean done) {
        this.done = done;
    }



    public String getQuestName() {
        return questName;
    }


    public String getQuestDescription() {
        return questDescription;
    }

    public boolean isDone() {
        return done;
    }




    public Quest(){

    }

}
