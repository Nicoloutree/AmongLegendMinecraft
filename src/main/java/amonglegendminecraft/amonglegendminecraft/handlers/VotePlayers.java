package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;

public class VotePlayers {


    private Player playerWhoVote;
    private Player playerVoted;
    private boolean haveVoted;

    public VotePlayers(Player playerWhoVote, Player playerVoted){
        this.playerWhoVote = playerWhoVote;
        this.playerVoted = playerVoted;

        haveVoted = false;
    }

    public VotePlayers(Player playerWhoVote){
        this.playerWhoVote = playerWhoVote;
        haveVoted = false;
    }

    public boolean haveVoted() {
        return haveVoted;
    }

    public void setHaveVoted(boolean haveVoted) {
        this.haveVoted = haveVoted;
    }

    public Player getPlayerVoted() {
        return playerVoted;
    }

    public Player getPlayerWhoVote() {
        return playerWhoVote;
    }

    public void setPlayerVoted(Player playerVoted) {
        this.playerVoted = playerVoted;
    }

    public void setPlayerWhoVote(Player playerWhoVote) {
        this.playerWhoVote = playerWhoVote;
    }

}
