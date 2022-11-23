package amonglegendminecraft.amonglegendminecraft;

import amonglegendminecraft.amonglegendminecraft.commands.StartCommand;
import amonglegendminecraft.amonglegendminecraft.listeners.CommonListeners;
import amonglegendminecraft.amonglegendminecraft.listeners.InventoryCheck;
import amonglegendminecraft.amonglegendminecraft.listeners.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AmongLegendMinecraft extends JavaPlugin {

    public static AmongLegendMinecraft plugin;
    public StartCommand startCommand = new StartCommand();

    @Override
    public void onEnable() {
        plugin = this;
        registerListerners();
        getCommand("start").setExecutor(startCommand);

    }

    public void registerListerners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new InventoryCheck(), this);
        pm.registerEvents(startCommand.getCommonListeners(), this);
    }
}
