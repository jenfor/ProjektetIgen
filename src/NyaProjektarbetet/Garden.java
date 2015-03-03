package NyaProjektarbetet;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Garden extends Room implements Serializable{
	
	public HashMap<Integer, String> gardenItems; //d�r Integer �r l�pnummer och String Items bild 
	
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
			gardenItems.put(lopnr, icon); //l�gger till iconbildens filnamn p� given position
			//System.out.println("Nu �r vi i Gardens addItem med l�pnr: "+ lopnr);
			//System.out.println("Detta �r vad som finns sparat i tr�dg�rden "+ gardenItems);
		}
		
		public void removeItem(int lopnr){
			gardenItems.remove(lopnr); //Tar bort bild fr�n plats
			//System.out.println("Nu �r vi i Gardens remove"+ lopnr);
		}
		
		public String getGardenIcon(int lopnr){
			 //H�mtar bilden till en speciell plats i Garden
			//System.out.println("Nu �r vi i Gardens getGardenIcon med l�pnr: "+ lopnr);
			//System.out.println("Ja det �r gardens getGardenIcon som k�r");
			//return "pictures/BrickBlue.png";
			return  gardenItems.get(lopnr);
			
			
		}
		
		@Override
		public String getPicture(String current)
		{
			return gardenPicture;
		}
		
		public Item getItem(){
			Item blueBrick = new Item(100, 1, "BrickBlue.png", "Bl� tegelsten");
			return blueBrick;
		}
}
