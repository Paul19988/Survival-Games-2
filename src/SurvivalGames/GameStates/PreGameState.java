package SurvivalGames.GameStates;

import java.util.Iterator;

import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.messages.MessageDisplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import SurvivalGames.Core;
import SurvivalGames.V;

public class PreGameState extends GameState implements Listener {
	
	private int PreGameTimer = 30;
	
	public PreGameState(Game game) {
		super(game, "PreGame State", "PreGame State");
	}

	@Override
	public String getDisplayName() {
		return "PreGame State";
	}

	@Override
	public String getName() {
		return "PreGame State";
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
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		gameInstance.addSpectator(p);
	}

	private Message m = new Message(Message.BLANK);
	public static World Map;
	
	@Override
	public void tick() {
		PreGameTimer--;
		Player[] players = Core.plugin.getServer().getOnlinePlayers();
		for (Player all : Bukkit.getOnlinePlayers()) {
			m.addRecipient(all);
			all.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
		if (PreGameTimer == 29) {
			if (Math.max(LobbyState.getMaps()[0], Math.max(LobbyState.getMaps()[2], Math.max(LobbyState.getMaps()[3], LobbyState.getMaps()[4]))) == LobbyState.getMaps()[1]) {
				Map = Core.world1;
				@SuppressWarnings("rawtypes")
				Iterator iterator = Core.game.players.keySet().iterator();
				World map1 = Bukkit.getWorld("Tribal_Vibes");
				map1.setAutoSave(false);
				for (int i = 24; i > 0; i--) {
					Core.plugin.getConfig().get("Tribal_Vibes.spawn" + i + ".x");
					double spawnx = Core.plugin.getConfig().getDouble("Tribal_Vibes" + ".spawn" + i + ".x");
					double spawny = Core.plugin.getConfig().getDouble("Tribal_Vibes" + ".spawn" + i + ".y");
					double spawnz = Core.plugin.getConfig().getDouble("Tribal_Vibes" + ".spawn" + i + ".z");
					int spawnyaw = Core.plugin.getConfig().getInt("Tribal_Vibes" + ".spawn" + i + ".yaw");
					int spawnpitch = Core.plugin.getConfig().getInt("Tribal_Vibes" + ".spawn" + i + ".pitch");
					Bukkit.getPlayer((String) iterator.next()).teleport(new Location(Bukkit.getWorld("Tribal_Vibes"), spawnx, spawny, spawnz, spawnyaw, spawnpitch));
				}
			} else if (Math.max(LobbyState.getMaps()[0], Math.max(LobbyState.getMaps()[2], Math.max(LobbyState.getMaps()[3], LobbyState.getMaps()[4]))) == LobbyState.getMaps()[2]) {
				Map = Core.world2;
				@SuppressWarnings("rawtypes")
				Iterator iterator = Core.game.players.keySet().iterator();
				World map2 = Bukkit.getWorld("Avaricia");
				map2.setAutoSave(false);
				for (int i = 1; i < Core.game.players.size(); i++) {
					Core.plugin.getConfig().get("Avaricia.spawn" + i + ".x");
					double spawnx = Core.plugin.getConfig().getDouble("Avaricia" + ".spawn" + i + ".x");
					double spawny = Core.plugin.getConfig().getDouble("Avaricia" + ".spawn" + i + ".y");
					double spawnz = Core.plugin.getConfig().getDouble("Avaricia" + ".spawn" + i + ".z");
					int spawnyaw = Core.plugin.getConfig().getInt("Avaricia" + ".spawn" + i + ".yaw");
					int spawnpitch = Core.plugin.getConfig().getInt("Avaricia" + ".spawn" + i + ".pitch");
					Bukkit.getPlayer((String) iterator.next()).teleport(new Location(Bukkit.getWorld("Avaricia"), spawnx, spawny, spawnz, spawnyaw, spawnpitch));
				}
			} else if (Math.max(LobbyState.getMaps()[0], Math.max(LobbyState.getMaps()[2], Math.max(LobbyState.getMaps()[3], LobbyState.getMaps()[4]))) == LobbyState.getMaps()[3]) {
				Map = Core.world3;
				@SuppressWarnings("rawtypes")
				Iterator iterator = Core.game.players.keySet().iterator();
				World map3 = Bukkit.getWorld("Survival_Games_6");
				map3.setAutoSave(false);
				for (int i = 1; i < Core.game.players.size(); i++) {
					Core.plugin.getConfig().get("Survival_Games_6.spawn" + i + ".x");
					double spawnx = Core.plugin.getConfig().getDouble("Survival_Games_6" + ".spawn" + i + ".x");
					double spawny = Core.plugin.getConfig().getDouble("Survival_Games_6" + ".spawn" + i + ".y");
					double spawnz = Core.plugin.getConfig().getDouble("Survival_Games_6" + ".spawn" + i + ".z");
					int spawnyaw = Core.plugin.getConfig().getInt("Survival_Games_6" + ".spawn" + i + ".yaw");
					int spawnpitch = Core.plugin.getConfig().getInt("Survival_Games_6" + ".spawn" + i + ".pitch");
					Bukkit.getPlayer((String) iterator.next()).teleport(new Location(Bukkit.getWorld("Survival_Games_6"), spawnx, spawny, spawnz, spawnyaw, spawnpitch));
				}
			} else if (Math.max(LobbyState.getMaps()[0], Math.max(LobbyState.getMaps()[2], Math.max(LobbyState.getMaps()[3], LobbyState.getMaps()[4]))) == LobbyState.getMaps()[4]) {
				Map = Core.world4;
				@SuppressWarnings("rawtypes")
				Iterator iterator = Core.game.players.keySet().iterator();
				World map4 = Bukkit.getWorld("Survival_Games_Highway");
				map4.setAutoSave(false);
				for (int i = 1; i < Core.game.players.size(); i++) {
					Core.plugin.getConfig().get("Survival_Games_Highway.spawn" + i + ".x");
					double spawnx = Core.plugin.getConfig().getDouble("Survival_Games_Highway" + ".spawn" + i + ".x");
					double spawny = Core.plugin.getConfig().getDouble("Survival_Games_Highway" + ".spawn" + i + ".y");
					double spawnz = Core.plugin.getConfig().getDouble("Survival_Games_Highway" + ".spawn" + i + ".z");
					int spawnyaw = Core.plugin.getConfig().getInt("Survival_Games_Highway" + ".spawn" + i + ".yaw");
					int spawnpitch = Core.plugin.getConfig().getInt("Survival_Games_Highway" + ".spawn" + i + ".pitch");
					Bukkit.getPlayer((String) iterator.next()).teleport(new Location(Bukkit.getWorld("Survival_Games_Highway"), spawnx, spawny, spawnz, spawnyaw, spawnpitch));
				}
			}
			for (Player all : players) {
				V.prefix.addRecipient(all).send("30 Seconds till you can move!");
			}
		}
		if (PreGameTimer == 20) {
			for (Player all : players) {
				V.prefix.addRecipient(all).send("20 Seconds till you can move!");
			}
		}
		if (PreGameTimer == 10) {
			for (Player all : players) {
				V.prefix.addRecipient(all).send("10 Seconds till you can move!");
			}
		}
		if (PreGameTimer == 5) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "5", MessageDisplay.TITLE);
		if (PreGameTimer == 4) m.send(ChatColor.RED + "" + ChatColor.BOLD + "4", MessageDisplay.TITLE);
		if (PreGameTimer == 3) m.send(ChatColor.GOLD + "" + ChatColor.BOLD + "3", MessageDisplay.TITLE);
		if (PreGameTimer == 2) m.send(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "2", MessageDisplay.TITLE);
		if (PreGameTimer == 1) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "1", MessageDisplay.TITLE);
		if (PreGameTimer == 0) gameInstance.nextState();
	}
}
