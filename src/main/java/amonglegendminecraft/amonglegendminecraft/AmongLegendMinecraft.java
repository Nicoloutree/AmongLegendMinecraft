package amonglegendminecraft.amonglegendminecraft;

import amonglegendminecraft.amonglegendminecraft.commands.MeetingCommand;
import amonglegendminecraft.amonglegendminecraft.commands.StartCommand;
import amonglegendminecraft.amonglegendminecraft.commands.ValidateQuest;
import amonglegendminecraft.amonglegendminecraft.commands.VoteCommand;
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
    }

    public void registerListerners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(startCommand.getCommonListeners(), this);
    }
}
