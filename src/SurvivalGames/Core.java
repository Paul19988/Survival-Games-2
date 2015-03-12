package SurvivalGames;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import me.olivervscreeper.networkutilities.NULogger;
import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.command.CommandManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {
	
	public static Core plugin;
	
	public static SurvivalGamesGame game = null;
	public static World lobby;
	public static World world1;
	public static World world2;
	public static World world3;
	public static World world4;
	public static World dmatch;
	
	File f = new File(getDataFolder(), "sqldata.yml");
	public static FileConfiguration SQLConfig = YamlConfiguration.loadConfiguration(new File("plugins/SurvivalGames", "sqldata.yml"));

	File prefix = new File(getDataFolder(), "prefix.yml");
	public static FileConfiguration PrefixConfig = YamlConfiguration.loadConfiguration(new File("plugins/SurvivalGames", "prefix.yml"));
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		CommandManager manager = NetworkUtilities.getCommandManager();
		manager.registerCommands(new Commands());
		game = new SurvivalGamesGame(new NULogger(true));
		plugin = this;
		
		lobby = Bukkit.getServer().createWorld(new WorldCreator("lobby"));
		lobby.setAutoSave(false);
		world1 = Bukkit.getServer().createWorld(new WorldCreator("Map1"));
		world1.setAutoSave(false);
		world2 = Bukkit.getServer().createWorld(new WorldCreator("Map2"));
		world2.setAutoSave(false);
		world3 = Bukkit.getServer().createWorld(new WorldCreator("Map3"));
		world3.setAutoSave(false);
		world4 = Bukkit.getServer().createWorld(new WorldCreator("Map4"));
		world4.setAutoSave(false);
		dmatch = Bukkit.getServer().createWorld(new WorldCreator("DeathMatch"));
		dmatch.setAutoSave(false);
		if (!f.exists()) {
			try {
				f.createNewFile();
				SQLConfig.set("host", "");
				SQLConfig.set("port", "3306");
				SQLConfig.set("database", "");
				SQLConfig.set("username", "");
				SQLConfig.set("password", "");
				SQLConfig.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			API.c = API.GameStatsMySQL.open();
		}
		if (!prefix.exists()) {
			try {
				prefix.createNewFile();
				PrefixConfig.set("Prefix", "");
				PrefixConfig.save(prefix);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    public static HashMap<Location, Inventory> crates1 = new HashMap<Location, Inventory>();
    public static HashMap<Location, Inventory> crates2 = new HashMap<Location, Inventory>();
}
