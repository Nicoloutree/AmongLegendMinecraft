package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;

public class PlayerTeam {

    private Player player;
    private Team team;
    private ArrayList<Quest> quests;


    private boolean hasImpoSword;
    private boolean hasExtraCash;
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
        this.hasExtraCash = false;
        this.hasImpoSword = false;
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
        Score scoreWallet;
        player.getPlayer().getScoreboard().getObjective("Quest").getScore("Portefeuille       | " + this.wallet+" |").resetScore();
        this.wallet = wallet;
        scoreWallet = player.getPlayer().getScoreboard().getObjective("Quest").getScore("Portefeuille       | " + wallet + " |");
        scoreWallet.setScore(107);

    }

    public ArrayList<PlayerTeam> getPlayersThatVoted() {
        return playersThatVoted;
    }

    public void setPlayersThatVoted(ArrayList<PlayerTeam> playersThatVoted) {
        this.playersThatVoted = playersThatVoted;
    }
    public boolean isHasImpoSword() {
        return hasImpoSword;
    }

    public void setHasImpoSword(boolean hasImpoSword) {
        this.hasImpoSword = hasImpoSword;
    }

    public boolean isHasExtraCash() {
        return hasExtraCash;
    }

    public void setHasExtraCash(boolean hasExtraCash) {
        this.hasExtraCash = hasExtraCash;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isQuestDone(String nameQuest) {
        int i = 0;
        while (i < quests.size() && quests.get(i).getQuestName().compareToIgnoreCase(nameQuest) != 0){
            i++;
        }
        if (quests.get(i).getQuestName().compareToIgnoreCase(nameQuest) == 0){
            return quests.get(i).isDone();
        }else{
            return false;
        }

    }

    public Quest getQuestElementFromKey(String key) {
        int j = 0;
        for(int i = 0; i < quests.size(); i++){
            if (quests.get(i).getQuestType().compareToIgnoreCase("advancements") == 0){
                if(quests.get(i).getKey().compareToIgnoreCase(key) == 0){
                    j = i;
                }
            }
        }

        if (quests.get(j).getKey() != null && quests.get(j).getKey().compareToIgnoreCase(key) == 0){
            return quests.get(j);
        }else{
            return null;
        }
    }

    public Quest getQuestElement(String nameQuest) {
        int j = 0;

        for (int i = 0; i < quests.size(); i++){
            if (quests.get(i).getQuestName().compareToIgnoreCase(nameQuest) == 0){
                j = i;
            }
        }
        if (quests.get(j).getQuestName().compareToIgnoreCase(nameQuest) == 0){
            return quests.get(j);
        }else{
            return null;
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
    public void addQuest(Quest quest){
        this.quests.add(quest);
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }
}
