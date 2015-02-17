package SurvivalGames.GameStates;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import SurvivalGames.Core;
import SurvivalGames.V;
import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;

public class RestartState extends GameState implements Listener {
	
	private int restartTimer = 11;
	
	public RestartState(Game game) {
		super(game, "Restarting", "Restarting");
	}

	@Override
	public String getDisplayName() {
		return "Restarting";
	}

	@Override
	public String getName() {
		return "Restarting";
	}

	@Override
	public boolean onStateBegin() {
		return true;
	}

	@Override
	public boolean onStateEnd() {
		return false;
	}
	
	@Override
	public void tick() {
		restartTimer--;
		if (restartTimer == 10) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(p);
			}
			V.prefix.send("Server Restarting in 10 seconds!");
		} else if (restartTimer == 5) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(p);
			}
			V.prefix.send("Server Restarting in 5 seconds!");
		} else if (restartTimer == 3) {
			for(Player p : Bukkit.getOnlinePlayers()) {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			try {
				out.writeUTF("Connect");
				out.writeUTF("arcade");
			} catch (IOException eee) {
				p.sendMessage(V.prefix + "Server Offline.");
				Bukkit.getLogger().info("You'll never see me!");
			}
			p.sendPluginMessage(Core.plugin, "BungeeCord", b.toByteArray());
			}
		} else if (restartTimer == 0){
			Bukkit.shutdown();
		}
	}
}
