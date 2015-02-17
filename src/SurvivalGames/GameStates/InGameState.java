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
	
	private Random random = new Random();
	private StackTraceElement[] ele = Thread.currentThread().getStackTrace();
	
	enum ECrateTiers {
		TIER1(),
		TIER2()
	}
	
	enum ECrateItem {
		
		// Tier 1
		
		ARROW1(Material.ARROW, ECrateTiers.TIER1, 75),
		WOODEN_SWORD1(Material.WOOD_SWORD, ECrateTiers.TIER1, 60),
		MELON1(Material.MELON, ECrateTiers.TIER1, 95),
		LEATHER_HELMENT1(Material.LEATHER_HELMET, ECrateTiers.TIER1, 40),
		LEATHER_CHESTPLATE1(Material.LEATHER_CHESTPLATE ,ECrateTiers.TIER1, 20),
		LEATHER_LEGGINGS1(Material.LEATHER_LEGGINGS, ECrateTiers.TIER1, 30),
		LEATHER_BOOTS1(Material.LEATHER_BOOTS, ECrateTiers.TIER1, 50),
		
		// Tier 2
		
		APPLE2(Material.APPLE, ECrateTiers.TIER2, 75),
		PUMPKIN_PIE2(Material.PUMPKIN_PIE, ECrateTiers.TIER2, 30),
		COOKIE2(Material.COOKIE, ECrateTiers.TIER2, 75),
		FLINT2(Material.FLINT, ECrateTiers.TIER2, 50),
		COOKED_CHICKEN2(Material.COOKED_CHICKEN, ECrateTiers.TIER2, 40),
		RAW_CHICKEN2(Material.RAW_CHICKEN, ECrateTiers.TIER2, 60),
		COOKED_BEEF2(Material.COOKED_BEEF, ECrateTiers.TIER2, 40);
		
		private Material material;
		private ECrateTiers tier;
		private int chanceToSpawnInTier;
		
		ECrateItem(Material material, ECrateTiers tier, int chanceToSpawnInTier) {
			this.material = material;
			this.tier = tier;
			this.chanceToSpawnInTier = chanceToSpawnInTier;
		}
		
		public Material getItemMaterial(ECrateItem item) {return material;}
		public ECrateTiers getItemTier(ECrateItem item) {return tier;}
		public int getChanceToSpawn(ECrateItem item) {return chanceToSpawnInTier;}
	}
	
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
	
	private int inGameTimer = 1200;
	
	@Override
	public void tick() {
		inGameTimer--;
		Player[] players = Core.plugin.getServer().getOnlinePlayers();
		Message m = new Message(Message.BLANK);
		for (Player p : players) {
			m.addRecipient(p);
		}
		m.send(ChatColor.AQUA + "" + ChatColor.BOLD + getTime(), MessageDisplay.ACTIONBAR);
		
		if (inGameTimer == 1) {m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 Seconds!", MessageDisplay.TITLE); gameInstance.nextState();}
		if (inGameTimer == 2) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "2 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 3) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "3 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 4) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "4 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 5) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "5 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 10) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "10 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 20) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "20 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 30) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "30 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 40) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "40 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 50) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "50 Seconds!", MessageDisplay.TITLE);
		if (inGameTimer == 60) m.send(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 Minute!", MessageDisplay.TITLE);
		if (inGameTimer == 299) m.send(ChatColor.RED + "" + ChatColor.BOLD + "Let the games begin!", MessageDisplay.TITLE); 
	}
	
	public String getTime() {
		return inGameTimer / 60 / 60 + ":" + inGameTimer / 60 / 60 / 60 + ":" + inGameTimer / 60 / 60 / 60 / 60;
	}
	
	private int tier1ItemAmount = getItemsinTierSector(ECrateTiers.TIER1);
	private int tier2ItemAmount = getItemsinTierSector(ECrateTiers.TIER2);
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Location loc;
		Inventory inv;
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType().equals(Material.REDSTONE_BLOCK)) {
			loc = e.getClickedBlock().getLocation();
			inv = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "TMSN" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ">" + ChatColor.GREEN + "Supply Crate");
			
			int itemsInChest = random.nextInt();
			ECrateTiers randomTier = random.nextInt(2) == 0 ? ECrateTiers.TIER1 : ECrateTiers.TIER2;
			
			for (int i = 0; i < itemsInChest; i++) {
				inv.setItem(random.nextInt(inv.getSize()), new ItemStack(getRandomItem(randomTier)));
			}
			p.openInventory(inv);
		}
	}
	
	private int randomchance = 0;
	private int[] options = getChoicesOfValues(ECrateTiers.TIER1);
	private int[] options2 = getChoicesOfValues(ECrateTiers.TIER2);
	private int[] chosenItems;
	
	private Material getRandomItem(ECrateTiers tier) {
		int chances = random.nextInt(options.length);
		chosenItems = new int[chances];
		if (tier == ECrateTiers.TIER1) {
			for (int item = 0; item < chances; item++) {
				chosenItems[item] = options[item];
			}
			
			for (int m = 0; m < ECrateItem.values().length; m++) {
				int a = ECrateItem.values()[m].chanceToSpawnInTier;
				if (a == chosenItems[random.nextInt(chosenItems.length)]) {
					return ECrateItem.values()[m].material;
				}
			}
		} else if (tier == ECrateTiers.TIER2) {
			for (int item = 0; item < chances; item++) {
				chosenItems[item] = options[item];
			}
			
			for (int m = 0; m < ECrateItem.values().length; m++) {
				int a = ECrateItem.values()[m].chanceToSpawnInTier;
				if (a == chosenItems[random.nextInt(chosenItems.length)]) {
					return ECrateItem.values()[m].material;
				}
			}
		} else {
			throwDeveloperErrorMessage(getErrorLocation());
			return Material.AIR;
		}
		throwDeveloperErrorMessage(getErrorLocation(), new NullPointerException());
		return null;
	}
	
	private int getItemsinTierSector(ECrateTiers tier) {
		int amount = 0;
		for (int a = 0; a < ECrateItem.values().length; a++) {
			if (ECrateItem.values()[a].name().contains((tier == ECrateTiers.TIER1 ? "1" : "2"))) amount++;
		}
		return amount;
	}
	
	private int[] getChoicesOfValues(ECrateTiers tier) {
		int[] values = new int[(tier == ECrateTiers.TIER1 ? tier1ItemAmount : tier2ItemAmount)];
		for (int a = tier == ECrateTiers.TIER1 ? tier1ItemAmount : 0; a < (tier == ECrateTiers.TIER1 ? tier1ItemAmount : tier2ItemAmount); a++) {
			values[a] = ECrateItem.values()[a].chanceToSpawnInTier;
		}
		return values;
	}
	
	private void throwDeveloperErrorMessage(String errorLocation) {
		Core.game.getLogger().log("Developer", "An Error has occured! [" + errorLocation + "] \nPlease contact Twitter >>> @Paul19988 or @Thecheatgamer1");
	}
	
	private void throwDeveloperErrorMessage(String errorLocation, Exception ex) {
		Core.game.getLogger().log("Developer", "An Error has occured! [" + errorLocation + "]\n" + ex.getMessage() + " \nPlease contact Twitter >>> @Paul19988 or @Thecheatgamer1");
	}
	
	private String getErrorLocation() {
		return ele[0].getFileName() + " >> " + ele[0].getMethodName() + " >> " + ele[0].getLineNumber();
	}
}