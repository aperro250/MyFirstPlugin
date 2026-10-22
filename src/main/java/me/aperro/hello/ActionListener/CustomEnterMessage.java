package me.aperro.hello.ActionListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.event.ClickEvent;

public class CustomEnterMessage implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String player_name = event.getPlayer().getName();

//      Metod not suported but it works
//      event.setJoinMessage(String.format("%s, welcome to the server!", player_name));

         Component message = Component.text("Player ", NamedTextColor.GRAY)
                 .append(Component.text(player_name, NamedTextColor.GOLD)
                         .hoverEvent(
                                 Component.text(
                                         String.format("Welcome to the server %s", player_name)
                                 )
                         )
                         .clickEvent(
                                 ClickEvent.suggestCommand(String.format("/msg %s Hello!", player_name))
                         )
                 )
                 .append(Component.text(" joined the server!", NamedTextColor.GREEN));

         event.joinMessage(message);
    }


}
