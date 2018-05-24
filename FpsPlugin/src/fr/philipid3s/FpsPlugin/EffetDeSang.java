package fr.philipid3s.FpsPlugin;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EffetDeSang implements Listener {
	
	public EffetDeSang() {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onEffetDeSang(EntityDamageByEntityEvent e){
		
		Entity entity = e.getEntity();
		Location location = entity.getLocation();
		
		if(entity.getType() != EntityType.ITEM_FRAME){
			entity.getWorld().playEffect(location, Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
		}
	}

}
