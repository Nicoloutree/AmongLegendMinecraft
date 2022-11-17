package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class createScoreboard {

    private Scoreboard scoreboard;
    private ScoreboardManager scoreboardManager;
    private Objective objective;
    private Score scorename;
    private Score scorename2;
    private Player player;
    private Quest quest;


    public createScoreboard(Scoreboard scoreboard, ScoreboardManager scoreboardManager, Objective objective, Score questName, Score questName2, Player player, Quest quest) {
        this.scoreboard = scoreboard;
        this.scoreboardManager = scoreboardManager;
        this.objective = objective;
        this.scorename = questName;
        this.scorename2 = questName2;
        this.player = player;
        this.quest = quest;
    }
    public createScoreboard(){

    }

    public void createBoardForPlayer(Player player, String y){

        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        objective = scoreboard.registerNewObjective(y,"dummy");
        objective.setDisplayName("Quête");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        this.player = player;
    }

    public void addObjective(Player player, Quest quest){


        scorename = objective.getScore(quest.getQuestName());
        scorename.setScore(0);

        this.player = player;
    }

    public void addObjective2(Player player, Quest quest){


        scorename2 = objective.getScore(quest.getQuestName());
        scorename2.setScore(0);

        this.player = player;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public void setScoreboardManager(ScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Score getQuestName() {
        return scorename;
    }

    public void setQuestName(Score questName) {
        this.scorename = questName;
    }
}
