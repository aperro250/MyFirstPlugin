package me.aperro.hello.ActionListener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public class OreMaster implements Listener {

    private final FileConfiguration config;

    public OreMaster(FileConfiguration config){
        this.config = config;
    }

    private final Random random = new Random();

    public int rollRandom(int chance) {
        if (chance == 0){
            return(-1);
        }
        return random.nextInt(chance);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (!config.getBoolean("ore-master.enabled", true)){
            return;
        }

        String playerName = event.getPlayer().getName();
        Material breakedBlock = event.getBlock().getType();

        // Its the 1/x posibility. For example: 4 = 25%, 10 = 10
        final int diamondDropChance = config.getInt("ore-master.rewards.diamond.chance", 4); // default 25%
        final int emeraldDropChance = config.getInt("ore-master.rewards.emerald.chance", 10); // default 10%
        final int goldDropChance = config.getInt("ore-master.rewards.gold.chance", 4); // default 25%
        final int lapisDropChance = config.getInt("ore-master.rewards.lapis.chance", 5); // default 20%

        final int diamondDropAmount = config.getInt("ore-master.rewards.diamond.amount", 2); // default 2
        final int emeraldDropAmount = config.getInt("ore-master.rewards.emerald.amount", 2); // default 2
        final int goldDropAmount = config.getInt("ore-master.rewards.gold.amount", 2); // default 2
        final int lapisDropAmount = config.getInt("ore-master.rewards.lapis.amount", 2); // default 2


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
        int amount;
        Component message;


        switch (breakedBlock) {

            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE ->{
                randomNum = rollRandom(diamondDropChance);
                oreName = "diamonds";
                oreID = diamondID;
                amount = diamondDropAmount;
            }
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE ->{
                randomNum = rollRandom(emeraldDropChance);
                oreName = "emeralds";
                oreID = emeraldID;
                amount = emeraldDropAmount;
            }
            case GOLD_ORE, DEEPSLATE_GOLD_ORE ->{
                randomNum = rollRandom(goldDropChance);
                oreName = "gold ingots";
                oreID = goldID;
                amount = goldDropAmount;
            }
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE ->{
                randomNum = rollRandom(lapisDropChance);
                oreName = "lapis";
                oreID = lapisID;
                amount = lapisDropAmount;
            }
            default ->{
                return;
            }
        }

        if (randomNum == 0) {
            Map<Integer, ItemStack> isOver = event.getPlayer().getInventory().addItem(new ItemStack(oreID, amount));

            if (isOver.isEmpty()) {
                message = Component.text(
                        "Awesome " + playerName + ", get " + amount + " "+ oreName + " more!",
                        NamedTextColor.GREEN);
            } else {
                message = Component.text(
                        "Sorry, dont have enought space for some free " + oreName + " :-(");
            }

            event.getPlayer().sendMessage(message);
        }
    }
}