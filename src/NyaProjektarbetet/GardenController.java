package NyaProjektarbetet;

import java.awt.Image;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This class communicates between Garden, Inventory and RoomPanel.
 * 
 * @author Jenny Forsberg
 * @version 2015-03-04
 */

public class GardenController extends Observable{
	
	
	String takenImage;
	GameEngine engine;
	HashMap<Item, Integer> inventory;	
		
	public GardenController(Inventory in, GameEngine engine)
	{
		inventory = in.getInventory(); 
		this.engine  = engine;
	}
	

	/**
	* setInventory - updates GardenControllers inventory.
	*
	* @param  	in  	inventory whit the latest changes
	* 		              
	*/
	public void setInventory(Inventory in){
		inventory = in.getInventory(); 
	}
	

	/**
	* getInventory - gets the inventory saved in gardenController.
	*	
	* @return		a hashmap with the items and the present number of them.                
	*/
	public HashMap< Item,Integer> getInventory()
	{
		return inventory;
	}
	

	/**
	* getIcon - gets the image of a brick saved at a specific place in the garden.
	*
	* @param  	lopnr   the position of a specific place in the garden.
	* 	 
	* @return			a string that represents an image of a brick.                
	*/
	
	public String getIcon(int lopnr)
	{
		return engine.garden.getGardenIcon(lopnr);
	}
	
	
	/**
	* remove - removes the image of a brick from a specific place in the garden
	* 		   and informs the present inventory of the change.
	*
	* @param  lopnr   the position of a specific place in the garden.
	*
	*/
	public void remove(int lopnr)
	{
		String icon = engine.garden.getGardenIcon(lopnr);
		engine.garden.removeItem(lopnr);
		
		for(Item i: inventory.keySet())
		{
			if(i.getItemPicture().equals(icon))
			{
				engine.userInventory.updateInventory(i, 1);
			}
		
		 }
	 }
	
	
	/**
	* take - remembers what image of a brick  the user has taken.
	*
	* @param  imageOfItem   a string that represents the taken image of a brick.
	*
	*/
	public void take(String imageOfItem)
	{
		takenImage = imageOfItem;
	}
	
	
	/**
	* getTakenImage - gets the taken image of a brick if it's allowed.
	*
	* @return 	a string that represents the taken image of a brick or null.
	*
	*/
	public String getTakenImage()
	{
		for(Item i: inventory.keySet())
		{
			if(i.getItemPicture().equals(takenImage))
			{
				if(inventory.get(i)!=null)
				{
					if(inventory.get(i) > 0 )
					{
						return takenImage;
					}
				}
			}
		}
		
		return null;
	}
	
	
	/**
	* build - saves the taken image of a brick at a specific place in the garden
	* 		  and informs the present inventory of the change.
	*
	* @param  	lopnr   the position of a specific place in the garden.
	* 	                 
	*/
	public void build(int lopnr)
	{
		if(takenImage != null)
		{
			engine.garden.addItem(lopnr, takenImage);
			
			for(Item i: inventory.keySet())
			{
				if(i.getItemPicture().equals(takenImage))
				{
					engine.userInventory.updateInventory(i, -1);
				}
		    }
		}
	}
}
