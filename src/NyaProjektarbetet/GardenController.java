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
	* @param  	in  inventory whit the latest changes		              
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
	* getIcon - gets the picture saved at a specific place in garden..
	*
	* @param  	lopnr   the number of the specific button where a picture might be saved.
	* 	
	* @return			a string that represents the picture saved at this position.                
	*/
	public String getIcon(int lopnr)
	{
		return engine.garden.getGardenIcon(lopnr);
	}
	
	
	/**
	* remove - removes an Itempicture from a specific button in the garden where it was saved
	* 		   and informs the present inventory of the change.
	*
	* @param  lopnr   the number of the specific button where the picture was saved.
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
	* take - removes an Itempicture from a specific button in the garden where it was saved
	* 		   and informs the present inventory of the change.
	*
	* @param  lopnr   the number of the specific button where the picture was saved.
	*
	*/
	public void take(String imageOfItem)
	{
		takenImage = imageOfItem;
	}
	
	
	public String getTakenImage()
	{
		//System.out.println("Bilden som följde med till GardenControllers getTakenItem är: : " +takenImage );
		for(Item i: inventory.keySet())
		{
			if(i.getItemPicture().equals(takenImage))
			{
				//if(inventory.get(takenImage)!=0 /*&& inventory.get(i/*takenImage*/)/*!=null)*/
				//{
					if(inventory.get(i/*takenImage*/)!=null)
						if(inventory.get(i) > 0 )
						{
							return takenImage;
						}
				//}
			    
			}
		}
		
			    /*else*/
		//return null;
		return null;
		
	}
	
	public void build(int lopnr)
	{
		//System.out.println("Inventory i PanelSklett: " + engine.userInventory.getInventory());
        //System.out.println("Inventory i GardenController: " + inventory);
        
		if(takenImage != null)
		{
			//System.out.println("takenItem är: : " +takenItem );
			//System.out.println("icon är: : " +icon );
			engine.garden.addItem(lopnr, takenImage);
			//System.out.println("Nu är vi i build"+ lopnr);
			
			
			//Minska värdet på key:n ImageIcon med 1
			for(Item i: inventory.keySet())
			{
				if(i.getItemPicture().equals(takenImage))
				{
					
				    //int nr = 0;
				    /*if(inventory.containsKey(takenImage)){*/
					//nr =inventory.get(i/*takenImage*/);
					//nr--;
					//setChanged();
					//notifyObservers(nr); 
					//inventory.put(i/*takenImage*/, nr);
					
					engine.userInventory.updateInventory(i, -1); //-----------------Linns experiment
				}
		    }
			
			
		
		}	
	}
}
