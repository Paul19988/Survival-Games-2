package SurvivalGames.GameStates;

import java.util.HashMap;
import java.util.Random;

import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.events.PlayerDeathInArenaEvent;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.messages.MessageDisplay;
import net.minecraft.server.v1_8_R1.Items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import SurvivalGames.Core;
import SurvivalGames.V;

public class InGameState extends GameState implements Listener {

	public InGameState(Game game) {
		super(game, "InGameState", "InGameState");
	}

	@Override
	public String getDisplayName() {
		return "In-Game State";
	}

	@Override
	public String getName() {
		return "In-Game State";
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

	int InGameTimer = 1200;

	Message m = new Message(Message.BLANK);

	private String timeConversion() {
		int hours = InGameTimer / 60 / 60;
		int minutes = (InGameTimer - (hours * 60 * 60)) / 60;
		int seconds = InGameTimer - (minutes * 60);

		if (seconds < 10) {
			return minutes + ":0" + seconds;
		}

		return minutes + ":" + seconds;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		InGameTimer--;
		for (Player all : Bukkit.getOnlinePlayers()) {
			m.addRecipient(all);
		}
		m.send(ChatColor.AQUA + "" + ChatColor.BOLD + timeConversion(), MessageDisplay.ACTIONBAR);
		if (InGameTimer == 299) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "Let the games begin!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 240) {

		}
		if (InGameTimer == 180) {

		}
		if (InGameTimer == 120) {

		}
		if (InGameTimer == 60) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 Minute!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 50) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "50 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 40) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "40 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 30) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "30 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 20) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "20 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 10) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "10 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 5) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "5 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 4) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "4 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 3) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.GOLD + "" + ChatColor.BOLD + "3 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 2) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "2 Seconds!", MessageDisplay.TITLE);
		}
		if (InGameTimer == 1) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				m.addRecipient(all);
			}
			m.send(ChatColor.GREEN + "" + ChatColor.BOLD + "1 Second!", MessageDisplay.TITLE);
			gameInstance.nextState();
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathInArenaEvent e) {
		Player p = e.getPlayer();
		gameInstance.removePlayer(p);
		gameInstance.addSpectator(p);
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

	public static HashMap<Location, Inventory> crates = new HashMap<Location, Inventory>();
	public static HashMap<String, Location> loc = new HashMap<String, Location>();

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType().equals(Material.REDSTONE_BLOCK)) {
				Location loc = e.getClickedBlock().getLocation();

				if (crates.get(loc) == null) {
					Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "TMSN" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ">" + ChatColor.GREEN + "Supply Crate");

					Random r = new Random();
					int random = r.nextInt(14);
					Random i = new Random();

					// Set 1 ---
					
					
					
					if (random == 0) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.ARROW, i.nextInt(15) + 1));
					}
					if (random == 1) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.WOOD_SWORD, 1));
					}
					if (random == 2) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.MELON, i.nextInt(15) + 1));
					}
					if (random == 3) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_HELMET, 1));
					}
					if (random == 4) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_BOOTS, 1));
					}
					if (random == 5) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_LEGGINGS, 1));
					}
					if (random == 6) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_CHESTPLATE, 1));
					}
					if (random == 7) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.ARROW, i.nextInt(15) + 1));
					}
					if (random == 8) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.WOOD_SWORD, 1));
					}
					if (random == 9) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.MELON, i.nextInt(15) + 1));
					}
					if (random == 10) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_HELMET, 1));
					}
					if (random == 11) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_BOOTS, 1));
					}
					if (random == 12) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_LEGGINGS, 1));
					}
					if (random == 13) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_CHESTPLATE, 1));
					}
					if (random == 14) {
						inv.setItem(i.nextInt(27), new ItemStack(Material.MELON, i.nextInt(15) + 1));
					}

					Random r2 = new Random();
					int random2 = r2.nextInt(14);
					Random i2 = new Random();

					// Set 2 ---

					if (random2 == 0) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.APPLE, i2.nextInt(3) + 1));
					}
					if (random2 == 1) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.PUMPKIN_PIE, i2.nextInt(5) + 1));
					}
					if (random2 == 2) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.COOKIE, i2.nextInt(31) + 1));
					}
					if (random2 == 3) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.FLINT, i2.nextInt(5) + 1));
					}
					if (random2 == 4) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.COOKED_CHICKEN, i2.nextInt(3) + 1));
					}
					if (random2 == 5) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.RAW_CHICKEN, i2.nextInt(15) + 1));
					}
					if (random2 == 6) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.COOKED_BEEF, i2.nextInt(1) + 1));
					}
					if (random2 == 7) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.APPLE, i2.nextInt(3) + 1));
					}
					if (random2 == 8) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.PUMPKIN_PIE, i2.nextInt(5) + 1));
					}
					if (random2 == 9) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.COOKIE, i2.nextInt(31) + 1));
					}
					if (random2 == 10) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.FLINT, i2.nextInt(5) + 1));
					}
					if (random2 == 11) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.COOKED_CHICKEN, i2.nextInt(3) + 1));
					}
					if (random2 == 12) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.RAW_CHICKEN, i2.nextInt(15) + 1));
					}
					if (random2 == 13) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.COOKED_BEEF, i2.nextInt(1) + 1));
					}
					if (random2 == 14) {
						inv.setItem(i2.nextInt(27), new ItemStack(Material.APPLE, i2.nextInt(3) + 1));
					}
					

					Random r3 = new Random();
					int random3 = r3.nextInt(14);
					Random i3 = new Random();

					// Set 3 ---

					if (random3 == 0) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.RAW_BEEF, i3.nextInt(3) + 1));
					}
					if (random3 == 1) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.FEATHER, i3.nextInt(3) + 1));
					}
					if (random3 == 2) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BREAD, i3.nextInt(5) + 1));
					}
					if (random3 == 3) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.POTATO, i3.nextInt(5) + 1));
					}
					if (random3 == 4) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BAKED_POTATO, i3.nextInt(1) + 1));
					}
					if (random3 == 5) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.CARROT_ITEM, i3.nextInt(3) + 1));
					}
					if (random3 == 6) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BOWL, i3.nextInt(1) + 1));
					}
					if (random3 == 7) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.RAW_BEEF, i3.nextInt(3) + 1));
					}
					if (random3 == 8) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.FEATHER, i3.nextInt(3) + 1));
					}
					if (random3 == 9) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BREAD, i3.nextInt(5) + 1));
					}
					if (random3 == 10) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.POTATO, i3.nextInt(5) + 1));
					}
					if (random3 == 11) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BAKED_POTATO, i3.nextInt(1) + 1));
					}
					if (random3 == 12) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.CARROT_ITEM, i3.nextInt(3) + 1));
					}
					if (random3 == 13) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BOWL, i3.nextInt(1) + 1));
					}
					if (random3 == 14) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BREAD, i3.nextInt(5) + 1));
					}

					Random r4 = new Random();
					int random4 = r4.nextInt(14);
					Random i4 = new Random();

					// Set 4 ---

					if (random4 == 0) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.RAW_FISH, i4.nextInt(7) + 1));
					}
					if (random4 == 1) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.COOKED_FISH, i4.nextInt(3) + 1));
					}
					if (random4 == 2) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.STONE_SWORD, 1));
					}
					if (random4 == 3) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.STICK, 1));
					}
					if (random4 == 4) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.GOLD_HELMET, 1));
					}
					if (random4 == 5) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.GOLD_CHESTPLATE, 1));
					}
					if (random4 == 6) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.GOLD_LEGGINGS, 1));
					}
					if (random4 == 7) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.GOLD_BOOTS, 1));
					}
					if (random4 == 8) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.IRON_INGOT, 1));
					}
					if (random4 == 9) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.FISHING_ROD, 1));
					}
					if (random4 == 10) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.STONE_AXE, 1));
					}
					if (random4 == 11) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.WOOD_AXE, 1));
					}
					if (random4 == 12) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.BOW, 1));
					}
					if (random4 == 13) {
						inv.setItem(i4.nextInt(27), new ItemStack(Material.BOAT, 1));
					}
					if (random3 == 14) {
						inv.setItem(i3.nextInt(27), new ItemStack(Material.BOWL, i3.nextInt(1) + 1));
					}

					p.openInventory(inv);
					crates.put(loc, inv);
				} else {

					Inventory inv = crates.get(loc);

					p.openInventory(inv);
				}
			}
		}
	}

}
