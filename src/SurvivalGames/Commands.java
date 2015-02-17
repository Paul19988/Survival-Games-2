package SurvivalGames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import me.olivervscreeper.networkutilities.command.Command;
import me.olivervscreeper.networkutilities.messages.Message;
import me.olivervscreeper.networkutilities.serialization.LocationSerialization;
import me.olivervscreeper.networkutilities.utils.PasteUtils;

public class Commands{

	@Command(label="log", permission="tmsn.admin.log")
	public void log(Player p, List<String> args){
		new Message(Message.INFO).addRecipient(p).send("Pasted: " + Core.game.getLogger().getLog());
	}
	
	@Command(label = "config-mylocation", permission = "tmsn.admin.config")
    public void onLocationCommand(Player p, List<String> args){
        new Message(Message.INFO).addRecipient(p).send("Pasted: " + PasteUtils.paste(LocationSerialization.serializeLocationAsString(p.getLocation())));
	}
	
	@Command(label="world", permission="tmsn.admin.world")
	public void world(Player p, List<String> args){
		Location loc = new Location(Bukkit.getServer().createWorld(new WorldCreator(args.get(0))), 0.5, 100, 0.5);
	    p.teleport(loc);
	    V.prefix.addRecipient(p).send("You have been teleported to " + args.get(0));
	}
	
	@Command(label="next", permission="tmsn.admin.next")
	public void next(Player p, List<String> args){
		Core.game.nextState();
		Core.game.getLogger().log("In-Lobby", p.getName() + " has forced the game onto the state: " + Core.game.getState().getDisplayName());
		V.prefix.addRecipient(p).send("You have forced the game onto the state: " + Core.game.getState().getDisplayName());
	}
	
    public List<String> spawns = new ArrayList<String>();

    @Command(label="setspawn", permission="tmsn.admin.setspawn")
    public void setspawn(Player p, List<String> args){
    	try{
		Core.plugin.getConfig().set(p.getWorld().getName() + ".spawn" + args.get(0) + ".x", p.getLocation().getX());
		Core.plugin.getConfig().set(p.getWorld().getName() + ".spawn" + args.get(0) + ".y", p.getLocation().getY());
		Core.plugin.getConfig().set(p.getWorld().getName() + ".spawn" + args.get(0) + ".z", p.getLocation().getZ());
		Core.plugin.getConfig().set(p.getWorld().getName() + ".spawn" + args.get(0) + ".yaw", p.getLocation().getYaw());
		Core.plugin.getConfig().set(p.getWorld().getName() + ".spawn" + args.get(0) + ".pitch", p.getLocation().getPitch());
		Core.plugin.saveConfig();
		new Message(Message.INFO).addRecipient(p).send("Spawn saved.");
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		new Message(Message.INFO).addRecipient(p).send("Failed to save.");
    	}
    }
    
}
