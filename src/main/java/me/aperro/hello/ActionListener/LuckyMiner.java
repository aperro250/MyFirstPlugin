package me.aperro.hello.ActionListener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public class LuckyMiner implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        String playerName = event.getPlayer().getName();
        Material breakedBlock = event.getBlock().getType();

        if (breakedBlock == Material.DIAMOND_ORE ||
            breakedBlock == Material.DEEPSLATE_DIAMOND_ORE ){

            Component message;
            // Its the 1/x posibility. For example: 4 = 25%, 10 = 10%
            int dropChance = 4;
            int randomNum = random.nextInt(dropChance);

            if (randomNum == 0){

                Map <Integer, ItemStack> isOver = event.getPlayer().getInventory().addItem(new ItemStack (Material.DIAMOND, 3));
             // event.getPlayer().sendMessage("Awesome " + playerName + ", get 3 diamonds more!");

                if (isOver.isEmpty()) {
                    message = Component.text(
                            "Awesome " + playerName + ", get 3 diamonds more!",
                            NamedTextColor.GREEN);
                } else {
                    message = Component.text(
                            "Sorry, dont have enought space for some free diamonds :-(");
                }

                event.getPlayer().sendMessage(message);
            }
        }

//        Player player = event.getPlayer();
//        Material block = event.getBlock().getType();
//
//        player.sendMessage(String.format("You broke %s block", Component.text(String.valueOf(block), NamedTextColor.GREEN)));
    }



}
