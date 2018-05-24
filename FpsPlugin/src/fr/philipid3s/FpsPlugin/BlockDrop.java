package fr.philipid3s.FpsPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockDrop implements Listener {

	@EventHandler
	public void BreakBlock(BlockBreakEvent e){
		
		Location blocLoc = e.getBlock().getLocation();
		
		switch(e.getBlock().getType()){
		case IRON_ORE:
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			
			// on rajoute les drops custom
			blocLoc.getWorld().dropItemNaturally(blocLoc, new ItemStack(Material.IRON_INGOT, 2));
			
			break;
		default:
			break;
			
		}
	}
}
