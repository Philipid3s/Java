package fr.philipid3s.FpsPlugin;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Join implements Listener {
	
	static int task;
	static int timer = 30;
	
	
	// on va afficher dans la barre d xp es joueurs des infos relatives au timer
	public void setLevel(int timer){

		for(UUID uuid : FpsPlugin.getInstance().playerInGame){
			Player pl = Bukkit.getPlayer(uuid);


			pl.setLevel(timer);
		}
		
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			BlockState block = e.getClickedBlock().getState();
			if(block instanceof org.bukkit.block.Sign){
				org.bukkit.block.Sign sign = (org.bukkit.block.Sign)block;
				if(sign.getLine(0).equalsIgnoreCase("[start]")){
					
					//ON RECUPERE LE JOUEUR QUI SOUHAITE REJOINDRE LE JEU
			        final Player p = e.getPlayer();
			       
			        p.sendMessage("start !");
			        
			        //ON VERIFIE QUE LE JOUEUR NEST PAS DANS LA LISTE
			        if(!FpsPlugin.getInstance().playerInGame.contains(p.getUniqueId())){
			           
			            //SI IL NEST PAS DANS LA LISTE ON VA LAJOUTER
			        	FpsPlugin.getInstance().playerInGame.add(p.getUniqueId());
			            
			        	//ON VA CHECK SI LE NOMBRE DE JOUEUR EST EGAL A 1
			            if(FpsPlugin.getInstance().playerInGame.size() == 1){
			                //ON START LE CHRONOMETRE
			            	// Demarrer le chrono
			            	task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FpsPlugin.getInstance(), new Runnable(){
						 
			            		@Override
			            		public void run() {
			            			if(timer == 30){
			            				Bukkit.broadcastMessage("Le jeu commance dans 30 secondes !");
			            			}
			            			
			            			
			            			//ON ENLEVE 1 A CHAQUE SECONDE POUR QUE LE CHRONO PUISSENT BAISSER
			            			timer--;
			            			setLevel(timer);
			            			
			            			if(timer == 10){
			            				//LE JEU COMMENCE DANS 10 SEC
	                           
			            				for(UUID uuid : FpsPlugin.getInstance().playerInGame){
			            					Player pl = Bukkit.getPlayer(uuid);
			            					pl.sendMessage("Le jeu commance dans 10 secondes !");
			            				}
			            			}
			            			
			            			if(timer <= 10){
			            				Bukkit.broadcastMessage(Integer.toString(timer));
			            			}
	                       
			            			if(timer == 0){
			            				//ON ARRETE LE CHRONO
			            				Bukkit.getScheduler().cancelTask(task);
			            				
			            				// on lance la partie
			            				FPSGame.Start();
			            			}
	                       
			            		}
	                   
			            	},20,20);
			            }
			        }
				}
			}
		}
	}
}
