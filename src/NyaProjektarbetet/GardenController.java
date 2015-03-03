package NyaProjektarbetet;

import java.awt.Image;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class GardenController extends Observable{
	
	//Room garden;
	ImageIcon itemIcon;
	int nrOfItem;
	String takenImage;
	GameEngine engine;
	//HashMap<ImageIcon, Integer> inventory = fuskaFramHashMap();// Tillfällig  map när jag jobbar utan inventory Icon ska egentligen var Item
	HashMap<Item, Integer> inventory;	
	//Room gardenThis;
	
	public GardenController(Inventory in, GameEngine engine)
	{
		inventory = in.getInventory(); 
		//gardenThis = engine.garden;
		this.engine  = engine;
			
	}
	
	public void setInventory(Inventory in){
		inventory = in.getInventory(); 
	}
	
	
		
	public HashMap</*ImageIcon, */ Item,Integer> getInventory()
	{
		//System.out.println("( metod getInventory: Detta är vad som finns sparat i inventory nu:  "+ inventory);
		return inventory;
		
	
	}
	

	
	public String getIcon(int lopnr)
	{
		
		
		if(engine.garden.getGardenIcon(lopnr) != null){
			return engine.garden.getGardenIcon(lopnr);
			//return "pictures/BrickBlue.png";
			}
		else{
			return null;
			//return "pictures/BrickBlue.png";
		}
		
		//return engine.garden.getGardenIcon(lopnr);
		//return "pictures/BrickBlue.png";
	}
	
	
	
	
	public void remove(int lopnr)
	{
		String icon = engine.garden.getGardenIcon(lopnr);
		engine.garden.removeItem(lopnr);
		//System.out.println("Nu är vi i remove"+ lopnr);
		//if(inventory.containsKey(icon))
		//{
		for(Item i: inventory.keySet())
		{
			if(i.getItemPicture().equals(icon))
			{
				
				int nr = inventory.get(i/*icon*/);	
				nr++;
				setChanged();
				notifyObservers(nr);
				inventory.put(i/*icon*/, nr);
				
				//engine.userInventory.updateInventory(i, 1); //---------------------Linns experiment
			}
		//}
		//System.out.println("Detta är vad som finns sparat i inventory efter att " +icon+" har plockats tillbaka:  "+ inventory);
		 }
	 }
	
	public void take(String imageOfItem)
	{
		takenImage = imageOfItem;
		//System.out.println("Bilden som följde med till GardenControllers take är: " +takenImage );
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
					
				    int nr = 0;
				    /*if(inventory.containsKey(takenImage)){*/
					nr =inventory.get(i/*takenImage*/);
					nr--;
					setChanged();
					notifyObservers(nr); 
					inventory.put(i/*takenImage*/, nr);
					
					//engine.userInventory.updateInventory(i, -1); //-----------------Linns experiment
				}
		    }
			
			
		
		}	
	}
	
	
	private HashMap<ImageIcon, Integer> fuskaFramHashMap()
	{
		HashMap<ImageIcon, Integer> inventoryFusk = new HashMap<ImageIcon, Integer>();
		
		ImageIcon i = new ImageIcon("pictures/skylt.png");
		inventoryFusk.put(i, 100);
		ImageIcon j = new ImageIcon("pictures/exitskylt.png");
		inventoryFusk.put(j, 100);
		ImageIcon k = new ImageIcon("pictures/fonster.png");
		inventoryFusk.put(k, 100);
		ImageIcon l = new ImageIcon("pictures/flowe.png");
		inventoryFusk.put(l, 100);
		ImageIcon m = new ImageIcon("pictures/pyramid.png");
		inventoryFusk.put(m, 100);
		ImageIcon n = new ImageIcon("pictures/redbrick.jpg");
		inventoryFusk.put(n, 100);
		ImageIcon q = new ImageIcon("pictures/bluebrick.png");
		inventoryFusk.put(q, 100);
		
		 Image o = n.getImage() ;  
		   Image newimg = o.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
		   ImageIcon icon = new ImageIcon( newimg );
		   inventoryFusk.put(icon, 25);
		   
					
		Image p = k.getImage() ;  
			 Image newimg1 = p.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
			 ImageIcon icon1 = new ImageIcon( newimg1 );
			 inventoryFusk.put(icon1, 25);
			     
		Image r = q.getImage() ;  
			 Image newimg2 = r.getScaledInstance( 55, 55,  java.awt.Image.SCALE_SMOOTH ) ;  
			 ImageIcon icon2 = new ImageIcon( newimg2 );
			 inventoryFusk.put(icon2, 25);
		   
		return inventoryFusk;
		}
	
}
