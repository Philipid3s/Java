package fr.philipid3s.FpsPlugin;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.philipid3s.FpsPlugin.EffetDeSang;
import fr.philipid3s.FpsPlugin.Guns;
import fr.philipid3s.FpsPlugin.FpsPluginCommand;

public class FpsPlugin extends JavaPlugin implements Listener {
	
	public static FpsPlugin instance;
	
	public static FpsPlugin getInstance(){
		return instance;
	}
	
	//IL SAGIT DE LA LISTE DES JOUEURS EN JEU
	public ArrayList<UUID> playerInGame = new ArrayList<>();
    
	public void onEnable() {
		instance = this;
		
		// Register Commands
		System.out.println("FPS Plugin > Register Commands");
		getCommand("kit").setExecutor(new FpsPluginCommand());
		getCommand("zombie").setExecutor(new FpsPluginCommand());
		
		// Register Events
		System.out.println("FPS Plugin > Register Events");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EffetDeSang(), this);
		pm.registerEvents(new Guns(), this);
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new Artifice(), this);
		pm.registerEvents(new SpeedRecipes(), this);
		pm.registerEvents(new BlockDrop(), this);
				
		pm.registerEvents(new uhcPVP(), this);
		
		// Register new Crafts
		System.out.println("FPS Plugin > Register Crafts");
		
		// custom craft : explosive minecart
		// ShapedRecipe recipe1 = new ShapedRecipe(new ItemStack(Material.EXPLOSIVE_MINECART, 10));
		@SuppressWarnings("deprecation")
		ShapedRecipe recipe1 = new ShapedRecipe(new ItemStack(Material.EXPLOSIVE_MINECART, 10));
		
		recipe1.shape(new String[] {" s ", "s s", " s "});
		recipe1.setIngredient('s',  Material.SAND);
		getServer().addRecipe(recipe1);
		
		// Configuration
		getConfig().options().copyDefaults(true);
		saveConfig();
				
		// set default borders
		Bordure.setDefault();
						
		System.out.println("Mon premier plugin > ACTIVE");
	}
}
