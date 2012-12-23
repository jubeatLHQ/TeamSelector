package mc.lhq.TeamSelector.Listeners;

import javax.swing.SwingUtilities;

import mc.lhq.TeamSelector.PlayerData;
import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.TeamSelector;
import mc.lhq.TeamSelector.UI.SelectorPanel;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TeamPlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		PlayerData pd = new PlayerData(p);
		PlayerData.playerDatas.add(pd);
		addPlayer(p);
	}
	private void addPlayer(final Player p) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	SelectorPanel.addPlayer(TeamSelector.nullTeamPanel, p.getName());
            }
        });
	}
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event){
		Player p = event.getPlayer();
		PlayerData pd = PlayerData.getPlayerData(p);
		if(pd.getTeam()!=null){
			Team.removeTeam(pd.getTeam(), p);
		}
		PlayerData.playerDatas.remove(pd);
		removePlayer(p);
	}
	
	private void removePlayer(Player p) {
		SelectorPanel.allRemovePlayer(p.getName());
	}
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			Player p = (Player) event.getEntity();
			Team t = PlayerData.getPlayerData(p).getTeam();
			if(t!=null){
				if(!t.isStart()){
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player dp = event.getEntity();
		PlayerData pd = PlayerData.getPlayerData(dp);
		Team team = pd.getTeam();
		if(!checkTeam(team)){
			return;
		}
		
		if(dp.getKiller()!=null){
			Player p = dp.getKiller();
			PlayerData pd2 = PlayerData.getPlayerData(p);
			Team team2 = pd2.getTeam();
			if(!checkTeam(team2)){
				return;
			}
			
			pd.setDeathPoint(pd.getDeathPoint()+1);
			team.setTeamDeaths(team.getTeamDeaths()+1);
			
			pd2.setKillPoint(pd2.getKillPoint()+1);
			team2.setTeamKills(team2.getTeamKills()+1);
		}else{
			pd.setDeathPoint(pd.getDeathPoint()+1);
			team.setTeamDeaths(team.getTeamDeaths()+1);
		}
		
	}
	
	public boolean checkTeam(Team team){
		if(team==null){
			return false;
		}
		if(!team.isStart()){
			return false;
		}
		return true;
	}

}
