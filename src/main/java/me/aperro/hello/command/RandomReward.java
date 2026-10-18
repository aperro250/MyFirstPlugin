package me.aperro.hello.command;

import me.aperro.hello.ItemCreator.ItemCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class RandomReward implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)){
            sender.sendMessage("You must be a person to use this command");
            return false;
        }

        if (!(player.hasPermission("randomreward.use"))){
            player.sendMessage("You dont have permisions");
            return false;
        }


        Random random = new Random();

        String [] items = {"DIAMOND", "GOLD_INGOT", "ENCHANTED_GOLDEN_APPLE"};
        int [] item_amounts = {16, 8, 1};

        int random_number = random.nextInt(3);

        ItemStack item = ItemCreator.generateItem(items[random_number], items[random_number], items[random_number], item_amounts[random_number]);

        player.getInventory().addItem(item);
        player.sendMessage(String.format("You obtained %d %s", item_amounts[random_number],items[random_number]));

        return true;
    }
}
