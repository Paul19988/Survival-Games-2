package SurvivalGames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import me.olivervscreeper.networkutilities.game.states.GameState;

public class API {
	
    public static String host = Core.SQLConfig.getString("host");
    public static String port = Core.SQLConfig.getString("port");
    public static String database = Core.SQLConfig.getString("database");
    public static String user = Core.SQLConfig.getString("username");
    public static String password = Core.SQLConfig.getString("password");

	static MySQL GameStatsMySQL = new MySQL(host, port, database, user, password);
	static Connection c = null;
	
	public static String lobbyname = Core.PrefixConfig.getString("lobbyname");

	public static int LobbyTimer = 60;
	public static int PreGameTimer = 15;
	public static int InGameTimer = 1200;
	public static int DeathMatchTimer = 300;
	public static int RestartingTimer = 10;

	public static GameState checkState() {
		return Core.game.getState();
	}

	public static int checkPlayerSize() {
		return Core.game.players.size();
	}

	public static int checkSpectatorSize() {
		return Core.game.spectators.size();
	}

	public static int checkTimer(String timer) {
		if (timer == "LobbyTimer") {
			return LobbyTimer;
		} else if (timer == "PreGameTimer") {
			return PreGameTimer;
		} else if (timer == "InGameTimer") {
			return InGameTimer;
		} else if (timer == "DeathMatchTimer") {
			return DeathMatchTimer;
		}
		return 0;
	}

	public static boolean checkConnection() {
		try {
			if (!c.isClosed()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int checkKills(Player p) {
		try {
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM gamestats WHERE uuid = '" + p.getUniqueId() + "';");
			return res.getInt("kills");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int checkDeaths(Player p) {
		try {
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM gamestats WHERE uuid = '" + p.getUniqueId() + "';");
			return res.getInt("deaths");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int checkWins(Player p) {
		try {
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM gamestats WHERE uuid = '" + p.getUniqueId() + "';");
			return res.getInt("kills");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void checkLoses(Player p) {
		try {
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM gamestats WHERE uuid = '" + p.getUniqueId() + "';");
			res.getInt("kills");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int checkPoints(Player p) {
		try {
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM gamestats WHERE uuid = '" + p.getUniqueId() + "';");
			return res.getInt("kills");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String checkMapAuthor(String mapname) {
		try {
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM mapinfo WHERE mapname = '" + mapname + "';");
			return res.getString("mapauthor");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapname;
	}

	public static void checkMapURL(String mapname) {

	}

	public static void addKills(Player p) {
		try {
			Statement statement = c.createStatement();
			statement.executeUpdate("UPDATE gamestats SET kills=+1 WHERE uuid='" + p.getUniqueId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addDeaths(Player p) {
		try {
			Statement statement = c.createStatement();
			statement.executeUpdate("UPDATE gamestats SET deaths=+1 WHERE uuid='" + p.getUniqueId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addWins(Player p) {
		try {
			Statement statement = c.createStatement();
			statement.executeUpdate("UPDATE gamestats SET wins=+1 WHERE uuid='" + p.getUniqueId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addLoses(Player p) {
		try {
			Statement statement = c.createStatement();
			statement.executeUpdate("UPDATE gamestats SET loses=+1 WHERE uuid='" + p.getUniqueId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addPoints(Player p) {
		try {
			Statement statement = c.createStatement();
			statement.executeUpdate("UPDATE gamestats SET points=+1 WHERE uuid='" + p.getUniqueId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
