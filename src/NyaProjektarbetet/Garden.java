package NyaProjektarbetet;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Garden extends Room implements Serializable{
	
	public HashMap<Integer, String> gardenItems; //där Integer är löpnummer och String Items bild 
	
	//private Item gardenItem;
	private String gardenPicture;
	
	public Garden(){
		gardenItems = new HashMap<Integer, String>();
		gardenPicture="pictures/strand.jpg"; 
		}
	
	public HashMap<Integer, String> getGardenItems(){
		return gardenItems;
	}
	
		
		public void addItem(int lopnr, String icon){
			gardenItems.put(lopnr, icon); //lägger till iconbildens filnamn på given position
			//System.out.println("Nu är vi i Gardens addItem med löpnr: "+ lopnr);
			//System.out.println("Detta är vad som finns sparat i trädgården "+ gardenItems);
		}
		
		public void removeItem(int lopnr){
			gardenItems.remove(lopnr); //Tar bort bild från plats
			//System.out.println("Nu är vi i Gardens remove"+ lopnr);
		}
		
		public String getGardenIcon(int lopnr){
			 //Hämtar bilden till en speciell plats i Garden
			//System.out.println("Nu är vi i Gardens getGardenIcon med löpnr: "+ lopnr);
			//System.out.println("Ja det är gardens getGardenIcon som kör");
			//return "pictures/BrickBlue.png";
			return  gardenItems.get(lopnr);
			
			
		}
		
		@Override
		public String getPicture(String current)
		{
			return gardenPicture;
		}
		
		public Item getItem(){
			Item blueBrick = new Item(100, 1, "BrickBlue.png", "Blå tegelsten");
			return blueBrick;
		}
}
