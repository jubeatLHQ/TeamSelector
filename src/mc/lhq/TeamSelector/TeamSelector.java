package mc.lhq.TeamSelector;

import java.util.logging.Logger;

import mc.lhq.TeamSelector.Listeners.TeamPlayerListener;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamSelector extends JavaPlugin{
	
	public final static Logger log =Logger.getLogger("Minecraft");
	public Server server;
	public Plugin plugin;
	
	public void onEnable(){
		server = getServer();
		plugin = this;
		server.getPluginManager().registerEvents(new TeamPlayerListener(), this);
	}
	
	public void onDisable(){
	}

}
