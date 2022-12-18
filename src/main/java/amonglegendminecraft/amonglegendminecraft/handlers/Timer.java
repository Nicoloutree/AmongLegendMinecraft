package amonglegendminecraft.amonglegendminecraft.handlers;

import amonglegendminecraft.amonglegendminecraft.AmongLegendMinecraft;
import amonglegendminecraft.amonglegendminecraft.utils.ChatUtilities;
import amonglegendminecraft.amonglegendminecraft.utils.LocationUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import java.util.ArrayList;

public class Timer {


    public void timerIncrement(Score time){
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(AmongLegendMinecraft.plugin, new Runnable() {
            @Override
            public void run() {
                time.setScore(time.getScore() + 1);

            }
        }, 0L, 20L);
    }

    public void displayTimer(ArrayList<Player> playerArrayList,Scoreboard scoreboard){
        for (Player p : playerArrayList){
            p.setScoreboard(scoreboard);
        }
    }

    public void timerParser(int seconds, Objective objective){
        int S = seconds % 60;
        int H = seconds / 60;
        int M = H % 60;
        H = H / 60;
        String heures = String.valueOf(H);
        String minutes = String.valueOf(M);
        String second = String.valueOf(S);

        objective.getScore(heures + ":" + minutes + ":" + second);
    }




}