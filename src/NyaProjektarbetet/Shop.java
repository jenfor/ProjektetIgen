package NyaProjektarbetet;

import java.util.HashMap;

import javax.swing.JOptionPane;


public class Shop extends Room{
	private HashMap<Item, Boolean> shopItems; //Boolean = får spelaren köpa föremålet?
	private Player player;
	
	public Shop(HashMap<Item, Integer> inventory, Player player){
		shopItems = new HashMap<Item, Boolean>();
		this.player = player;

		
		for(Item item : inventory.keySet() ){	//Lägger in alla föremål i affären
			shopItems.put(item, false);			//Men sätter att inget kan köpas än
		}
		
		updateShop();
	}
	
	public HashMap<Item, Boolean> getShopItems(){
		return shopItems;
	}
	
	private void updateShop(){
		
		for(Item item : shopItems.keySet() ){				//Går igenom alla föremål
			if(player.getLevel() >= item.getItemLevel()){	//Kollar om spelarens level >= föremålens level
				shopItems.put(item, true);					//Sätter att föremål får köpas om ens level är tillräckligt högt
			}
		}
		
	}
	
	public void calculatePrice(Item boughtItem, int numberOfItems){
		int price = 0;
		price = numberOfItems * boughtItem.getItemPrice();
		JOptionPane.showMessageDialog(null, "Det blir " + price + " kronor.", "", JOptionPane.OK_CANCEL_OPTION);
		
		if(player.getMoney() >= price){
    		JOptionPane.showMessageDialog(null, "Du har råd!", "", JOptionPane.OK_CANCEL_OPTION);
    		buy(boughtItem, numberOfItems);
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Du har inte råd!", "", JOptionPane.OK_CANCEL_OPTION);
    	}
	}
	
	public void buy(Item boughtItem, int numberOfItems){ 
		
		if ( shopItems.get(boughtItem) ){ 	//dvs om föremålet man klickat på är true = får köpas
			JOptionPane.showMessageDialog(null, "Dina pengar innan köp: " + player.getMoney(), "Pengar", JOptionPane.INFORMATION_MESSAGE);
			player.changeMoney((-1) * numberOfItems * boughtItem.getItemPrice()); 	//drar bort pengar från spelarens plånbok
			JOptionPane.showMessageDialog(null, "Dina pengar efter köp: " + player.getMoney(), "Pengar", JOptionPane.INFORMATION_MESSAGE);
			player.myInventory.updateInventory(boughtItem, numberOfItems); 	//lägger till rätt antal föremål i ryggsäcken
		}
		else{
			JOptionPane.showMessageDialog(null, "... men du har för lågt level.", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	
}
