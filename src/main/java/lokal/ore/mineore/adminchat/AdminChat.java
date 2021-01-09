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
    List<UUID> inAdminChat = new ArrayList<UUID>();

    public void onEnable() {
        getCommand("a").setExecutor(new CommandExecutor() {
            public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
                if(commandSender.hasPermission("mineore.adminczatlokal")) {
                    if(strings.length == 0) {
                        if (commandSender instanceof Player) {
                            Player p = ((Player) commandSender);
                            if(!inAdminChat.contains(p.getUniqueId())) {
                                inAdminChat.add(p.getUniqueId());
                                p.sendMessage(ChatColor.GREEN + "Jestes w trybie admin czatu");
                            }else{
                                inAdminChat.remove(p.getUniqueId());
                                p.sendMessage(ChatColor.RED + "Nie jestes juz w trybie admin czatu");
                            }
                        }else{
                            commandSender.sendMessage(ChatColor.RED + "Tylko wspaniali ludzie mogą przejść w ten tryb");
                        }
                    }else{
                        String message = combineArgs(strings);
                        for(Player player : Bukkit.getOnlinePlayers()) {
                            if(player.hasPermission("mineore.adminczatlokal")) {
                                player.sendMessage(ChatColor.YELLOW + "[CzatA] " + ChatColor.GOLD + commandSender.getName() + ": " + ChatColor.GRAY + message);
                            }
                        }
                    }
                }else{
                    commandSender.sendMessage(ChatColor.RED + "Brak permisji do tej komendy");
                }
                return true;
            }
        });

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onTalk(AsyncPlayerChatEvent e) {
                if(inAdminChat.contains(e.getPlayer().getUniqueId())) {
                    e.setCancelled(true);
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        if(player.hasPermission("mineore.adminczatlokal")) {
                            player.sendMessage(ChatColor.YELLOW + "[CzatA] " + ChatColor.GOLD + e.getPlayer().getName() + ": " + ChatColor.GRAY + e.getMessage());
                        }
                    }
                }
            }
        },this);
    }

    public String combineArgs(String[] args) {
        String ret = "";
        for(String arg : args) {
            ret+=(arg+" ");
        }
        return ret.trim();
    }
}
