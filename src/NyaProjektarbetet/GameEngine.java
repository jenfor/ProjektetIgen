package NyaProjektarbetet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;

/**
 * Den här klassen skapar rum.
 * Return: myFrame och currentRoom med hjälp av två public metoder.
 */
public class GameEngine {
	private Player user;
	private UserInterface gui;
	//***Current room:
	private String current;
	//***Rum:
	public Shop shop;
	public Room center, garden, minigame1;
	
	//public GardenController gardenController;		//tror inte denna behövs här?
	public Inventory userInventory;
	
	
	
	public static class State implements Serializable{
		//Player, Garden, Inventory måste vara Serializable för att kunna skrivas till fil
		Player player = new Player();
		Room gard = new Garden();
		Inventory inven = new Inventory();
		
		public Player getSavedPlayer(){
			return player;
		}
		public void setStatePlayer(Player user){
			player = user;
		}
		
		public Room getSavedGarden(){
			return gard;
		}
		public void setStateGarden(Room garden){
			gard = garden;
		}
		
		public Inventory getSavedInventory(){
			return inven;
		}
		public void setStateInventory(Inventory inventory){
			inven = inventory;
		}
		

	}
	public State gameState;
	
	public GameEngine() {
		
		gameState = new State();
		userInventory = new Inventory();
		gui = new UserInterface(this);
		gui.gameStart();
		user = new Player();
		user.setInventory(userInventory);
		
		
	}


	    	public void save(){
	    	System.out.println("" + gameState.getSavedPlayer().getMoney());
	    	gameState.setStatePlayer(user);
	    	gameState.setStateGarden(garden);
	    	gameState.setStateInventory(userInventory);
    		try{
    			FileOutputStream saveFile = new FileOutputStream( "saves/" + gameState.player.getUserName() + ".sav" );
    			ObjectOutputStream save = new ObjectOutputStream( saveFile );

    			save.writeObject( gameState.player );
    			save.writeObject( gameState.gard );
    			save.writeObject( gameState.inven );
    			
    			saveFile.close();
    			save.close();
    		}
    			
    		catch(Exception e){
    			e.printStackTrace();
    			JOptionPane.showMessageDialog( null, "Hoppsan, något gick fel vid sparandet!","Spara",JOptionPane.OK_CANCEL_OPTION);
    		}

    	}
    	
	    	
    	public void load(){
    		try{
    			FileInputStream saveFile = new FileInputStream( "saves/" + gameState.player.getUserName() + ".sav" );
    			ObjectInputStream load = new ObjectInputStream( saveFile );

    			gameState.player = (Player) load.readObject();
    			gameState.gard = (Garden) load.readObject();
    			gameState.inven = (Inventory) load.readObject();
    			
    			saveFile.close();
    			load.close();
    			
    			if( !(gameState.getSavedPlayer().equals(user)) ){
    				user = gameState.getSavedPlayer();
    				garden = gameState.getSavedGarden();
    				userInventory = gameState.getSavedInventory();
    				JOptionPane.showMessageDialog( null, "Din sparfil har laddats in!","Ladda",JOptionPane.OK_CANCEL_OPTION);
    			}
    			else{
    				
    			}
    			
    		}
    		
    		catch(Exception e){
    			JOptionPane.showMessageDialog( null, "Du är en ny spelare!","Ny spelare",JOptionPane.OK_CANCEL_OPTION);
    		}

    	}
	
	public String getCurrent() {
		return current;
	}
	
	
	public Player getPlayer(){
		return user;
	}
	

	public void setCurrent(String room) {
		current = room;
	}
	
	
	public void printWelcome() {
		String name;
		JOptionPane.showMessageDialog(gui.myFrame(), "Välkommen till vårt spel!!", "", JOptionPane.INFORMATION_MESSAGE);
		name = JOptionPane.showInputDialog(gui.myFrame(), "Vad är ditt namn?", "", JOptionPane.QUESTION_MESSAGE);
		
		stateSetup(name);
		
		
		
	}
	
	private void createRooms() {
      
        center = new Room();
        garden = gameState.getSavedGarden();
        minigame1 = new Room();
        shop = new Shop(userInventory.getInventory(), user);
        
        current = "center";		//startar spelet i centrum
    }
	
	
	 public void changeRoom(String current)
	 {
		 Room room; //tillfälligt rum för rumsbyte
		 
		 setCurrent(current);
		 if(current.equals("center")) room = center; 
		 else if(current.equals("shop")) room = shop;
		 else if(current.equals("garden")) room = garden;
		 else room = minigame1;
		 System.out.println(current);
		 gui.setJPanelWithBackground(room.getPicture(current));
	 }
	 
	 
	 public void stateSetup(String name){
		 	user.setUserName(name);
			gameState.setStatePlayer(user);
			
			System.out.println("" + gameState.getSavedPlayer().getUserName());
			load();	
			
			createRooms();
	 }
	
}
