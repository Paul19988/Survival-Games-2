package SurvivalGames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.event.Listener;

import SurvivalGames.GameStates.DeathMatchState;
import SurvivalGames.GameStates.InGameState;
import SurvivalGames.GameStates.LobbyState;
import SurvivalGames.GameStates.PreGameState;
import me.olivervscreeper.networkutilities.NULogger;
import me.olivervscreeper.networkutilities.game.Game;
import me.olivervscreeper.networkutilities.game.states.GameState;
import me.olivervscreeper.networkutilities.serialization.LocationSerialization;

public class SurvivalGamesGame extends Game implements Listener{

	public SurvivalGamesGame(NULogger logger) {
		super(logger, "SurvivalGames", "SurvivalGames");
	}

	List<GameState> states = new ArrayList<GameState>();
	
	@Override
	public List<GameState> getAllStates() {
		states.add(new LobbyState(this));
		states.add(new PreGameState(this));
		states.add(new InGameState(this));
		states.add(new DeathMatchState(this));
		return states;
	}

	@Override
	public Location getLobbyLocation() {
		return LocationSerialization.getLocationMeta("{\"world\":\"lobby\",\"x\":-97.4980778890179,\"y\":31,\"z\":-49.58343182627862,\"pitch\":1.4999947547912598,\"yaw\":0}");
	}

}
