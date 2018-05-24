package fr.philipid3s.FpsPlugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FpsPluginCommand implements CommandExecutor {

	public FpsPluginCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			// monkit
			if(cmd.getName().equalsIgnoreCase("kit")){
				ItemStack shotgun = new ItemStack(Material.DIAMOND_HOE, 1);
				ItemMeta shotgunM = shotgun.getItemMeta(); 
				
				ArrayList<String> description = new ArrayList<>();
				
				description.add(ChatColor.BLUE + "Shotgun");
				description.add("Diablement efficace en close combat !");
				
				shotgunM.setDisplayName("Shotgun");
				shotgunM.setLore(description);
				
				shotgun.setItemMeta(shotgunM);
				
				p.getInventory().addItem(shotgun);
				
				p.sendMessage(ChatColor.GREEN + "Vous venez de recevoir un putain de SHOTGUN!!!!"); 
			}
			
			// Mob custom
			if(cmd.getName().equalsIgnoreCase("zombie")){
				Zombie z = (Zombie)p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
				
				z.setCustomName("Mich Mich");
				z.setCustomNameVisible(true);
				
				// custom weapon
				z.getEquipment().setHelmet(new ItemStack(Material.TNT, 1));
				z.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
			
				// Add Potion effects
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 50));
				
				p.sendMessage(ChatColor.RED + "Attention !!!! Zombie !!!!"); 
			}
						
		}
		return false;
	}

}
