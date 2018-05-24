package fr.philipid3s.FpsPlugin;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class Artifice implements Listener {
	
	public Artifice() {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		
		Player p = e.getPlayer();
		Firework f = (Firework) p.getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.FIREWORK);
		
		f.detonate();
		
		FireworkMeta fm = f.getFireworkMeta();
		FireworkEffect fe = FireworkEffect.builder()
				.flicker(true)
				.withColor(Color.RED)
				.withFade(Color.BLUE)
				.with(Type.STAR)
				.trail(true)
				.build();
		
		// hauteur du feu d artifice: 1 - 4
		fm.setPower(4);
		
		fm.addEffect(fe);
		f.setFireworkMeta(fm);
	}

}
