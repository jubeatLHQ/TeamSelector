package mc.lhq.TeamSelector;

import java.awt.GridLayout;
import java.util.logging.Logger;

import mc.lhq.TeamSelector.Listeners.TeamPlayerListener;
import mc.lhq.TeamSelector.UI.MainFrame;
import mc.lhq.TeamSelector.UI.SelectorPanel;
import mc.lhq.TeamSelector.UI.TeamPanel.TeamPanel;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamSelector extends JavaPlugin{
	
	public final static Logger log =Logger.getLogger("Minecraft");
	public Server server;
	public Plugin plugin;
	
	public static MainFrame mainWindow;
	
	public static TeamPanel nullTeamPanel;
	
	public void onEnable(){
		server = getServer();
		plugin = this;
		createMainPane();
		server.getPluginManager().registerEvents(new TeamPlayerListener(), this);
	}
	
	public void onDisable(){
	}
	
	private void createMainPane() {
		///メインフレームを作成し、マネージャーパネルを配置する(上下分割)
		mainWindow = new MainFrame("TeamSelector");
		SelectorPanel managerMainPanel = new SelectorPanel(new GridLayout(2,1));
		mainWindow.addPanelToTab("Manager", managerMainPanel);
		
		nullTeamPanel = new TeamPanel(managerMainPanel,true);
		managerMainPanel.addTeamPanel(nullTeamPanel);
		managerMainPanel.addTeamPanel(new TeamPanel(managerMainPanel,false));
		managerMainPanel.addTeamPanel(new TeamPanel(managerMainPanel,false));
		managerMainPanel.addTeamPanel(new TeamPanel(managerMainPanel,false));
	}

}
