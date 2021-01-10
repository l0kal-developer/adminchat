package lokal.ore.mineore.adminchat;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AdminChat extends JavaPlugin {
    private static List<UUID> inAdminChat = new ArrayList<UUID>();
    public static List<UUID> getInAdminChat(){
        return inAdminChat;
    }
    public void onEnable() {
        getCommand("a").setExecutor(new AcCommand());
        Bukkit.getPluginManager().registerEvents(new EventListener(),this);
    }

}
