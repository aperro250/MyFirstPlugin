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

public class OreMaster implements Listener {

    private final Random random = new Random();

    public int rollRandom(int chance) {
        return random.nextInt(chance);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        String playerName = event.getPlayer().getName();
        Material breakedBlock = event.getBlock().getType();

        // Its the 1/x posibility. For example: 4 = 25%, 10 = 10
        final int diamondDropChance = 4; // 25%
        final int emeraldDropChance = 10; // 10%
        final int goldDropChance = 4; // 25%
        final int lapisDropChance = 5; // 20%

//        final String DiamondID = "DIAMOND";
//        final String EmeraldID = "EMERALD";
//        final String GoldID = "GOLD_INGOT";
//        final String LapisID = "LAPIS_LAZULI";

        final Material diamondID = Material.DIAMOND;
        final Material emeraldID = Material.EMERALD;
        final Material goldID = Material.GOLD_INGOT;
        final Material lapisID = Material.LAPIS_LAZULI;

        int randomNum;
        String oreName;
        Material oreID;
        Component message;


        switch (breakedBlock) {

            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE ->{
                randomNum = rollRandom(diamondDropChance);
                oreName = "diamonds";
                oreID = diamondID;
            }
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE ->{
                randomNum = rollRandom(emeraldDropChance);
                oreName = "emeralds";
                oreID = emeraldID;
            }
            case GOLD_ORE, DEEPSLATE_GOLD_ORE ->{
                randomNum = rollRandom(goldDropChance);
                oreName = "gold ingots";
                oreID = goldID;
            }
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE ->{
                randomNum = rollRandom(lapisDropChance);
                oreName = "lapis";
                oreID = lapisID;
            }
            default ->{
                return;
            }
        }

        if (randomNum == 0) {
            Map<Integer, ItemStack> isOver = event.getPlayer().getInventory().addItem(new ItemStack(oreID, 2));

            if (isOver.isEmpty()) {
                message = Component.text(
                        "Awesome " + playerName + ", get 2 " + oreName + " more!",
                        NamedTextColor.GREEN);
            } else {
                message = Component.text(
                        "Sorry, dont have enought space for some free " + oreName + " :-(");
            }

            event.getPlayer().sendMessage(message);
        }
    }
}