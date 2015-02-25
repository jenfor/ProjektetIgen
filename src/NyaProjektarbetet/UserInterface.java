package NyaProjektarbetet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.*;


/**
 * 
 */
public class UserInterface {
	private GameEngine engine;
	private JFrame myFrame;
	private String image;
	private JButton exitButton;
	private JPanelWithBackground panel;
	public PanelSklett invisPanels;
	private JPanelWithBackground background;
	//private JTextField entryField;
	//private JTextArea log;
	//private HashMap<String,JButton> exitButtons = new HashMap<String,JButton>();
	//private UserInterface that = this; // ;-)
	//private Room room;
	
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        //createGUI();
        //room = new Room();
        invisPanels = new PanelSklett(engine, this);
    }
    
    public JFrame myFrame() {
    	return myFrame;
    }
    
    public void gameStart() {
    	myFrame = new JFrame("spel");
		background = new JPanelWithBackground("pictures/startbackground.jpg");
		background.setLayout(null);
		Font font = new Font("Viner Hand ITC", Font.BOLD, 50);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.setSize( screenSize.getWidth() , (screenSize.getHeight() - 30) ); //-30 kompenserar för windows-menybaren
        //double width = screenSize.getWidth();
        //double height = screenSize.getHeight();
        
		//Frame har en bestämd storlek 1280x800. Kan finnas kvar tills man implementerar resize-funktion för JButton
        /*myFrame.setPreferredSize(new Dimension(1280, 750));	
        myFrame.setMinimumSize(new Dimension(1280, 750));
        myFrame.setResizable(false);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);*/
        
        //Ska vi ha såhär så att det skalar istället? Eller blev det något problem med JButton då? (enligt kommentaren här ovanför...)
        myFrame.setPreferredSize(screenSize);	
        myFrame.setMinimumSize(screenSize);
        myFrame.setResizable(false);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        createMenu();
		
		JButton startButton = new JButton ("Starta spel");
        startButton.setBounds(420,580,400,80);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false); 		//med eller utan kant
        startButton.setFont(font);
        startButton.setForeground(Color.pink); 		//färg på startknappen
        
        background.add(startButton);
		myFrame.add(background);
		
		//Skapa några lyssnare
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //starta spel här
            	createGUI();
            	engine.printWelcome();
            	engine.changeRoom("center");
            }	
        });
		
        myFrame.pack();
        myFrame.setVisible(true);
    }
	
	public void createMenu() {
    	//GUI'n skapas
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Menyn skapas
        menuBar = new JMenuBar();

        //Första menyn
        menu = new JMenu("Meny");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //Nytt spel
        menuItem = new JMenuItem("Nytt spel", new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent event) {
    			createGUI();
    			engine.printWelcome();
    		}
		});
		menu.add(menuItem);

        //Öppna en sparad fil
        menuItem = new JMenuItem("Öppna",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Öppnar ditt spel (förhoppningsvis)");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	engine.load();
            }
        });
        menu.add(menuItem);
        
        //Spara en fil
        menuItem = new JMenuItem("Spara", new ImageIcon("image.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	engine.save();
            }
        });
        menu.add(menuItem);
        
        //Avsluta
        menuItem = new JMenuItem("Avsluta",
                                 new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	int svar = JOptionPane.showConfirmDialog(null, "Vill du lämna spelet utan att spara?", "Avsluta", JOptionPane. YES_NO_OPTION);
            	if(svar == JOptionPane.YES_OPTION)
            		System.exit(0);
            }
        });
        
        menu.add(menuItem);


        //Fler menydelar vi kanske kan vilja använda till något
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        //Checkboxar
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);

        //Undermeny
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

        //En andra meny i menyn
        menu = new JMenu("Inställningar");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);

        myFrame.setJMenuBar(menuBar);
        
        //En tredje meny i menyn
        menu = new JMenu("Hjälp");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Hjälp",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent event) {
        			JOptionPane.showMessageDialog( null, "[hjälp kommer senare]","Hjäälp",JOptionPane.OK_CANCEL_OPTION); 
        		}
        });
        
        menu.add(menuItem);
        myFrame.setJMenuBar(menuBar);
        
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
		
        myFrame.pack();
	}
	
	 public void createGUI() {
		
	        image ="pictures/startbackground.jpg";
	        
	        //entryField = new JTextField(34);
	        /*
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        double width = screenSize.getWidth();
	        double height = screenSize.getHeight();
	        
	        double textHeight = height * 0.15;
	        double textWidth = height * 0.15;
	        double imgWidth = width * 0.9;
	        double imgHeight = height * 0.9;
	        
	        myFrame.setPreferredSize(new Dimension((int)width, (int)height));
	        myFrame.setMinimumSize(new Dimension((int)width, (int)height));
	        myFrame.setResizable(false);
	        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        
	        entryField.requestFocus();
	         */	
	        panel = new JPanelWithBackground(image); 
	        addBorderLayout(panel, engine.getCurrent());
	        //createMenu();
	       	               	        
	        myFrame.pack();
	              
	        
	    }
	 
	 private void addBorderLayout(JPanel pa, String current)
	 {
		 	//int i = 0;
		 	String nextRoom = "Centrum";
		 	if("center".equals(current)){ nextRoom = "Affär";}
		 	//final int j = i;
		 	final String c = current;
		 			 	
		 	exitButton = new JButton("Exit");
	        JButton button2 = new JButton(nextRoom);
	        JButton mapButton = new JButton("Karta");
	        JButton itemButton = new JButton("Föremål");
	        JButton moneyButton = new JButton("Pengar");
	        
	        //String image ="pictures/startbackground.jpg";
	                
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        double width = screenSize.getWidth();
	        double height = screenSize.getHeight();
	        
	        /*------Dessa kan bli användbara för skalning, ta ej bort!
	        double textHeight = height * 0.15;
	        double textWidth = height * 0.15;
	        double imgWidth = width * 0.9;
	        double imgHeight = height * 0.9;
	        */
	        
	        JPanel p = new JPanel(new GridLayout(4,1));
	        JPanel p2 = new JPanel(new GridLayout(4,1));
	        JPanel b = new JPanel();
	        b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));

	        //p.add(button2);
	        //p.add(mapButton);
	        //p.add(exitButton);
	        b.add(itemButton);
	        b.add(moneyButton);
	        b.add(button2);
	        b.add(mapButton);
	        b.add(exitButton);
	        
	        panel.setLayout(new BorderLayout());
	        //panel.add(textBox, BorderLayout.AFTER_LAST_LINE);
	        panel.add(p, BorderLayout.WEST);
	        panel.add(p2, BorderLayout.EAST);
	        panel.add(b, BorderLayout.NORTH);
	       
	        panel.setPreferredSize(new Dimension((int)width, (int)height)); //bildstorlek, gör om till att skala
	        panel.setMinimumSize(new Dimension((int)width, (int)height)); //istället för att skära av
	        
	        myFrame.getContentPane().add(panel, BorderLayout.NORTH);
	        
	        
	       button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if("center".equals(c)){
					//that.changeRoom("shop");
					//engine.changeCurrentRoom(engine.shop);		//rumsreferenser ist. för strängar
					engine.changeRoom("shop");				//flyttat till engine
					}
					//else that.changeRoom("center");
					//else engine.changeCurrentRoom(engine.center);		//rumsreferenser ist. för strängar
					else engine.changeRoom("center");				//flyttat till engine
				}
			});
	       	
	        exitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	System.exit(0);
	            }
	        });
	        
	        mapButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	JOptionPane.showMessageDialog(null, "Du är i " + engine.getCurrent(), "Karta", JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
	        
	        itemButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	invisPanels.createInventoryPanel();
	            }
	        });
	 }
	 
	 public void setJPanelWithBackground(String i)
	 {
		  myFrame.remove(panel);
		  panel = new JPanelWithBackground(i);
		  panel.setLayout(new BorderLayout());
		  addBorderLayout(panel, engine.getCurrent());
		  panel.add(invisPanels.getPanel(engine.getCurrent()), BorderLayout.CENTER); //room.getRoomPanel("Shop"/*engine.getCurrent()*/));
		  myFrame.add(panel);			
		  myFrame.pack();
		  //myFrame.setVisible(true);   
	 }
	 
	 /*
	 public void changeRoom(String current)
	 {
		 //Eftersom rumsbyte är mer logik än grafik, har jag flyttat rummen till gameengine. så den här koden behövs inte egentligen
		  * 
		  * 
		  * 
		 //engine.setCurrent(current);
		 //if(current.equals("Center")) room = invisPanels.center; 
		 //else if(current.equals("Shop")) room = invisPanels.shop;
		 //else if(current.equals("Garden")) room = invisPanels.garden;
		 //else room = invisPanels.miniGame1;
		 
		 //setJPanelWithBackground(room.getPicture(engine.getCurrent()));
		 
		 engine.setCurrent(current);
		 if(current.equals("Center")) room = engine.center; 
		 else if(current.equals("Shop")) room = engine.shop;
		 else if(current.equals("Garden")) room = engine.garden;
		 else room = engine.minigame1;
		 
		 setJPanelWithBackground(room.getPicture(engine.getCurrent()));
	 }*/
	 

}
