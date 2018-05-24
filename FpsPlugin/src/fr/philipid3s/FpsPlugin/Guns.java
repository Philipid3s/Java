package fr.philipid3s.FpsPlugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Guns implements Listener {

	public Guns() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * gestion des domages
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Snowball){
			Snowball s = (Snowball) e.getDamager();
			
			if (s.getShooter() instanceof Player){
				Player shooter = (Player) s.getShooter();
				
				if(shooter.getItemInHand().getType() == Material.DIAMOND_HOE){
					e.setDamage(100);
				}
			}
		}
	}
	
	/*
	 * gestion des armes
	 */
	@EventHandler
	public void onPInteract(PlayerInteractEvent e){
		e.setCancelled(false);
		
		Player p = e.getPlayer();
		if(e.getItem() != null){
			if(e.getItem().getType() == Material.DIAMOND_HOE){
				
				if(e.getAction() == Action.RIGHT_CLICK_AIR){
					
					p.launchProjectile(Snowball.class);
					
					/* gestion des munitions
					 * 
					if(p.getInventory().contains(Material.DIAMOND)){
						p.launchProjectile(Snowball.class);
						p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 1));
						p.updateInventory();
					}
					else {
						// ne fonctionne pas avec ChatColor ?!?!?!
						p.sendMessage("Vous etes a court de munitions");
					}
					*/
					
					e.setCancelled(true);
				}
				
				// Effet de ZOOM
				if(e.getAction() == Action.LEFT_CLICK_AIR){
					if(p.hasPotionEffect(PotionEffectType.SLOW)){
						p.removePotionEffect(PotionEffectType.SLOW);
					}
					else{
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 9999, 20));
					}
					
					e.setCancelled(true);
				}
			}
		}
	}
}
