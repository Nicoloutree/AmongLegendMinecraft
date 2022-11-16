package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class Crewmate {
    private ArrayList<Player> playerArrayList;
    private int nbquete;
    public Crewmate(ArrayList<Player> playerArrayList,int nbquete){
        this.playerArrayList=playerArrayList;
        this.nbquete=nbquete;
    }
    public ArrayList<Player> getPlayerArrayList(){
        return this.playerArrayList;
    }
    public void setPlayerArrayList(ArrayList<Player> playerArrayList){
        this.playerArrayList=playerArrayList;
    }
    public void displayQuete(ArrayList<Quete> queteArrayList){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board=manager.getNewScoreboard();
        final Objective objective=board.registerNewObjective("general","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GREEN+"QUETES");
        Score[] tabScore=new Score[queteArrayList.size()];
        for(int i=0;i<queteArrayList.size();i++){
            tabScore[i]= objective.getScore(ChatColor.BLUE+queteArrayList.get(i).getNameQuete());
        }
        for(Player p:this.playerArrayList){
            p.setScoreboard(board);
        }
    }
    public boolean isQueteValidate(Quete quete){
        return quete.isDone();
    }
}
