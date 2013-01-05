package mc.lhq.TeamSelector.Listeners;

import mc.lhq.TeamSelector.PlayerData;
import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.UI.SelectorPanel;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class TeamPlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		PlayerData.addPlayer(p);
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
			PlayerData.reload();
			Team t = PlayerData.getPlayerData(p).getTeam();
			if(t!=null){
				if(!t.isStart()){
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onHeal(EntityRegainHealthEvent event){
		if(event.getEntity() instanceof Player){
			PlayerData.reload();
		}
	}
	@EventHandler
	public void onRespwn(PlayerRespawnEvent event){
		PlayerData.reload();
	}
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event){
		if(event.getEntity() instanceof Player){
			PlayerData.reload();
		}
	}
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			Player deffend = (Player) event.getEntity();
			PlayerData pd = PlayerData.getPlayerData(deffend);
			if(pd.getTeam()==null){
				return;
			}
			if(event.getDamager() instanceof Player){
				Player attack = (Player) event.getDamager();
				PlayerData apd = PlayerData.getPlayerData(attack);
				if(apd.getTeam()!=null){
					if(pd.getTeam().equals(apd.getTeam())){
						event.setCancelled(true);
					}
				}
			}else if(event.getDamager() instanceof Projectile){
				Projectile projectile = (Projectile) event.getDamager();
				if(projectile.getShooter() instanceof Player){
					Player attack = (Player) projectile.getShooter();
					PlayerData apd = PlayerData.getPlayerData(attack);
					if(apd.getTeam()!=null){
						if(pd.getTeam().equals(apd.getTeam())){
							event.setCancelled(true);
						}
					}
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
		PlayerData.reload();
		Team.reloadRanking();
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
