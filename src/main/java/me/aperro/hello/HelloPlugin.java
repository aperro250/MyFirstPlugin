
package me.aperro.hello;

import me.aperro.hello.ActionListener.CustomEnterMessage;
import me.aperro.hello.ActionListener.OreMaster;
import me.aperro.hello.command.ComandRoll;
import me.aperro.hello.command.ItemCommand;
import me.aperro.hello.command.RandomReward;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelloPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        final String[] ores = {
                "diamond",
                "emerald",
                "gold",
                "lapis"
        };
        boolean pluginOff = false;

        for (String ore : ores) {
            if (getConfig().getInt("ore-master.rewards." + ore + ".chance") <= 0 ||
                    getConfig().getInt("ore-master.rewards." + ore + ".amount") <= 0) {
                getLogger().warning("Bad config for ore-master ore " + ore);
                pluginOff = true;
            }
        }

        if (pluginOff){
            getLogger().warning("Shutting down HelloPlugin!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getCommand("getcustomitem") != null) {
            getCommand("getcustomitem").setExecutor(new ItemCommand());
            getLogger().info("The item was registred");
        } else {

            getLogger().info("This comand is not in plugin.yml");
        }

        if (getCommand("roll") != null) {
            getCommand("roll").setExecutor(new ComandRoll());
            getLogger().info("The item was registred");
        } else {

            getLogger().info("This comand is not in plugin.yml");
        }

        if (getCommand("randomreward") != null) {
            getCommand("randomreward").setExecutor(new RandomReward());
        } else {
            getLogger().info("This comand is not in plugin.yml");
        }

        getServer().getPluginManager()
                .registerEvents(new CustomEnterMessage(), this);

        getServer().getPluginManager()
                .registerEvents(new OreMaster(getConfig()), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин выключен!");
    }
}
