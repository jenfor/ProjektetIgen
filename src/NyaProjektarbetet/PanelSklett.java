package NyaProjektarbetet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.Toolkit;






//import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * This class makes different panels for different rooms.
 * 
 * @author 
 * @version 2015-03-04
 */


public class PanelSklett implements Observer{
	private GameEngine engine;
	private JPanel panelClickable;
	private ArrayList<JButton> itemButtons = new ArrayList<JButton>();
	private int presentItem; 
	private HashMap<Integer, JButton> panelButtons = new  HashMap<Integer, JButton>();
	private HashMap<Item, JButton> panelButtons2 = new  HashMap<Item, JButton>();
	private HashMap<String, Integer> rightPanel = new  HashMap<String, Integer>();
	

	private ShopController shopControl;
	public GardenController gardenController;
	//public Inventory in = new Inventory();
	//public JButton itemsLeft = null;
	public JButton itemsLeft2;
	
	
	
	public PanelSklett(GameEngine e, UserInterface ui)
	{
		engine = e;
		//this.ui = ui;
		/*center = new Center();		//har flyttat dessa till gameengine istället
		garden = new Garden();		
		miniGame1 = new MiniGame();	*/
		shopControl = new ShopController(engine, this);
		/*
		center = new Center();
		shop = new Shop();
		garden = new Garden();
		miniGame1 = new MiniGame();*/

		gardenController = new GardenController(e.userInventory, engine);
		gardenController.addObserver(this);
		//engine.userInventory.addObserver(this);
		
		//engine.getPlayer().myInventory
	}
	
	/*
	private JPanel getInventoryPanel()
	{
		panelClickable = createInventoryPanel();
		return panelClickable;
		
	}*/
	
	/*
	 private void reSize(int windowchanges)
	 {
		 
	 }*/
	
	public JPanel getPanel(String current)
	{
		if(current.equals("center")) panelClickable = createCenterPanel();
		else if(current.equals("shop")) panelClickable = createShopPanel();
		else if(current.equals("garden")) panelClickable = createGardenPanel();
		//else panelClickable = createMiniGamePanel();
		
		return panelClickable;
	}
	
	private JPanel createCenterPanel()
	{
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.setSize( screenSize.getWidth() , (screenSize.getHeight() - 30) ); //-30 kompenserar för windows-menybaren
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        Font font = new Font("Viner Hand ITC", Font.BOLD, 25);
        
        
        
	   	JPanel panel = new JPanel();    
	    panel.setOpaque(false);
	    //panel.setLayout(new GridLayout(4,4));
	    panel.setLayout(null);
	    

	    //Tar in skyltbilden, skalar om den, sparar som newIcon
    	URL imageURL = this.getClass().getClassLoader().getResource("skylt.png");
    	ImageIcon icon = new ImageIcon(imageURL);
	    Image img = icon.getImage();
	    Image newimg = img.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH);
	    ImageIcon newIcon = new ImageIcon(newimg);
	    
	    JButton clickButton = new JButton ("Affär", newIcon);
	    
	    clickButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//ui.changeRoom("shop");					//strängar ist. för rumsreferenser
				//engine.changeCurrentRoom(engine.shop);	//rumsreferenser ist. för strängar
				engine.changeRoom("shop");				//flyttat till engine
				
			}
		});
	    clickButton.setHorizontalTextPosition(JButton.CENTER);
	    clickButton.setVerticalTextPosition(JButton.CENTER);
	    clickButton.setBounds((int)(width * 0.15),(int)(height * 0.2),(int)(width * 0.15),(int)(height * 0.15));//x,y,width,height
	    clickButton.setContentAreaFilled(false);
	    clickButton.setFont(font);
	    clickButton.setForeground(Color.white); 		//färg på skyltens text
	    clickButton.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton);
	    
	    JButton clickButton2 = new JButton ("Trädgård", newIcon);
	    
	    clickButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//ui.changeRoom("garden");					//strängar ist. för rumsreferenser
				//engine.changeCurrentRoom(engine.garden);	//rumsreferenser ist. för strängar
				engine.changeRoom("garden");				//flyttat till engine
				
			}
		});
	    clickButton2.setHorizontalTextPosition(JButton.CENTER);
	    clickButton2.setVerticalTextPosition(JButton.CENTER);
	    clickButton2.setBounds((int)(width * 0.60),(int)(height * 0.70),(int)(width * 0.15),(int)(height * 0.15));//x,y,width,height
	    clickButton2.setContentAreaFilled(false);
	    clickButton2.setFont(font);
	    clickButton2.setForeground(Color.white); 		//färg på skyltens text
	    clickButton2.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton2);
	    
	    JButton clickButton3 = new JButton ("Minispel", newIcon);
	    
	    clickButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MiniGame();
			}
		});
	    clickButton3.setBounds((int)(width * 0.80),(int)(height * 0.20),(int)(width * 0.15),(int)(height * 0.15));//x,y,width,height
	    clickButton3.setContentAreaFilled(false);
	    clickButton3.setFont(font);
	    clickButton3.setForeground(Color.white); 		//färg på skyltens text
	    clickButton3.setHorizontalTextPosition(JButton.CENTER);
	    clickButton3.setVerticalTextPosition(JButton.CENTER);
	    clickButton3.setBorderPainted(false); //med eller utan kant
	    panel.add(clickButton3);
	    
	    return panel;
    
    
	}
	
//*************************************************************************************************
//*************************************SHOP-PANEL START*********************************************
//*************************************************************************************************
	private JPanel createShopPanel()
	{
		HashMap<Item, Boolean> shopItems = engine.shop.getShopItems();
		
		//**************************Skapa paneler**************************
		JPanel panel = new JPanel();    
		JPanel gridPanel = new JPanel(); 
		JPanel downPanel = new JPanel(); 
		
	    panel.setOpaque(false);
	    gridPanel.setOpaque(false);
	    downPanel.setOpaque(false);
	    
	    panel.setLayout(new BorderLayout());
	    downPanel.setLayout(new GridLayout(1,10));
	    gridPanel.setLayout(new GridLayout(5,5,40,20));
	    
	    downPanel.setPreferredSize(new Dimension(100, 100)); 
	    
	    
	    //**************************Skapa knappar**************************
	    //Ska lägga till funktion som gör oköpbara föremål typ grå eller liknande
	    
	    for(Item item : shopItems.keySet() ){ 
	    	
	    	ImageIcon icon;
	    	
	    	if (shopControl.levelControl(item)){
	    		URL imageURL = this.getClass().getClassLoader().getResource(item.getItemPicture());
		    	icon = new ImageIcon(imageURL);
	    	}
	    	else{
	    		URL imageURL = this.getClass().getClassLoader().getResource("cross_grey.png");
		    	icon = new ImageIcon(imageURL);
	    	}
	    	
	    	/*URL imageURL = this.getClass().getClassLoader().getResource(item.getItemPicture());
	    	ImageIcon icon = new ImageIcon(imageURL);*/
	    	Font font = new Font("Verdana", Font.BOLD, 12);
	    	JButton tempButton = new JButton (item.getItemName(), icon);
	    	tempButton.setFont(font);
	    	tempButton.setForeground(Color.white);
	    	
	    	JTextField itemText;
	    	
	    	if ( shopControl.levelControl(item)){
	    		itemText = new JTextField("Pris: " + item.getItemPrice() + " kr\n Level: " + item.getItemLevel());
	    	}
	    	else{
	    		itemText = new JTextField("Låses upp på level " + item.getItemLevel());
	    	}
	    	itemText.setHorizontalAlignment(JTextField.CENTER);
	    	itemText.setEditable(false);
	    	//itemText.setOpaque(true);	
	    	itemText.setOpaque(false);	
	    	itemText.setFont(font);
	    	//itemText.setBackground(Color.BLACK);
	    	itemText.setForeground(Color.white);
	    	itemText.setBorder(null);
	    	
	    	
	    	
	    	
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);
	    	
	    	JPanel itemGrid = new JPanel();
	    	itemGrid.setOpaque(false);
	    	itemGrid.setLayout(new GridLayout(2,1));
	    	
	    	itemGrid.add(tempButton);
	    	itemGrid.add(itemText);

	    	
	    	tempButton.addActionListener(new ActionListener()  {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String clickedItem = new String();			//Sträng för att spara namnet på det man klickat på
					for(JButton button : itemButtons){			//Letar igenom vilken knapp man klickat på
						if(arg0.getSource().equals(button)){	//När knappen hittats...
							clickedItem = button.getText();		//...så sparar man föremålets namn för den knappen
						}
					}
					String inputValue = JOptionPane.showInputDialog("Hur många vill du köpa?");
					shopControl.buyControl(inputValue, clickedItem);	//Startar controllern med föremålet samt antal
				}
			});
	    	
	    	//gridPanel.add(tempButton);	//lägger till knappen -------------------------------
	    	//gridPanel.add(tempLabel);-------------------------------
	    	
	    	gridPanel.add(itemGrid);
	    	itemButtons.add(tempButton); //sparar knappen i en arraylist för att man ska kunna leta igenom knapparna
		}
	    
	    
	  //**************************Skapa tomma knappar för resten av utrymmena**************************
	    int buffer = 25 - shopItems.size();
	    int j = 0;
	    for(j = 1; j <= buffer; j++ ){ //lägger till tomma knappar för att fylla skärmen
	    	//JButton tempButton = new JButton ("Item nr " + j);
	    	JButton tempButton = new JButton ();
	    	tempButton.setContentAreaFilled(false);
	    	tempButton.setBorderPainted(false);
	    	gridPanel.add(tempButton);
	    	itemButtons.add(tempButton); //för att kunna iterera genom knapparna, om det behövs senare...
		}
	    
	  //**************************Övriga menyknappar etc**************************
	    Font menuFont = new Font("Verdana", Font.BOLD, 16);
	    
	    /*
	    moneyLabel = new JButton ("Du har " + engine.getPlayer().getMoney() + " kr.");
	    moneyLabel.setContentAreaFilled(false);
	    moneyLabel.setFont(menuFont);
	    moneyLabel.setForeground(Color.white);
	    //centrumButton.setBounds(4,6,100,200);
	    //centrumButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(moneyLabel);*/
	    
	   
	    JButton centrumButton = new JButton ("Tillbaka till centrum");
	    centrumButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ui.changeRoom("center");
				//engine.changeCurrentRoom(engine.center);		//rumsreferenser ist. för strängar
				engine.changeRoom("center");				//flyttat till engine
				
			}
		});
	    centrumButton.setContentAreaFilled(false);
	    centrumButton.setFont(menuFont);
	    centrumButton.setForeground(Color.white);
	    //centrumButton.setBounds(4,6,100,200);
	    //centrumButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(centrumButton);
	    
	    JButton gardenButton = new JButton ("Till trädgården");
	    gardenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ui.changeRoom("garden");
				//engine.changeCurrentRoom(engine.garden);		//rumsreferenser ist. för strängar
				engine.changeRoom("garden");				//flyttat till engine
				
			}
		});
	    gardenButton.setFont(menuFont);
	    gardenButton.setForeground(Color.white);
	    gardenButton.setContentAreaFilled(false);
	    //gardenButton.setBounds(4,6,100,200);
	    //gardenButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(gardenButton);
	    
	    JButton inventoryButton = new JButton ("Ryggsäck");
	    inventoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createInventoryPanel();
				
			}
		});
	    inventoryButton.setContentAreaFilled(false);
	    inventoryButton.setFont(menuFont);
	    inventoryButton.setForeground(Color.white);
	    
	    //clickButton2.setBounds(300,400,200,200);
	    //clickButton.setBorderPainted(false); //med eller utan kant
	    downPanel.add(inventoryButton);
	    
	    panel.add(gridPanel, BorderLayout.CENTER);
	    panel.add(downPanel, BorderLayout.SOUTH);

	    return panel;
	}
//*************************************************************************************************
//*************************************SHOP-PANEL END***********************************************
//*************************************************************************************************
	
	
	private JPanel createGardenPanel()
	{
		Sound.playSomeSound("beachsummer_image.wav");
		engine.userInventory.addObserver(this);
		
		JPanel panel = new JPanel(); 
		panel.setOpaque(false);
	    panel.setLayout(null);
	    JPanel showInventory;
	    
	    int rad = 0;
	    int column =0;
	    int lopnr=0;
	    
	    //HashMap<Item, Integer> inventory = gardenController.getInventory();
	    
	    HashMap<Item, Integer> inventory = engine.userInventory.getInventory();
	    gardenController.setInventory(engine.userInventory);
	    
        Set<Entry<Item, Integer>> pairs = inventory.entrySet();
        
        for(Item item : inventory.keySet()){
			engine.userInventory.updateInventory(item, 0);
        }
        
	    
	    // Det osynlliga rutnätet med knappar
	    
	    for( int j=0;j<43;j++){    	
	    		    
		    for( int i=0;i<23;i++)
		    {
		    	lopnr++;// Varje knapp får ett eget nummer			    		    
			    final int nr = lopnr;
			    
			    String s = "buildable"; // Kom ihåg om det finns en tegelsten här eller inte...
			    if(gardenController.getIcon(nr)!= null)  s= "unbuildable";
			    final String startState = s;
			    
			    final ImageIcon icon3;
			    //ImageIcon icon3;
			    if (gardenController.getIcon(nr)!= null)
			    {
			    	//URL iconString = this.getClass().getClassLoader().getResource(gardenController.getIcon(nr));
			    	//URL iconString = this.getClass().getClassLoader().getResource("pictures/BrickBlue.png");
			    	//icon3 = new ImageIcon(gardenController.getIcon(nr));
			    	//System.out.println("gardenController.getIcon(nr) är just nu = " +gardenController.getIcon(nr));
			    	//icon3 = new ImageIcon(/*gardenController.getIcon(nr)*/"pictures/BrickBlue.png");
			    	//System.out.println(("pictures/" + gardenController.getIcon(nr)));
			    	icon3 = new ImageIcon("pictures/" + gardenController.getIcon(nr));
			    	
			    }
			    else
			    	icon3 =null;
			    //final ImageIcon icon3 = new ImageIcon(iconString);
			    
			    
			   /* if (this.getClass().getClassLoader().getResource(gardenController.getIcon(nr))!= null)
			    {
			    	iconString =this.getClass().getClassLoader().getResource(gardenController.getIcon(nr));
			    	icon3= new ImageIcon(iconString);
			    }
		    		    
			    else{  icon3 = null;}
			    */
			    //ImageIcon icon3 = new ImageIcon(gardenController.getIcon(nr));
		    	
		    				    
			    final JButton clickButton = new JButton(icon3);
			    //setResizable(false);// Testa senare
			    clickButton.setBounds(column,rad,30,30);
			    clickButton.setContentAreaFilled(false);//Osynlighet
			    clickButton.setBorderPainted(false);//Osynlighet
			    clickButton.addActionListener(new ActionListener() {
			    	
			    	String state = startState;
			    	String takenImageString;
			    	ImageIcon takenImage; 
			    	//String state ="buildable";
			    	@Override
					public void actionPerformed(ActionEvent arg0) {
			    		
			    		
						if(state.equals("buildable"))
						{
							takenImageString = gardenController.getTakenImage();
							takenImage = new ImageIcon("pictures/" +takenImageString);
							if(takenImage!=null)
							{
								state="unbuildable";
								//System.out.println("Bilden som följde med till Panelskletts rutnät är: : " +takenImage );
								if(gardenController.getTakenImage()!=null)
								{
									checkRightPanel(takenImageString);
									clickButton.setIcon(takenImage);
									gardenController.build(nr);
									takenImage = null;
								}
								
							}
														
						}
	
						else if(state.equals("unbuildable"))
					    {
							state="buildable";
							checkRightPanel(takenImageString);
							clickButton.setIcon(null);
							clickButton.setContentAreaFilled(false);
							gardenController.remove(nr);
						}
					}
					
			    });	
					
			    panel.add(clickButton);
			    
			    rad = rad + 30;
			    			   	    		    
		    }
		   
		    column = column + 30;
		    rad =0;
						   	    		    
		}
	    
	  //*************************************Panelen vid sidan av rutnätet********************************************************** 	
	    
	    showInventory = new JPanel();
	    showInventory.setLayout(null);
	    showInventory.setBounds(1279,0, 81, 1000);
	    
	    int buttonNr=0;
	    
	    // Nollställer för ny loop
	     rad = 30;
	     column = 30;
	   
	 
	        
	   
	        //Fixar fram det som ska visas på sidopanelen
	        for(Entry<Item, Integer> entry: pairs){
		        	buttonNr++;
		        final int bNr = buttonNr;
		    	// Visa bild på tillgänliga Item
		        final Item item= entry.getKey();	
		        
		        URL imageOfItemString = this.getClass().getClassLoader().getResource(item.getItemPicture());
		    	final ImageIcon imageOfItem= new ImageIcon(imageOfItemString);
		    	rightPanel.put(item.getItemPicture(), buttonNr);
		    	
		    	//final ImageIcon imageOfItem = entry.getKey();
		    	//System.out.println("imageOfItem i sidopanelen" +imageOfItem );
		    	final JButton showItem;
		    	showItem = new JButton(imageOfItem);
			    showItem.setBounds(column, rad,30,30);
			    
			    //Vad bildknappen ska göra
			    
			    showItem.addActionListener(new ActionListener() {
			    	    	
			    	@Override
					public void actionPerformed(ActionEvent arg0) {
												
							gardenController.take(item.getItemPicture());
							presentItem = bNr;
							//System.out.println("Tagen bild i sidopanelen är: " +imageOfItem );
					}
					
			    });	
			    
			    
			    
			    //Visa antal  tillgänliga Items
			    itemsLeft2 = new JButton();
			    itemsLeft2.setText("" + entry.getValue());
			    itemsLeft2.setBounds(column-2, rad+30,60,30);
			    itemsLeft2.setContentAreaFilled(false);//Osynlighet
			    itemsLeft2.setBorderPainted(false);//Osynlighet
			    showInventory.add(itemsLeft2);
			    
			    
			    // Visa antal  tillgänliga Items
			    /*int x = entry.getValue();
			    itemsLeft = new JButton();
			    itemsLeft.setText("" + x);
			    itemsLeft.setBounds(column-15, rad+30,60,30);
			    itemsLeft.setContentAreaFilled(false);//Osynlighet
			    itemsLeft.setBorderPainted(false);//Osynlighet*/
			    //panelButtons.put(buttonNr, itemsLeft);
			    //showInventory.add(itemsLeft);
			    
			    
			    
			    //Spara referens till just denna rutan
			    
			    panelButtons2.put(item, itemsLeft2);
			    
			     
			    //*****************************************************************
			    
			   		    
			    //Lägg till bild och antal 
			    showInventory.add(showItem);
			    
			    // Byt till ny position
			    rad= rad +60;
	    }
	        
	        		    
		   //*****************************************************************
		    
	     panel.add(showInventory);
	     return panel;
	     
	}
	
	
	public void checkRightPanel(String picture)
	{
		if(gardenController.getTakenImage()!=(null))
		{
			
		}
		if( rightPanel.containsKey(picture))
		{ 
			int nr = rightPanel.get(picture);
			if (presentItem!= nr)
			{
				presentItem = nr;
			}
			
		}
				
	
		
	}
	
	//*************************************************************************************************
	//*************************************INVENTORY-PANEL START***************************************
	//*************************************************************************************************
	
	
	//Ganska klart förutom layouten
		public void createInventoryPanel()
		{
			JFrame inventory = new JFrame("Ryggsäck");
			//HashMap<Item, Integer> items = inventoryItems.getInventory();
			HashMap<Item, Integer> items = engine.getPlayer().myInventory.getInventory();
			
			JPanelWithBackground invBG = new JPanelWithBackground("pictures/inventoryBG.jpg");
			inventory.add(invBG);
			invBG.setLayout(new GridLayout(4,1,1,1));
			
			JPanel panel = new JPanel();
		    panel.setOpaque(false);
		    panel.setLayout(new GridLayout(4,1));
		    invBG.add(panel);
			
		    invBG.add(new JLabel("Ryggsäck"));
			
			for (Entry<Item, Integer> entry : items.entrySet() ) {
			//for (HashMap.Entry<Item, Integer> entry : items.entrySet() ) {
			    //Item item = entry.getKey();
			    //Object amount = entry.getValue();
				//ImageIcon iconapprove = new ImageIcon(entry.getKey().getItemPicture());
				
				if(entry.getKey().getItemLevel() <= engine.getPlayer().getLevel()){
					URL imageURL = this.getClass().getClassLoader().getResource(entry.getKey().getItemPicture());
					ImageIcon iconapprove = new ImageIcon(imageURL);
					
					JLabel imglabelapprove1 = new JLabel(entry.getValue().toString());
				    imglabelapprove1.setIcon(iconapprove);
				    imglabelapprove1.setHorizontalAlignment(JLabel.CENTER);
				    invBG.add(imglabelapprove1);
				}
			}
			
			inventory.pack();
			inventory.setVisible(true);
		}
	
	
	//*************************************************************************************************
	//*************************************INVENTORY-PANEL END*****************************************
	//*************************************************************************************************
	
	
	/*
	private JPanel createMiniGamePanel()
	{
		JPanel panel = new JPanel();
		
		panel.setOpaque(false);
	    panel.setLayout(null);
		
		return panel;
		
	}
	*/
	public void update(Observable obj, Object arg)
	{
		Item temp; //-------
		temp = new Item(200, 2, "fish.png", "tomt item");
		
		/*if(obj instanceof GardenController && arg instanceof Integer)
		{
			panelButtons.get(presentItem).setText("" + arg);
			//itemsLeft.setText("" + arg);
			kurt.setText("kvar:"+ arg);
		}*/
		
		if ( obj instanceof Inventory && arg instanceof Item ){
			temp = (Item)arg;
			if(panelButtons2.get(arg) != null)
				panelButtons2.get(arg).setLabel("" + engine.userInventory.getInventory().get(temp));
		}
		
			
	}
}
	
	
	
	
	/*
	private JPanel createGardenPanel()
	{
		JPanel panel = new JPanel(); 
		panel.setOpaque(false);
	    panel.setLayout(null);
	    int rad = 0;
	    int column =0;
	    int i =0;
	    int j = 0;
	    
	    for( j=0;j<43;j++){    	
	    		    
		    for( i=0;i<23;i++)
		    {
		    		   
			    final JButton clickButton = new JButton ();
			    clickButton.setContentAreaFilled(false);
			    clickButton.setBounds(column,rad,30,30);
			     
			    clickButton.setBorderPainted(false);//Osynlig
			    clickButton.addActionListener(new ActionListener() {
			    	int option =1;
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						if(option ==1){
							option=0;
							clickButton.setContentAreaFilled(true);
						}
	
						else if(option ==0)
					    {
						option =1;
						clickButton.setContentAreaFilled(false);
						}
						
					}
					
			    });	
					
			    panel.add(clickButton);
			    
			    rad = rad + 30;
			    			   	    		    
		    }
		    /*rad = 0;
		    i=0;
		    for( i=0;i<23;i++)
		    {
		    		   
			    JButton clickButton = new JButton ();
			    clickButton.setContentAreaFilled(false);
			    clickButton.setBounds(30,rad,30,30);
			    //clickButton.setContentAreaFilled(false); Synlig för tillfället så jag ser vad jag gör
			    panel.add(clickButton);
			    
			    rad = rad + 30;
			    			   	    		    
		    }
		    column = column + 30;
		    i=0;
		    rad =0;
			 
			   	    		    
		}
	   
		    
		return panel;
			
	}
	
	private JPanel createMiniGamePanel()
	{
		JPanel panelClickable = new JPanel();
		
		panelClickable.setOpaque(false);
	    panelClickable.setLayout(null);
		
		return panelClickable;
	}
	
	private JPanel createInventoryPanel()
	{
		panelClickable.setOpaque(false);
	    panelClickable.setLayout(null);
		
		return panelClickable;
	}
	
	
}*/


