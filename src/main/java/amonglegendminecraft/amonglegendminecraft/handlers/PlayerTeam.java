package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerTeam {

    private Player player;
    private Team team;
    private ArrayList<Quest> quests;
    private boolean canKill;
    private int cooldown;
    private int nbVotes;
    private boolean hasVoted;
    private int nbQuests;
    private int wallet;

    private ArrayList<PlayerTeam> playersThatVoted;

    public PlayerTeam(){

    }

    public PlayerTeam(Player player, int nbQuests){
        this.player = player;
        this.nbVotes = 0;
        this.hasVoted = false;
        this.nbQuests = nbQuests;
        quests = Quest.initializeQuests(nbQuests);
        playersThatVoted = new ArrayList<>();
        this.wallet = 0;
        this.canKill = true;
        this.cooldown = 30;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
        player.getPlayer().getScoreboard().getObjective("Quest").getScore("Portefeuille").setScore(wallet);
    }

    public ArrayList<PlayerTeam> getPlayersThatVoted() {
        return playersThatVoted;
    }

    public void setPlayersThatVoted(ArrayList<PlayerTeam> playersThatVoted) {
        this.playersThatVoted = playersThatVoted;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isQuestDone(String nameQuest) throws Exception {
        int i = 0;
        while (i < quests.size() && quests.get(i).getQuestName().compareToIgnoreCase(nameQuest) != 0){
            i++;
        }
        if (quests.get(i).getQuestName().compareToIgnoreCase(nameQuest) == 0){
            return quests.get(i).isDone();
        }else{
            throw new Exception("La quête n'existe pas");
        }

    }

    public Quest getQuestElement(String nameQuest) throws Exception {
        int i = 0;
        while (i < quests.size() && quests.get(i).getQuestName().compareToIgnoreCase(nameQuest) != 0){
            i++;
        }
        if (quests.get(i).getQuestName().compareToIgnoreCase(nameQuest) == 0){
            return quests.get(i);
        }else{
            throw new Exception("La quête n'existe pas");
        }

    }

    public boolean allQuestDone(){
        for (int i = 0; i < quests.size(); i++){
            if (!quests.get(i).isDone()){
                return false;
            }
        }
        return true;
    }

    public int nbQuestDone(){
        int compteur = 0;
        for (int i = 0; i < quests.size(); i++){
            if (quests.get(i).isDone()){
                compteur++;
            }
        }
        return compteur;
    }

    public boolean isCanKill() {
        return canKill;
    }

    public void setCanKill(boolean canKill) {
        this.canKill = canKill;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void incrementNbVotes(){
        this.nbVotes++;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(int nbVotes) {
        this.nbVotes = nbVotes;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public int getNbQuests() {
        return nbQuests;
    }

    public void setNbQuests(int nbQuests) {
        this.nbQuests = nbQuests;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }
}
