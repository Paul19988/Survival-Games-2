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
	
	private int DmatchTimer = 300;
	private Message m = new Message(Message.BLANK);
	
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

	public static Set<String> winner = Core.game.players.keySet();
	
	@Override
	public void tick() {
		DmatchTimer--;
		for (Player all : Bukkit.getOnlinePlayers()) {
			m.addRecipient(all);
		}
		m.send(ChatColor.AQUA + "" + ChatColor.BOLD + getTime(), MessageDisplay.ACTIONBAR);
		if(DmatchTimer == 299 | DmatchTimer % 10 == 0){
			m.send(ChatColor.RED + "" + ChatColor.BOLD + getTime(), MessageDisplay.ACTIONBAR);
		}
		if (DmatchTimer <= 5) {
			m.send(ChatColor.RED + "" + ChatColor.BOLD + DmatchTimer, MessageDisplay.TITLE);
		}
		if (DmatchTimer == 0) {
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "Game Over!", MessageDisplay.TITLE);
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "The winner of that game was: " + winner, MessageDisplay.SUBTITLE);
		}
	}
	
	private String getTime() {
		return DmatchTimer / 60 / 60 + ":" + DmatchTimer / 60 / 60 / 60 + ":" + DmatchTimer / 60 / 60 / 60 / 60;
	}
}
