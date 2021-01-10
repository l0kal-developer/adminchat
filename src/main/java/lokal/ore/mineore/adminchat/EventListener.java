package lokal.ore.mineore.adminchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventListener implements Listener {
	@EventHandler
	public void onTalk(AsyncPlayerChatEvent e) {
		if(AdminChat.getInAdminChat().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			for(Player player : Bukkit.getOnlinePlayers()) {
				if(player.hasPermission("mineore.adminczatlokal")) {
					player.sendMessage(ChatColor.YELLOW + "[CzatA] " + ChatColor.GOLD + e.getPlayer().getName() + ": " + ChatColor.GRAY + e.getMessage());
				}
			}
		}
	}
}
