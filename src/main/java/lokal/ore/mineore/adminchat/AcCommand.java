package lokal.ore.mineore.adminchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AcCommand implements CommandExecutor {

	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if(commandSender.hasPermission("mineore.adminczatlokal")) {
			if(strings.length == 0) {
				if (commandSender instanceof Player) {
					Player p = ((Player) commandSender);
					List<UUID> adminchat = AdminChat.getInAdminChat();
					if(!adminchat.contains(p.getUniqueId())) {
						adminchat.add(p.getUniqueId());
						p.sendMessage(ChatColor.GREEN + "Jestes w trybie admin czatu");
					}else{
						adminchat.remove(p.getUniqueId());
						p.sendMessage(ChatColor.RED + "Nie jestes juz w trybie admin czatu");
					}
				}else{
					commandSender.sendMessage(ChatColor.RED + "Tylko wspaniali ludzie mogą przejść w ten tryb");
				}
			}else{
				String message = combineArgs(strings);
				message = ChatColor.YELLOW + "[CzatA] " + ChatColor.GOLD + commandSender.getName() + ": " + ChatColor.GRAY + message;
				Bukkit.broadcast(message,"mineore.adminczatlokal");

			}
		}else{
			commandSender.sendMessage(ChatColor.RED + "Brak permisji do tej komendy");
		}
		return true;
	}
	public String combineArgs(String[] args) {
		String ret = "";
		for(String arg : args) {
			ret+=(arg+" ");
		}
		return ret.trim();
	}
}
