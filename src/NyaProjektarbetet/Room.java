package NyaProjektarbetet;

import java.io.Serializable;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Room implements Serializable{
	//Tillfällig kod för att det ska kompilera
	//private PanelSklett jp;
	//private GameEngine e;
	//public int i;
	
	public Room()
	{
	}
	
	public Room(PanelSklett jp, GameEngine e)
	{
		//this.jp = jp;
		//this.e = e;
		
	}
	
	public String getPicture(String current)
	{
		if(current.equals("center"))
		{
			
			return "pictures/stig.jpg";
			
		}
		
		if(current.equals("shop"))
		{
			
			return "pictures/wood_shelves3.jpg";
			
		}
		
		else 
		{
			
			return "pictures/sno.jpg";
		}
		
	}
		
		
		public void addItem(int lopnr, String icon)
		{
			
		}
		
		public void removeItem(int lopnr)
		{
			
		}
		
		public String getGardenIcon(int lopnr)
		{
			return null;
		}
		
		public HashMap<Integer, String> getGardenItems(){
			return null;
		}
	    

		public Item getItem(){
			Item redBrick = new Item(100, 1, "BrickRed.png", "Röd tegelsten");
			return redBrick;
		}

	//Onödig!!
	/*public JPanel getRoomPanel(String current)
	//{
		if (jp != null)
		return jp.getPanel("Shop"/*e.getCurrent()*//*);
		/*
		else 
			return new JPanel();
	}
      */

}


