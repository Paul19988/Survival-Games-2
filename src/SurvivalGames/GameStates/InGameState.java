package SurvivalGames.GameStates;

import java.util.HashMap;
import java.util.Iterator;
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
import org.bukkit.World;
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

	private Random random = new Random();

	/*
	 * 
	 * // Tier 1
	 * 
	 * ARROW(Material.ARROW, ECrateTiers.TIER1, 25),
	 * WOODEN_SWORD(Material.WOOD_SWORD, ECrateTiers.TIER1, 26),
	 * LEATHER_HELMENT(Material.LEATHER_HELMET, ECrateTiers.TIER1, 10),
	 * LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE ,ECrateTiers.TIER1, 11),
	 * LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, ECrateTiers.TIER1, 12),
	 * LEATHER_BOOTS(Material.LEATHER_BOOTS, ECrateTiers.TIER1, 13),
	 * STONE_SWORD(Material.STONE_SWORD, ECrateTiers.TIER1, 2),
	 * MELON(Material.MELON, ECrateTiers.TIER1, 95), APPLE(Material.APPLE,
	 * ECrateTiers.TIER1, 90), PUMPKIN_PIE(Material.PUMPKIN_PIE,
	 * ECrateTiers.TIER1, 93), COOKIE(Material.COOKIE, ECrateTiers.TIER1, 94),
	 * FLINT(Material.FLINT, ECrateTiers.TIER1, 30), STICK(Material.STICK,
	 * ECrateTiers.TIER1, 4), COOKED_CHICKEN(Material.COOKED_CHICKEN,
	 * ECrateTiers.TIER1, 96), RAW_CHICKEN(Material.MELON, ECrateTiers.TIER1,
	 * 97), RAW_BEEF(Material.MELON, ECrateTiers.TIER1, 98),
	 * GOLD_HELMET(Material.GOLD_HELMET, ECrateTiers.TIER1, 6),
	 * GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, ECrateTiers.TIER1, 7),
	 * GOLD_LEGGINGS(Material.GOLD_LEGGINGS, ECrateTiers.TIER1, 8),
	 * GOLD_BOOTS(Material.GOLD_BOOTS, ECrateTiers.TIER1, 9),
	 * FEATHER(Material.MELON, ECrateTiers.TIER1, 20), BREAD(Material.MELON,
	 * ECrateTiers.TIER1, 70), POTATO(Material.MELON, ECrateTiers.TIER1, 71),
	 * BAKED_POTATO(Material.MELON, ECrateTiers.TIER1, 72),
	 * IRON_INGOT(Material.MELON, ECrateTiers.TIER1, 14),
	 * FISHING_ROD(Material.MELON, ECrateTiers.TIER1, 3), CARROT(Material.MELON,
	 * ECrateTiers.TIER1, 75), STONE_AXE(Material.MELON, ECrateTiers.TIER1, 15),
	 * WODD_AXE(Material.WOOD_AXE, ECrateTiers.TIER1, 16), BOWL(Material.MELON,
	 * ECrateTiers.TIER1, 76), BOW(Material.MELON, ECrateTiers.TIER1, 17),
	 * BOAT(Material.MELON, ECrateTiers.TIER1, 18), RAW_FISH(Material.MELON,
	 * ECrateTiers.TIER1, 80), COOKED_FISH(Material.MELON, ECrateTiers.TIER1,
	 * 79),
	 * 
	 * // Tier 2
	 * 
	 * IRON_HELMET(Material.IRON_HELMET, ECrateTiers.TIER2, 75),
	 * IRON_CHESTPLATE(Material.IRON_CHESTPLATE, ECrateTiers.TIER2, 30),
	 * IRON_LEGGINGS(Material.IRON_LEGGINGS, ECrateTiers.TIER2, 75),
	 * IRON_BOOTS(Material.IRON_BOOTS, ECrateTiers.TIER2, 50),
	 * CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, ECrateTiers.TIER2, 40),
	 * CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, ECrateTiers.TIER2,
	 * 60), CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, ECrateTiers.TIER2,
	 * 41), CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, ECrateTiers.TIER2, 42),
	 * GOLD_HELMET2(Material.GOLD_HELMET, ECrateTiers.TIER2, 43),
	 * GOLD_CHESTPLATE2(Material.GOLD_CHESTPLATE, ECrateTiers.TIER2, 44),
	 * GOLD_LEGGINGS2(Material.GOLD_LEGGINGS, ECrateTiers.TIER2, 45),
	 * GOLD_BOOTS2(Material.GOLD_BOOTS, ECrateTiers.TIER2, 46),
	 * BREAD2(Material.BREAD, ECrateTiers.TIER2, 47),
	 * FLINT_AND_STEEL(Material.FLINT_AND_STEEL, ECrateTiers.TIER2, 48),
	 * BOW2(Material.BOW, ECrateTiers.TIER2, 49), //
	 * GAPPLE(Material.FISHING_ROD, ECrateTiers.TIER2, 40),
	 * DIAMOND(Material.DIAMOND, ECrateTiers.TIER2, 51),
	 * STONE_SWORD2(Material.STONE_SWORD, ECrateTiers.TIER2, 52),
	 * MUSHROOM_SOUP(Material.MUSHROOM_SOUP, ECrateTiers.TIER2, 53),
	 * COOKED_BEEF(Material.COOKED_BEEF, ECrateTiers.TIER2, 54);
	 */

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

	public static World DMatchMap;

	@EventHandler
	public void playerClick2(PlayerInteractEvent e) {

		Player pl = e.getPlayer();

		if (!gameInstance.spectators.containsKey(pl.getName())) {

			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

				if (e.getClickedBlock().getType().equals(Material.CHEST)) {

					Location loc = e.getClickedBlock().getLocation();

					if (Core.crates1.get(loc) == null) {

						Player p = e.getPlayer();

						Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Supply Crate | Tier 1");

						Core.crates1.put(loc, inv);

						inv.clear();

						Random r = new Random();
						int random = r.nextInt(4);
						Random i = new Random();

						// Set 1 ---

						if (random == 0) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.ARROW, i.nextInt(15) + 1));
						}

						if (random == 1) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.WOOD_SWORD, 1));
						}

						if (random == 2) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_BOOTS, 1));
						}

						if (random == 3) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_HELMET, 1));
						}

						if (random == 4) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.LEATHER_CHESTPLATE, 1));
						}

						Random r2 = new Random();
						int random2 = r2.nextInt(4);
						Random i2 = new Random();

						// Set 2 ---

						if (random2 == 0) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.LEATHER_LEGGINGS, 1));
						}

						if (random2 == 1) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.STONE_SWORD, 1));
						}

						if (random2 == 2) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.MELON, 1));
						}

						if (random2 == 3) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.PORK, i2.nextInt(2) + 1));
						}

						if (random2 == 4) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.GOLD_HELMET, 1));
						}

						Random r3 = new Random();
						int random3 = r3.nextInt(3);
						Random i3 = new Random();

						// Set 3 ---

						if (random3 == 0) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.FEATHER, i3.nextInt(3) + 1));
						}

						if (random3 == 1) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.STONE_SWORD, 1));
						}

						if (random3 == 2) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.GOLD_BOOTS, 1));
						}
						if (random3 == 3) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.LEATHER_LEGGINGS, 1));
						}

						Random r4 = new Random();
						int random4 = r4.nextInt(3);
						Random i4 = new Random();

						// Set 4 ---

						if (random4 == 0) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.GOLD_LEGGINGS, 1));
						}
						if (random4 == 1) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.LEATHER_CHESTPLATE, 1));
						}
						if (random4 == 2) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.CARROT_ITEM, i4.nextInt(2) + 1));
						}
						if (random4 == 3) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.STICK, i4.nextInt(1) + 1));
						}
						if (Core.crates1.get(loc) == inv) {
							p.openInventory(inv);
						}
					} else {
						Player p = e.getPlayer();
						Inventory inv = Core.crates1.get(loc);
						p.openInventory(inv);
						e.setCancelled(true);
					}

				}
			}
		}
	}

	@EventHandler
	public void playerClick(PlayerInteractEvent e) {

		Player pl = e.getPlayer();
		if (!gameInstance.spectators.containsKey(pl.getName())) {

			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

				if (e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
					
					e.setCancelled(true);

					Location loc = e.getClickedBlock().getLocation();

					if (Core.crates2.get(loc) == null) {

						Player p = e.getPlayer();

						Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Supply Crate | Tier 2");

						Core.crates2.put(loc, inv);

						inv.clear();

						Random r = new Random();
						int random = r.nextInt(6);
						Random i = new Random();

						// Set 1 ---

						if (random == 0) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.COOKED_CHICKEN, i.nextInt(1) + 1));
						}

						if (random == 1) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.WEB, i.nextInt(2) + 1));
						}

						if (random == 2) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.IRON_INGOT, i.nextInt(1) + 1));
						}

						if (random == 3) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.BOW, 1));
						}

						if (random == 4) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.IRON_BOOTS, 1));
						}

						if (random == 5) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
						}

						if (random == 6) {
							inv.setItem(i.nextInt(27), new ItemStack(Material.ARROW, i.nextInt(15) + 1));
						}

						Random r2 = new Random();
						int random2 = r2.nextInt(7);
						Random i2 = new Random();

						// Set 2 ---

						if (random2 == 0) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.PUMPKIN_PIE, i2.nextInt(1) + 1));
						}

						if (random2 == 1) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.DIAMOND, 1));
						}

						if (random2 == 2) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.COOKED_BEEF, i2.nextInt(1) + 1));
						}

						if (random2 == 3) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.IRON_CHESTPLATE, 1));
						}

						if (random2 == 4) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.BAKED_POTATO, i2.nextInt(2) + 1));
						}

						if (random2 == 5) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.CHAINMAIL_BOOTS, 1));
						}

						if (random2 == 6) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.ARROW, i2.nextInt(15) + 1));
						}

						if (random2 == 7) {
							inv.setItem(i2.nextInt(27), new ItemStack(Material.ARROW, i2.nextInt(15) + 1));
						}

						Random r3 = new Random();
						int random3 = r3.nextInt(7);
						Random i3 = new Random();

						// Set 3 ---

						if (random3 == 0) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.IRON_HELMET, 1));
						}

						if (random3 == 1) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.APPLE, i3.nextInt(3) + 1));
						}

						if (random3 == 2) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.IRON_BOOTS, 1));
						}

						if (random3 == 3) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.GOLDEN_CARROT, i3.nextInt(1) + 1));
						}

						if (random3 == 4) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.WOOD_SWORD, 1));
						}

						if (random3 == 5) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.LEATHER_HELMET, 1));
						}

						if (random3 == 6) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.GOLD_CHESTPLATE, 1));
						}

						if (random3 == 7) {
							inv.setItem(i3.nextInt(27), new ItemStack(Material.ARROW, i3.nextInt(15) + 1));
						}

						Random r4 = new Random();
						int random4 = r4.nextInt(7);
						Random i4 = new Random();

						// Set 4 ---

						if (random4 == 0) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.STONE_SWORD, 1));
						}

						if (random4 == 1) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.IRON_LEGGINGS, 1));
						}

						if (random4 == 2) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.FLINT_AND_STEEL, 1));
						}

						if (random4 == 3) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.FISHING_ROD, 1));
						}

						if (random4 == 4) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.STICK, i4.nextInt(3) + 1));
						}

						if (random4 == 5) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.LEATHER_CHESTPLATE, 1));
						}

						if (random4 == 6) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.GOLD_HELMET, 1));
						}

						if (random4 == 7) {
							inv.setItem(i4.nextInt(27), new ItemStack(Material.ARROW, i4.nextInt(15) + 1));
						}

						if (Core.crates2.get(loc) == inv) {
							p.openInventory(inv);
						}
					} else {
						Player p = e.getPlayer();
						Inventory inv = Core.crates2.get(loc);
						p.openInventory(inv);
						e.setCancelled(true);
					}
				}
			}
		}
	}

	public void TPToDMatchArena() {
		DMatchMap = Bukkit.getWorld("DeathMatch");
		@SuppressWarnings("rawtypes")
		Iterator iterator = Core.game.players.keySet().iterator();
		World dmatch = Bukkit.getWorld("DeathMatch");
		dmatch.setAutoSave(false);
		for (int i = 4; i > 0; i--) {
			Core.plugin.getConfig().get("DeathMatch.spawn" + i + ".x");
			double spawnx = Core.plugin.getConfig().getDouble("DeathMatch" + ".spawn" + i + ".x");
			double spawny = Core.plugin.getConfig().getDouble("DeathMatch" + ".spawn" + i + ".y");
			double spawnz = Core.plugin.getConfig().getDouble("DeathMatch" + ".spawn" + i + ".z");
			int spawnyaw = Core.plugin.getConfig().getInt("DeathMatch" + ".spawn" + i + ".yaw");
			int spawnpitch = Core.plugin.getConfig().getInt("DeathMatch" + ".spawn" + i + ".pitch");
			Bukkit.getPlayer((String) iterator.next()).teleport(new Location(Bukkit.getWorld("DeathMatch"), spawnx, spawny, spawnz, spawnyaw, spawnpitch));
		}
	}

	private int inGameTimer = 1200;

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		inGameTimer--;
		Player[] players = Core.plugin.getServer().getOnlinePlayers();
		Message m = new Message(Message.BLANK);
		for (Player p : players) {
			m.addRecipient(p);
		}
		m.send(ChatColor.AQUA + "" + ChatColor.BOLD + getTime(), MessageDisplay.ACTIONBAR);

		if (inGameTimer == 1) {
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 Seconds!", MessageDisplay.TITLE);
			gameInstance.nextState();
		}
		if (inGameTimer == 2)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "2 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 3)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "3 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 4)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "4 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 5)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "5 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 10)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "10 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 20)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "20 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 30)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "30 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 40)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "40 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 50)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "50 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 60)
			m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 Minute!", MessageDisplay.TITLE);
		if (inGameTimer == 299)
			m.send(ChatColor.RED + "" + ChatColor.BOLD + "Let the games begin!", MessageDisplay.TITLE);
	}

	private String timeConversion() {
		int hours = inGameTimer / 60 / 60;
		int minutes = (inGameTimer - (hours * 60 * 60)) / 60;
		int seconds = inGameTimer - (minutes * 60);

		if (seconds < 10) {
			return minutes + ":0" + seconds;
		}

		return minutes + ":" + seconds;
	}

	public String getTime() {
		return timeConversion();
	}

	// Temp removal of new Crate system due to bugs

	// /*
	// * Crate System
	// * @Author -> Thecheatgamer1
	// */
	//
	// private HashMap<Location, Inventory> invs = new HashMap<Location,
	// Inventory>();
	// private final int arraySize = 64;
	// private Material[] tier1 = new Material[arraySize];
	// private Material[] tier2 = new Material[arraySize];
	//
	// @EventHandler
	// public void onPlayerInteract(PlayerInteractEvent e) {
	// Player p = e.getPlayer();
	// Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN +
	// "Supply Crate");
	//
	// if (random.nextBoolean()) {
	// for (int a = 0; a < inv.getSize(); a++) {
	// if (random.nextInt(101) > 75) inv.addItem(new
	// ItemStack(tier1[random.nextInt(getArrayNonNullSize(tier1) + 1)]));
	// }
	// } else {
	// for (int a = 0; a < inv.getSize(); a++) {
	// if (random.nextInt(101) > 25) inv.addItem(new
	// ItemStack(tier2[random.nextInt(getArrayNonNullSize(tier2) + 1)]));
	// }
	// }
	// if (e.getAction() == Action.RIGHT_CLICK_BLOCK &&
	// e.getClickedBlock().getType().equals(Material.REDSTONE_BLOCK)) {
	// if (invs.get(e.getClickedBlock().getLocation()) != null) {
	// p.openInventory(invs.get(e.getClickedBlock().getLocation()));
	// } else {
	// invs.put(e.getClickedBlock().getLocation(), inv);
	// p.openInventory(inv);
	// }
	// }
	// }
	//
	// public void addItem(int tier, Material material) {
	// if (tier == 1) {
	// tier1[getArrayNonNullSize(tier1)] = material;
	// } else {
	// tier2[getArrayNonNullSize(tier2)] = material;
	// }
	// }
	//
	// private int getArrayNonNullSize(Material[] mat) {
	// int i = 0;
	// for (int a = 0; a < mat.length; a++) {
	// if (mat[i] != null) i++; else return i;
	// }
	// return 0;
	// }
}