package pl.barthvisuals.bvboxes;

import org.bukkit.plugin.java.JavaPlugin;
import pl.barthvisuals.bvboxes.commands.BoxCommand;
import pl.barthvisuals.bvboxes.listeners.BoxListener;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Ładowanie pluginu...");
        getCommand("box").setExecutor(new BoxCommand());
        getServer().getPluginManager().registerEvents(new BoxListener(), this);
        getLogger().info("Załadowano!");
    }
}
