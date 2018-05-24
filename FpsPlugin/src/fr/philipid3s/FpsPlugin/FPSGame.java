package fr.philipid3s.FpsPlugin;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class FPSGame {
	
	public static int timer = 1800; // 30 min , 3600 60min
	public static int task;
	
	@SuppressWarnings("deprecation")
	public static void Start() {

		Bukkit.broadcastMessage(ChatColor.RED + "GO!");
		
		Boolean bEntitiesInvoked = false;
		
		// on donne effets de potions + kits aux joueurs connectes
		for(UUID uuid : FpsPlugin.getInstance().playerInGame){
			Player pl = Bukkit.getPlayer(uuid);

			// to be done one time only
			if (bEntitiesInvoked == true){
				bEntitiesInvoked = true;
				
				Location loc = new Location(pl.getWorld(), -186,67,97);
				Zombie bob = pl.getWorld().spawn(loc, Zombie.class);
				
				bob.setCustomName(ChatColor.RED + "Bob");
				bob.setCustomNameVisible(true);
			}

			// on lance la boucle principale du jeu
			task = Bukkit.getScheduler().scheduleAsyncRepeatingTask(FpsPlugin.getInstance(), new Runnable(){
				
				
				public void run(){
					timer--;
					
					if (timer==0){
						// fin du jeu
					}
				}
				
			}, 20, 20);
			// ... s
		}
	}

}
