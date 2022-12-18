package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;

public class PlayerVoted {

    private Player playerVoted;
    private int nbVotes;

    public PlayerVoted(Player playerVoted){
        this.playerVoted = playerVoted;
        nbVotes = 0;
    }

    public Player getPlayerVoted() {
        return playerVoted;
    }

    public void setPlayerVoted(Player playerVoted) {
        this.playerVoted = playerVoted;
    }

    public int getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(int nbVotes) {
        this.nbVotes = nbVotes;
    }

    public void incrementNbVotes(){
        this.nbVotes++;
    }
}
