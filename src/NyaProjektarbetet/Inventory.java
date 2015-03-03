package NyaProjektarbetet;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * Just nu innehåller inventory en hashmap med bluebrick o redbrick bara
 * För att testa detta kan man köra den bortkommenterade raden
 *
 */

public class Inventory implements Serializable {
	private HashMap<Item, Integer> items;
	
	public Inventory() {
		items = new HashMap<Item, Integer>();
		createInventory();
	}
	
	public HashMap<Item, Integer> getInventory() {
		return items;
	}
	
	public void updateInventory(Item item, Integer amount) {
		int total = (items.get(item)) + amount;
		items.put(item, total);
	}
	
	private void createInventory(){
		//******* Lägg in nya items så att de med HÖGST level läggs till FÖRST med items.put! 
		//(Detta för tt de ska hamna i rätt ordning i shop)
		
		Item blueBrick = new Item(100, 1, "BrickBlue.png", "Blå tegelsten");
		Item redBrick = new Item(200, 1, "BrickRed.png", "Röd tegelsten");
		Item fishBrick = new Item(200, 2, "fish.png", "Fisksten");
		items.put(fishBrick, 0);
		items.put(redBrick, 0);
		items.put(blueBrick, 0);
		//JOptionPane.showMessageDialog(gui.myFrame(), items.get(blueBrick), "", JOptionPane.INFORMATION_MESSAGE);
	}
	
}

	
	

