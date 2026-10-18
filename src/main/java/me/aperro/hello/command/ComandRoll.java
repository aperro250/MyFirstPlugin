package me.aperro.hello.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ComandRoll implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Random random = new Random();

        int randomNumber = random.nextInt(19)+1;

        sender.sendMessage(String.format("The random number is %s", randomNumber));
        return true;
    }
}
