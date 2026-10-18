package me.aperro.hello.command;

import me.aperro.hello.ItemCreator.ItemCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)){
            sender.sendMessage("You must be a player to send this command");
            return false;
        }

        if (!player.hasPermission("getcustomitem.admin")){
            player.sendMessage("You dont have permisions");
            return false;
        }

        ItemStack itemStack = ItemCreator.generateItem( "WOODEN_SWORD", getRandomName(), "A wooden sword!", 1);

        player.getInventory().addItem(itemStack);
        player.sendMessage("Item was created and aded to your inventory");

        return true;
    }

    private static @Nullable String getRandomName() {

        Random random = new Random();
        String[] names = {"dasd", "dsad", "asdasdsd", "sda"};

        return names[new Random().nextInt(names.length)];
    }
}
