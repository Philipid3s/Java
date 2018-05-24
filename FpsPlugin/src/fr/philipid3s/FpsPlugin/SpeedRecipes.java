package fr.philipid3s.FpsPlugin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpeedRecipes implements Listener {

	
	@EventHandler
	public void changeCraft(PrepareItemCraftEvent e){
		
		// on check que le craft se fait sur une table de craft
		if (e.getInventory() instanceof CraftingInventory){
			// on recupere l inventaire du craft en cours
			CraftingInventory inv = (CraftingInventory)e.getInventory();
			
			switch(inv.getResult().getType()){
			case WOOD_PICKAXE:
				ItemStack customRecipe = new ItemStack(Material.STONE_PICKAXE, 1);
				ItemMeta customM = customRecipe.getItemMeta();
				customM.setDisplayName("Ma pioche en pierre rapide");
				customM.addEnchant(Enchantment.DIG_SPEED, 2, true);
				customM.addEnchant(Enchantment.DURABILITY, 3, true);
				customRecipe.setItemMeta(customM);
				
				inv.setResult(customRecipe);
				
				break;
			default:
				break;
			}
		}
	}
}
