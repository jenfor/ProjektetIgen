package NyaProjektarbetet;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This class saves the specific things that makes the garden.
 * 
 * @author Jenny Forsberg
 * @version 2015-03-04
 */

public class Garden extends Room implements Serializable{
	
	private HashMap<Integer, String> gardenItems;  
	private String gardenPicture;
	
	
	public Garden(){
		gardenItems = new HashMap<Integer, String>();
		gardenPicture="pictures/strand.jpg"; 
		}
	
	
	/**
	* getGardenItems - gets all the pictures and their places in garden .
	*
	* @return  	a hasmap where strings representing pictures are saved whit specific numbers of the buttons where they are saved. 
	*       
	*/	
	public HashMap<Integer, String> getGardenItems(){
		return gardenItems;
	}
	
		
	/**
	* add - adds an Itempicture whit the number of a specific button in garden where it will be saved .
	*
	* @param  lopnr   the number of the specific button where the picture will be saved.
	* @param  icon    a string which represents the picture that will be saved here.
	* 
	*/
	public void addItem(int lopnr, String icon){
			gardenItems.put(lopnr, icon); 
	}
	
		
	/**
	* removeItem - removes an Itempicture from a specific button in garden where it was saved .
	*
	* @param  lopnr   the number of the specific button where the picture was saved.
	*
	*/
	public void removeItem(int lopnr){
			gardenItems.remove(lopnr); 
	}
	
	
	/**
	* getGardenitem - gets the picture saved at a specific place in garden.
	*
	* @param  	lopnr   the number of the specific button where a picture might be saved.
	* @return			a string that represents the picture saved at this position.                
	*/
	public String getGardenIcon(int lopnr){
		
		return  gardenItems.get(lopnr);
	}
	
	
	/**
	* getPicture - gets the backgroundpicture for garden.
	*
	* @param  	current the name of the the current room	
	* @return			a string that represents the backgroundpicture for garden.                
	*/
	public String getPicture(String current)
	{
		return gardenPicture;
	}
	
	
}
