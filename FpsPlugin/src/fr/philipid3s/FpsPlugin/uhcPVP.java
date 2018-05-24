package fr.philipid3s.FpsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class uhcPVP implements Listener {

	// methode generique simulant la mort du joueur
	public void fakeDeath(Entity entity, double damage){
		Player p = (Player)entity;
		double health = p.getHealth();
		
		if(damage >= health) {
			// le joueur est mort
			
			p.setGameMode(GameMode.SPECTATOR);
			Bukkit.broadcastMessage(p.getName() + " est elimine!");
			FpsPlugin.getInstance().playerInGame.remove(p.getUniqueId());
		}
	}
	
	@EventHandler 
	public void fakeDeath(EntityDamageByEntityEvent e){
		
		if(e.getEntity() instanceof Player){
			fakeDeath(e.getEntity(), e.getDamage());
		}
	}
}
