package fr.philipid3s.FpsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class Bordure {
	
	public Bordure(){
		
	}
	
	public static void setDefault(){
		
		// on set les bordures sur tous les mondes
		
		for (World wld : Bukkit.getWorlds()){
			WorldBorder wb = wld.getWorldBorder();
			wb.setCenter(0, 0);
			wb.setSize(1000); // bordure a 1000 blocs
			
			// on set warning distance
			wb.setWarningDistance(10); // a 10 blocs de la bordure
		}
		
	}
}
