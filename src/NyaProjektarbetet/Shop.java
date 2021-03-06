package NyaProjektarbetet;

import java.util.HashMap;

import javax.swing.JOptionPane;


public class Shop extends Room{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Item, Boolean> shopItems; //Boolean = f�r spelaren k�pa f�rem�let?
	private Player player;
	String shopPicture;
	
	public Shop(HashMap<Item, Integer> inventory, Player player){
		shopItems = new HashMap<Item, Boolean>();
		this.player = player;
		shopPicture = "pictures/wood_shelves3.png";
		
		for(Item item : inventory.keySet() ){	//L�gger in alla f�rem�l i aff�ren
			shopItems.put(item, false);			//Men s�tter att inget kan k�pas �n
		}
		
		updateShop();
	}
	
	public HashMap<Item, Boolean> getShopItems(){
		return shopItems;
	}
	
	private void updateShop(){
		
		for(Item item : shopItems.keySet() ){				//G�r igenom alla f�rem�l
			if(player.getLevel() >= item.getItemLevel()){	//Kollar om spelarens level >= f�rem�lens level
				shopItems.put(item, true);					//S�tter att f�rem�l f�r k�pas om ens level �r tillr�ckligt h�gt
			}
		}
		
	}
	
	public void calculatePrice(Item boughtItem, int numberOfItems){
		int price = 0;
		price = numberOfItems * boughtItem.getItemPrice();
		JOptionPane.showMessageDialog(null, "Det blir " + price + " kronor.", "", JOptionPane.OK_CANCEL_OPTION);
		
		if(player.getMoney() >= price){
    		buy(boughtItem, numberOfItems);
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Du har inte r�d!", "", JOptionPane.OK_CANCEL_OPTION);
    	}
	}
	
	public void buy(Item boughtItem, int numberOfItems){ 
		
		if ( shopItems.get(boughtItem) ){ 	//dvs om f�rem�let man klickat p� �r true = f�r k�pas
			player.changeMoney((-1) * numberOfItems * boughtItem.getItemPrice()); 	//drar bort pengar fr�n spelarens pl�nbok
			player.myInventory.updateInventory(boughtItem, numberOfItems); 	//l�gger till r�tt antal f�rem�l i ryggs�cken
		}
		else{
			JOptionPane.showMessageDialog(null, "... men du har f�r l�gt level.", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public String getPicture(String current)
	{
		return shopPicture;
	}
	

	
}
