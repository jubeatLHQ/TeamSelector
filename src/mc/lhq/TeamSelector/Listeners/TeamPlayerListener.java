package mc.lhq.TeamSelector.Listeners;

import mc.lhq.TeamSelector.PlayerData;
import mc.lhq.TeamSelector.Team;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TeamPlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		if(PlayerData.getPlayerData(p)==null){
			PlayerData pd = new PlayerData(p);
			PlayerData.playerDatas.add(pd);
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			Player p = (Player) event.getEntity();
			Team t = new PlayerData(p).getTeam();
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
