
package me.aperro.hello;

import me.aperro.hello.ActionListener.CustomEnterMessage;
import me.aperro.hello.ActionListener.LuckyMiner;
import me.aperro.hello.ActionListener.OreMaster;
import me.aperro.hello.command.ComandRoll;
import me.aperro.hello.command.ItemCommand;
import me.aperro.hello.command.RandomReward;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelloPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Мой первый Minecraft-плагин запущен!");

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
                .registerEvents(new OreMaster(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин выключен!");
    }
}
