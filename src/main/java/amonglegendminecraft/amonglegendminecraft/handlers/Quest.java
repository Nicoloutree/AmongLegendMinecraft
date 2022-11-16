package amonglegendminecraft.amonglegendminecraft.handlers;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Quest {

    private String questName;
    private String questDescription;
    private boolean done;

    private Player player;

    public Quest(String questName, String questDescription, boolean done) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.done = done;
    }


    public String getQuestName() {
        return questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }
}
