package amonglegendminecraft.amonglegendminecraft;
import amonglegendminecraft.amonglegendminecraft.commands.StartCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AmongLegendMinecraft extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        getCommand("start").setExecutor(new StartCommand());
    }
}
