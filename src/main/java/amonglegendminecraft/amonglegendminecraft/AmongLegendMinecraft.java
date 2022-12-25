package amonglegendminecraft.amonglegendminecraft;

import amonglegendminecraft.amonglegendminecraft.commands.*;
import amonglegendminecraft.amonglegendminecraft.listeners.CommonListeners;
import amonglegendminecraft.amonglegendminecraft.listeners.InventoryCheck;
import amonglegendminecraft.amonglegendminecraft.listeners.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AmongLegendMinecraft extends JavaPlugin {

    public static AmongLegendMinecraft plugin;
    public StartCommand startCommand = new StartCommand();
    public ValidateQuest validateQuest = new ValidateQuest();
    public MeetingCommand meetingCommand = new MeetingCommand();
    public VoteCommand voteCommand = new VoteCommand();

    public SabotageCommand sabotageCommand = new SabotageCommand();

    @Override
    public void onEnable() {
        plugin = this;
        registerListerners();
        getCommand("start").setExecutor(startCommand);

        validateQuest.setGameData(startCommand);
        getCommand("quest").setExecutor(validateQuest);

        meetingCommand.setGameData(startCommand);
        getCommand("meeting").setExecutor(meetingCommand);
        startCommand.setMeetingData(meetingCommand);

        voteCommand.setMeetingData(meetingCommand);
        getCommand("vote").setExecutor(voteCommand);

        sabotageCommand.setGameData(startCommand);
        getCommand("sabotage").setExecutor(sabotageCommand);
    }

    public void registerListerners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(startCommand.getCommonListeners(), this);
    }
}
