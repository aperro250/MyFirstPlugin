package me.aperro.hello.ActionListener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LuckyMiner implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        String playerName = event.getPlayer().getName();
        Material breakedBlock = event.getBlock().getType();

        if (breakedBlock == Material.DIAMOND_ORE ||
            breakedBlock == Material.DEEPSLATE_DIAMOND_ORE ){

            int randomNum = random.nextInt(4);

            if (randomNum == 0){

                event.getPlayer().getInventory().addItem(new ItemStack (Material.DIAMOND, 3));
                event.getPlayer().sendMessage("Awesome " + playerName + ", get 3 diamonds more!");
            }
        }

//        Player player = event.getPlayer();
//        Material block = event.getBlock().getType();
//
//        player.sendMessage(String.format("You broke %s block", Component.text(String.valueOf(block), NamedTextColor.GREEN)));
    }



}
