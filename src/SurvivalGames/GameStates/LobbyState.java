package SurvivalGames.GameStates;

import java.util.ArrayList;

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
import org.bukkit.scoreboard.ScoreboardManager;

import SurvivalGames.Core;
import SurvivalGames.V;
import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.messages.MessageDisplay;

public class LobbyState extends GameState implements Listener {

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

	int lobbytimer = 60;

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		lobbytimer--;
		String timer = "";
		for (Player all : Bukkit.getOnlinePlayers()) {
			m.addRecipient(all);
		}
	    if(lobbytimer <10){
	    	timer = "00:0" + lobbytimer;
	    	m.send(ChatColor.AQUA + "" + ChatColor.BOLD + timer, MessageDisplay.ACTIONBAR);
	    }else{
		m.send(ChatColor.AQUA + "" + ChatColor.BOLD + "00:" + lobbytimer, MessageDisplay.ACTIONBAR);
	    }
		if (lobbytimer == 59) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(all).send("1 Minute till game starts!");
			}
		}
		if (lobbytimer == 50) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(all).send("50 Seconds till game starts!");
			}
		}
		if (lobbytimer == 40) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(all).send("40 Seconds till game starts!");
			}
		}
		if (lobbytimer == 30) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(all).send("30 Seconds till game starts!");
			}
		}
		if (lobbytimer == 20) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(all).send("20 Seconds till game starts!");
			}
		}
		if (lobbytimer == 10) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				V.prefix.addRecipient(all).send("10 Seconds till game starts!");
			}
		}
		if (lobbytimer == 5) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "5", MessageDisplay.TITLE);
		}
		if (lobbytimer == 4) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "4", MessageDisplay.TITLE);
		}
		if (lobbytimer == 3) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.GOLD + "" + ChatColor.BOLD + "3", MessageDisplay.TITLE);
		}
		if (lobbytimer == 2) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "2", MessageDisplay.TITLE);
		}
		if (lobbytimer == 1) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "1", MessageDisplay.TITLE);
		}
		if (lobbytimer == 0) {
			if (Bukkit.getOnlinePlayers().length < 12) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					V.prefix.addRecipient(all).send("Restarting Timer! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")");
					m.addRecipient(all);
				}
				lobbytimer = 60;
				Core.game.getLogger().log("In-Lobby", "Not Enough Players Restarting! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")");
				m.send(ChatColor.AQUA + "" + ChatColor.BOLD + "Restarting Timer! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")", MessageDisplay.TITLE);
			} else {
				for (Player all : Bukkit.getOnlinePlayers()) {
					V.prefix.addRecipient(all).send("Starting game!");
					m.addRecipient(all);
				}
				m.send(ChatColor.AQUA + "" + ChatColor.BOLD + "Starting Game!", MessageDisplay.TITLE);
				gameInstance.nextState();
			}
		}
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	Message m = new Message(Message.BLANK);
	public static ArrayList<String> Voted = new ArrayList<String>();

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

	public static int Map1;
	public static int Map2;
	public static int Map3;
	public static int Map4;

	@SuppressWarnings("deprecation")
	public void MapUpdate(Player p) {
		ScoreboardManager sm = Bukkit.getServer().getScoreboardManager();

		org.bukkit.scoreboard.Scoreboard s = sm.getNewScoreboard();
		Objective o = s.registerNewObjective("Dash", "dummy");

		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.DARK_RED + "Map Votes:");

		Score Map1Title = null;
		Score Map2Title = null;
		Score Map3Title = null;
		Score Map4Title = null;

		Map1Title = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "Tribal Vibes"));

		Map1Title.setScore(Map1);

		Map2Title = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "Avaricia"));

		Map2Title.setScore(Map2);

		Map3Title = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "SG 6"));

		Map3Title.setScore(Map3);

		Map4Title = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "SG Highway"));

		Map4Title.setScore(Map4);

		p.setScoreboard(s);

	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.showPlayer(p);
			p.showPlayer(all);
			m.addRecipient(all);
		}
		m.send("", MessageDisplay.TITLE);
		for (Player all : Bukkit.getOnlinePlayers()) {
			m.addRecipient(all);
		}
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

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemDrag(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Tribal Vibes!")) {
			V.prefix.addRecipient(p).send("You just voted for Tribal Vibes!");
			Map1 = Map1 + 1;
			V.prefix.addRecipient(p).send("Tribal Vibes now has " + Map1 + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : Bukkit.getOnlinePlayers()) {
				MapUpdate(all);
			}
		}
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Avaricia!")) {
			V.prefix.addRecipient(p).send("You just voted for Avaricia!");
			Map2 = Map2 + 1;
			V.prefix.addRecipient(p).send("Avaricia now has " + Map2 + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : Bukkit.getOnlinePlayers()) {
				MapUpdate(all);
			}
		}
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Survival Games 6!")) {
			V.prefix.addRecipient(p).send("You just voted for Survival Games 6!");
			Map3 = Map3 + 1;
			V.prefix.addRecipient(p).send("Survival Games 6 now has " + Map3 + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : Bukkit.getOnlinePlayers()) {
				MapUpdate(all);
			}
		}
		if (e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Vote Survival Games Highway!")) {
			V.prefix.addRecipient(p).send("You just voted for Survival Games Highway!");
			Map4 = Map4 + 1;
			V.prefix.addRecipient(p).send("Survival Games Highway now has " + Map4 + " Vote(s)!");
			p.closeInventory();
			p.getInventory().setItem(0, null);
			Voted.add(p.getName());
			e.setCancelled(true);
			for (Player all : Bukkit.getOnlinePlayers()) {
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
}
