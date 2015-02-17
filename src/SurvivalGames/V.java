package SurvivalGames;

import me.olivervscreeper.networkutilities.messages.Message;

import org.bukkit.ChatColor;

public class V {
	
	public static Message prefix = new Message(ChatColor.translateAlternateColorCodes('&', Core.PrefixConfig.getString("Prefix") + "%m"));

}
