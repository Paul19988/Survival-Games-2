package SurvivalGames.GameStates;

import java.util.Set;

import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.messages.MessageDisplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import SurvivalGames.Core;

public class DeathMatchState extends GameState implements Listener {

	public DeathMatchState(Game game) {
		super(game, "DeathMatchState", "DeathMatchState");
	}

	@Override
	public String getDisplayName() {
		return "DMatch State";
	}

	@Override
	public String getName() {
		return "DMatch State";
	}

	@Override
	public boolean onStateBegin() {
		registerListener(this);
		return true;
	}

	@Override
	public boolean onStateEnd() {
		unregisterListener(this);
		return true;
	}

	int DmatchTimer = 300;

	Message m = new Message(Message.BLANK);

	private String timeConversion() {
		int hours = DmatchTimer / 60 / 60;
		int minutes = (DmatchTimer - (hours * 60 * 60)) / 60;
		int seconds = DmatchTimer - (minutes * 60);

		if (seconds < 10) {
			return minutes + ":0" + seconds;
		}

		return minutes + ":" + seconds;
	}

	public static Set<String> winner = Core.game.players.keySet();

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		DmatchTimer--;
		for (Player all : Bukkit.getOnlinePlayers()) {
			m.addRecipient(all);
		}
		m.send(ChatColor.AQUA + "" + ChatColor.BOLD + timeConversion(), MessageDisplay.ACTIONBAR);
		if(DmatchTimer == 299 | DmatchTimer % 10 == 0){
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.RED + "" + ChatColor.BOLD + timeConversion(), MessageDisplay.ACTIONBAR);
		}
		if (DmatchTimer <= 5) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.RED + "" + ChatColor.BOLD + DmatchTimer, MessageDisplay.TITLE);
		}
		if (DmatchTimer == 0) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "Game Over!", MessageDisplay.TITLE);
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "The winner of that game was: " + winner, MessageDisplay.SUBTITLE);
		}
	}

}
