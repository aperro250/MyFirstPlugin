package me.aperro.hello.ItemCreator;

import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemCreator {
    public static ItemStack generateItem(String type, String name, String description, int amount) {

        ItemStack item = new ItemStack(Material.valueOf(type), amount);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {

            meta.setDisplayName(name);
            meta.setLore(List.of(description));
        }

        item.setItemMeta(meta);

        return item;
    }


}
