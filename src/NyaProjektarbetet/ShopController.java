package NyaProjektarbetet;


import javax.swing.JOptionPane;

public class ShopController {
    private PanelSklett view;
    private GameEngine engine;

    ShopController(GameEngine engine, PanelSklett view) {
        this.view  = view;		//view är alltså panelskelettet
        this.engine  = engine;	//via engine kommer man åt shop, som är model
    }
    
    public void buyControl(String value, String clickedItem){
	
    	if( isInteger(value) ){		//Om man skrivit in siffror, fortsätter med kontrollen
    		JOptionPane.showMessageDialog(null, "Du vill köpa " + value + " stycken.", "KÖP", JOptionPane.OK_CANCEL_OPTION);
    		moneyControl(value, clickedItem);
    	}
    	else if (value == null){ 	//Om insträngen är null
    		JOptionPane.showMessageDialog(null, "Skriv in ett antal du vill köpa!", "KÖP", JOptionPane.OK_CANCEL_OPTION);
    	}
    	else{ 	//Om man skrivit in något annat än siffror
    		JOptionPane.showMessageDialog(null, "Du måste skriva in siffror!", "KÖP", JOptionPane.OK_CANCEL_OPTION);
    	}
    }
    
    public void moneyControl(String value, String clickedItem){
    	
    	Item boughtItem = new Item(0, 1, "BrickBlue.png", "null"); //skapa temporärt föremål som skrivs över
    	
    	for(Item item : engine.shop.getShopItems().keySet() ){ //letar upp föremålet med rätt namn
    		if(clickedItem == item.getItemName()){
    			boughtItem = item;
    		}
    	}
    	
    	engine.shop.calculatePrice(boughtItem, Integer.parseInt(value));	//anropar köpmetoderna i shop
    	
    }
    
	public static boolean isInteger(String s) {	//kollar om insträng är en integer
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true; 	    // man kommer bara hit om man inte redan returnerat false
	}

    /* -----------Man kan även göra inre klasser som implementerar actionlistener. Vore detta mer korrekt?
    class MultiplyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = "";
            try {
                userInput = view.getUserInput();
                model.multiplyBy(userInput);
                view.setTotal(model.getValue());

            } catch (NumberFormatException nfex) {
            	view.showError("Bad input: '" + userInput + "'");
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	model.reset();
        	view.reset();
        }
    }// end inner class ClearListener
     */
}
