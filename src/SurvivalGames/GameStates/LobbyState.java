package SurvivalGames.GameStates;

import java.util.ArrayList;

import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.messages.MessageDisplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import SurvivalGames.Core;
import SurvivalGames.V;

public class LobbyState extends GameState implements Listener {

	private int lobbyTimer = 60;
	
	public LobbyState(Game game) {
		super(game, "LobbyState", "LobbyState");
	}

	@Override
	public String getDisplayName() {
		return "In-Lobby";
	}

	@Override
	public String getName() {
		return "In-Lobby";
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
	
	@Override
	public void tick() {
		lobbyTimer--;
		Player[] players = Core.plugin.getServer().getOnlinePlayers();
		Message m = new Message(Message.BLANK);
		for (Player p : players) {
			m.addRecipient(p);
		}
		m.send(ChatColor.AQUA + "" + ChatColor.BOLD + "00:" + lobbyTimer, MessageDisplay.ACTIONBAR);
		
		if (lobbyTimer == 0) {
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 Seconds!", MessageDisplay.TITLE); 
			if (players.length < 12) {
				
			} else {
				for (Player all : players) {
					V.prefix.addRecipient(all).send("Starting game!");
				}
				m.send(ChatColor.AQUA + "" + ChatColor.BOLD + "Starting Game!", MessageDisplay.TITLE);
				gameInstance.nextState();
			}
		}
		if (lobbyTimer == 0) {
			if (Core.plugin.getServer().getOnlinePlayers().length < 12) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					V.prefix.addRecipient(all).send("Restarting Timer! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")");
				}
				lobbyTimer = 60;
				Core.game.getLogger().log("In-Lobby", "Not Enough Players Restarting! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")");
				m.send(ChatColor.AQUA + "" + ChatColor.BOLD + "Restarting Timer! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")", MessageDisplay.TITLE);
			} else {
				for (Player all : Core.plugin.getServer().getOnlinePlayers()) {
					V.prefix.addRecipient(all).send("Starting game!");
				}
				m.send(ChatColor.AQUA + "" + ChatColor.BOLD + "Starting Game!", MessageDisplay.TITLE);
				gameInstance.nextState();
			}
		}
		if (lobbyTimer == 1) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "1", MessageDisplay.TITLE);
		if (lobbyTimer == 2) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "2", MessageDisplay.TITLE);
		if (lobbyTimer == 3) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "3", MessageDisplay.TITLE);
		if (lobbyTimer == 4) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "4", MessageDisplay.TITLE);
		if (lobbyTimer == 5) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "5", MessageDisplay.TITLE);
		if (lobbyTimer == 6) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "Game will begin in...", MessageDisplay.TITLE);
		if (lobbyTimer == 10) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "10 Seconds Until the Game Begins!", MessageDisplay.TITLE);
		if (lobbyTimer == 20) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "20 Seconds Until the Game Begins!", MessageDisplay.TITLE);
		if (lobbyTimer == 30) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "30 Seconds Until the Game Begins!", MessageDisplay.TITLE);
		if (lobbyTimer == 40) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "40 Seconds Until the Game Begins!", MessageDisplay.TITLE);
		if (lobbyTimer == 50) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "50 Seconds Until the Game Begins!", MessageDisplay.TITLE);
		if (lobbyTimer == 59) m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "1 Minute Until the Game Begins!", MessageDisplay.TITLE);
	}
	
	private static int mapAmount = 4;
	private static int[] maps = new int[mapAmount];
	private Score[] mapTitles = new Score[mapAmount];
	
	private ScoreboardManager sm;
	private Scoreboard s;
	private Objective o;

	@SuppressWarnings("deprecation")
	public void MapUpdate(Player p) {
		sm = Bukkit.getServer().getScoreboardManager();

		s = sm.getNewScoreboard();
		o = s.registerNewObjective("Dash", "dummy");

		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.DARK_RED + "Map Votes:");
		
		mapTitles[0] = o.getScore(Core.plugin.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "Tribal Vibes"));
		mapTitles[0].setScore(maps[0]);

		mapTitles[1] = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "Avaricia"));
		mapTitles[1].setScore(maps[1]);

		mapTitles[2] = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "SG 6"));
		mapTitles[2].setScore(maps[2]);

		mapTitles[3] = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "SG Highway"));
		mapTitles[3].setScore(maps[3]);
		
		p.setScoreboard(s);
	}
	
	private Message m = new Message(Message.BLANK);
	public ArrayList<String> Voted = new ArrayList<String>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Player[] players = Core.plugin.getServer().getOnlinePlayers();
		for (Player all : players) {
			all.showPlayer(p);
			p.showPlayer(all);
			m.addRecipient(all);
		}
		
		m.send("", MessageDisplay.TITLE);
		m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + p.getName() + ChatColor.AQUA + "" + ChatColor.BOLD + " has joined Survival Games!", MessageDisplay.SUBTITLE);
		
		e.setJoinMessage(null);
		Core.game.addPlayer(p);
		MapUpdate(p);
		if (!Voted.contains(p.getName())) {
			ItemStack Vote = new ItemStack(Material.COMPASS, 1);
			ItemMeta VoteMeta = Vote.getItemMeta();
			VoteMeta.setDisplayName(ChatColor.GOLD + "Voting Machine!");
			Vote.setItemMeta(VoteMeta);
			p.getInventory().setItem(0, Vote);
		} else {
			for (Player all : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(all).send("You have already voted!");
			}
		}
	}
	
	@EventHandler
	public void onItemDragged(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Player[] players = Core.plugin.getServer().getOnlinePlayers();
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Tribal Vibes!")) {
			V.prefix.addRecipient(p).send("You just voted for Tribal Vibes!");
			maps[0] = maps[0] + 1;
			V.prefix.addRecipient(p).send("Tribal Vibes now has " + maps[0] + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : players) {
				MapUpdate(all);
			}
		}
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Avaricia!")) {
			V.prefix.addRecipient(p).send("You just voted for Avaricia!");
			maps[1] = maps[1] + 1;
			V.prefix.addRecipient(p).send("Avaricia now has " + maps[1] + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : players) {
				MapUpdate(all);
			}
		}
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Survival Games 6!")) {
			V.prefix.addRecipient(p).send("You just voted for Survival Games 6!");
			maps[2] = maps[2] + 1;
			V.prefix.addRecipient(p).send("Survival Games 6 now has " + maps[2] + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : players) {
				MapUpdate(all);
			}
		}
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Survival Games Highway!")) {
			V.prefix.addRecipient(p).send("You just voted for Survival Games Highway!");
			maps[3] = maps[3] + 1;
			V.prefix.addRecipient(p).send("Survival Games Highway now has " + maps[3] + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : players) {
				MapUpdate(all);
			}
		}
		e.setCancelled(true);
	}

	public Inventory addItems(Inventory inv) {
		ItemStack Map1 = new ItemStack(Material.MAP, 1);
		ItemMeta Map1Meta = Map1.getItemMeta();
		Map1Meta.setDisplayName(ChatColor.GREEN + "Vote Tribal Vibes!");
		Map1.setItemMeta(Map1Meta);
		inv.setItem(1, Map1);

		ItemStack Map2 = new ItemStack(Material.MAP, 1);
		ItemMeta Map2Meta = Map2.getItemMeta();
		Map2Meta.setDisplayName(ChatColor.GREEN + "Vote Avaricia!");
		Map2.setItemMeta(Map2Meta);
		inv.setItem(3, Map2);

		ItemStack Map3 = new ItemStack(Material.MAP, 1);
		ItemMeta Map3Meta = Map3.getItemMeta();
		Map3Meta.setDisplayName(ChatColor.GREEN + "Vote Survival Games 6!");
		Map3.setItemMeta(Map3Meta);
		inv.setItem(5, Map3);

		ItemStack Map4 = new ItemStack(Material.MAP, 1);
		ItemMeta Map4Meta = Map4.getItemMeta();
		Map4Meta.setDisplayName(ChatColor.GREEN + "Vote Survival Games Highway!");
		Map4.setItemMeta(Map4Meta);
		inv.setItem(7, Map4);

		return inv;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.COMPASS) {
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Voting Machine");
			inv = addItems(inv);
			p.openInventory(inv);
		}
		e.setCancelled(true);
	}
	
	public static int[] getMaps() {
		return maps;
	}
}
