package amonglegendminecraft.amonglegendminecraft.commands;

import amonglegendminecraft.amonglegendminecraft.handlers.PlayerTeam;
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

            final Player playerWhoVote = ((Player) sender).getPlayer();
            int y=-1;
            int k=-1;

            for (int i = 0; i < meetingData.getGameData().getPlayerTeamArrayList().size(); i++){
                if (meetingData.getGameData().getPlayerTeamArrayList().get(i).getPlayer().getName().compareToIgnoreCase(args[0]) == 0){
                    //Joueur qui s'est pris le vote
                    y = i;
                }
                if (meetingData.getGameData().getPlayerTeamArrayList().get(i).getPlayer() == playerWhoVote){
                    //Joueur qui vote
                    k = i;
                }
            }


            if (y != -1){
                if (!meetingData.getGameData().getPlayerTeamArrayList().get(k).isHasVoted()){ //Si le joueur qui effectue la commande n'a pas voté
                    meetingData.getGameData().getPlayerTeamArrayList().get(k).setHasVoted(true);
                    meetingData.getGameData().getPlayerTeamArrayList().get(y).incrementNbVotes();       //On incrémente le nb de votes du joueur voté
                    meetingData.getGameData().getPlayerTeamArrayList().get(y).getPlayersThatVoted().add(meetingData.getGameData().getPlayerTeamArrayList().get(k)); //On ajoute le joueur qui a voté dans la liste des joueurs qui ont voté contre le joueur voté
                    playerWhoVote.sendMessage("Votre vote à bien été pris en compte !");
                }else{
                    playerWhoVote.sendMessage("Vous avez déjà voté quelqu'un !");
                }
            }else{
                playerWhoVote.sendMessage("Le joueur n'existe pas !");
            }


            meetingData.addPlayerVotes(meetingData.getGameData().getPlayerTeamArrayList().get(y));



        }else{
            ((Player) sender).getPlayer().sendMessage("Vous ne pouvez pas voter en dehors d'un meeting !");
        }


        return true;
    }
}
