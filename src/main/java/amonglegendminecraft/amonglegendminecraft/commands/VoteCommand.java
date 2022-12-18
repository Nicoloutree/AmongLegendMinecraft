package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.PlayerVoted;
import amonglegendminecraft.amonglegendminecraft.handlers.VotePlayers;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VoteCommand implements CommandExecutor {

    private MeetingCommand meetingData;

    public void setMeetingData(MeetingCommand meetingData) {
        this.meetingData = meetingData;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (command.getName().equalsIgnoreCase("vote") && meetingData.isMeetingActivate() && meetingData.getGameData().isGameStarted()) {

            ArrayList<VotePlayers> votes = new ArrayList<>(meetingData.getVotePlayers());
            ArrayList<PlayerVoted> playerVoteds = new ArrayList<>(meetingData.getPlayerVoteds());
            ArrayList<Player> playersArray = new ArrayList<Player>(Bukkit.getOnlinePlayers());
            final Player playerWhoVote = ((Player) sender).getPlayer();

            for (Player player : playersArray) {                                                                        //On parcours tout les joueurs en ligne
                if (player.getName().compareToIgnoreCase(args[0]) == 0) {                                               //On vérifie si le joueur correspond à celui qui est voté
                    for (VotePlayers vote : votes) {                                                                    //On parcours la liste des votes des joueurs
                        if (vote.getPlayerWhoVote() == playerWhoVote) {      //Si le joueur qui vote est le même que celui qui a fait la commande /vote
                            if (!vote.haveVoted()){                                                                     //Si le joueur n'a pas déjà voté
                                playerWhoVote.sendMessage("Votre vote a bien été pris en compte.");
                                for (PlayerVoted playerVoted : playerVoteds){
                                    if (playerVoted.getPlayerVoted().getName().compareToIgnoreCase(player.getName()) == 0){
                                        vote.setPlayerVoted(player);
                                        vote.setHaveVoted(true);
                                        playerVoted.incrementNbVotes();
                                    }
                                }


                            }else{
                                player.sendMessage("Vous avez déjà voté quelqu'un !");
                            }
                        }
                    }
                }
            }



            meetingData.setVotePlayers(votes);
            meetingData.setPlayerVoteds(playerVoteds);



        }else{
            ((Player) sender).getPlayer().sendMessage("Vous ne pouvez pas voter en dehors d'un meeting !");
        }


        return true;
    }
}
