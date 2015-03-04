package NyaProjektarbetet;

import java.io.Serializable;
import java.util.HashMap;


/**
 * This class is the basclass for the rooms.
 * 
 * @author 
 * @version 2015-03-04
 */

public class Room implements Serializable{
		
	public Room()
	{
	}
	
	
	
	/**
	* getPicture - gets a backgroundpicture.
	*
	* @param  current 	the name of the the current room	
	* @return			a string that represents a backgroundpicture.                
	*/	
	public String getPicture(String current)
	{
		if(current.equals("center"))
		{
			return "pictures/stig.jpg";
		}
			
		else 
		{
			return "pictures/sno.jpg";
		}
		
	}
	
	/**
	* add - in only used in garden. 
	*
	* @param  lopnr   the position of a specific place in the garden.
	* @param  icon    a string that represents a picture of a brick.
	* 
	*/
	public void addItem(int lopnr, String icon)
	{
		
	}
	
		
	/**
	* removeItem - in only used in garden.
	*
	* @param  lopnr   the position of a specific place in the garden.
	*
	*/
	public void removeItem(int lopnr)
	{
		
	}
	
	/**
	* getGardenIcon - is only used in garden garden.
	*
	* @param  	lopnr   the position of a specific place in the garden.
	* @return			a string that represents the picture saved at this position.                
	*/
	public String getGardenIcon(int lopnr)
	{
		return null;
	}
	
	
	
	/**
	* getGardenItems - is used in garden.
	*
	* @return  	a hasmap where strings representing pictures are saved whit specific numbers of the buttons where they are saved. 
	*       
	*/	
	public HashMap<Integer, String> getGardenItems()
	{
		return null;
	}
	    

}


