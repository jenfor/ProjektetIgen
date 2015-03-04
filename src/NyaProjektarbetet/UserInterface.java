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
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


/**
 * 
 */
public class UserInterface implements Observer{
	private GameEngine engine;
	private JFrame myFrame;
	private String image;
	private JButton exitButton;
	public JLabel moneyButton;
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
		screenSize.setSize( screenSize.getWidth() , (screenSize.getHeight() - 30) ); //-30 kompenserar f�r windows-menybaren
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
		//Frame har en best�md storlek 1280x800. Kan finnas kvar tills man implementerar resize-funktion f�r JButton
        /*myFrame.setPreferredSize(new Dimension(1280, 750));	
        myFrame.setMinimumSize(new Dimension(1280, 750));
        myFrame.setResizable(false);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);*/
        
        //Ska vi ha s�h�r s� att det skalar ist�llet? Eller blev det n�got problem med JButton d�? (enligt kommentaren h�r ovanf�r...)
        myFrame.setPreferredSize(screenSize);	
        myFrame.setMinimumSize(screenSize);
        myFrame.setResizable(false);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        createMenu();
		
		JButton startButton = new JButton ("Starta spel");
        startButton.setBounds((int)(width*0.34), (int)(height*0.72), 400, 80);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false); 		//med eller utan kant
        startButton.setFont(font);
        startButton.setForeground(Color.pink); 		//f�rg p� startknappen
        
        background.add(startButton);
		myFrame.add(background);
		
		//Skapa n�gra lyssnare
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //starta spel h�r
            	createGUI();
            	engine.printWelcome();
            	engine.changeRoom("center");
            }	
        });
        
        Sound.soundInLoop("start.wav");
		
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

        //F�rsta menyn
        menu = new JMenu("Meny");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Den enda menyn som inneh�ller n�got");
        menuBar.add(menu);

        //Nytt spel
        menuItem = new JMenuItem("Nytt spel", new ImageIcon("pictures/small_star.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
		menuItem.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent event) {
    			createGUI();
    			engine.printWelcome();
    		}
		});
		menu.add(menuItem);

        //�ppna en sparad fil
		menuItem = new JMenuItem("�ppna", new ImageIcon("pictures/small_arrow.gif"));
        menuItem.setMnemonic(KeyEvent.VK_T);
        /*menuItem = new JMenuItem("�ppna",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "�ppnar ditt spel (f�rhoppningsvis)");*/
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	String name = JOptionPane.showInputDialog("Ladda spelare: ");
            	engine.getPlayer().setUserName(name);
    			engine.gameState.setStatePlayer(engine.getPlayer());
            	engine.load();
            }
        });
        menu.add(menuItem);
        
        //Spara en fil
        menuItem = new JMenuItem("Spara", new ImageIcon("pictures/small_arrow.gif"));
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
                                 new ImageIcon("pictures/small_cross.png"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	int svar = JOptionPane.showConfirmDialog(null, "Vill du l�mna spelet nu?", "Avsluta", JOptionPane. YES_NO_OPTION);
            	if(svar == JOptionPane.YES_OPTION)
            		System.exit(0);
            }
        });
        
        menu.add(menuItem);


        //Fler menydelar vi kanske kan vilja anv�nda till n�got
        /*
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
        menu.add(submenu);*/

        //En andra meny i menyn
        menu = new JMenu("Inst�llningar");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "Inneh�ller inget �n");
        menuBar.add(menu);

        myFrame.setJMenuBar(menuBar);
        
        //En tredje meny i menyn
        menu = new JMenu("Hj�lp");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "Hj�lpmeny");
        
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Hj�lp",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent event) {
        			JOptionPane.showMessageDialog( null, "[hj�lp kommer senare]","Hj��lp",JOptionPane.OK_CANCEL_OPTION); 
        		}
        });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Om spelet",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent event) {
        			JOptionPane.showMessageDialog( null, "Mattespel Version: 1.0 ","Om spelet",JOptionPane.OK_CANCEL_OPTION); 
        		}
        });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("?",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent event) {
        			JOptionPane.showMessageDialog( null, "Vars�god, 1000 kr","???",JOptionPane.OK_CANCEL_OPTION); 
        			engine.getPlayer().changeMoney(1000);
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
		 	engine.getPlayer().addObserver(this);
		
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
	 
	 //***************************Spelmenyn med pengar, f�rem�l etc*****************************
	 private void addBorderLayout(JPanel pa, String current)
	 {
		 	
		 	//int i = 0;
		 	String nextRoom = "Centrum";
		 	if("center".equals(current)){ nextRoom = "Aff�r";}
		 	//final int j = i;
		 	final String c = current;
		 			 	
		 	exitButton = new JButton("Avsluta");
	        JButton button2 = new JButton(nextRoom);
	        JButton mapButton = new JButton("Position");
	        JButton itemButton = new JButton("F�rem�l");
	        //JButton moneyButton = new JButton("Pengar");
	        moneyButton = new JLabel("     Pengar: " + engine.getPlayer().getMoney() + " kr     ");
	        
	        //String image ="pictures/startbackground.jpg";
	                
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        double width = screenSize.getWidth();
	        double height = screenSize.getHeight();
	        
	        /*------Dessa kan bli anv�ndbara f�r skalning, ta ej bort!
	        double textHeight = height * 0.15;
	        double textWidth = height * 0.15;
	        double imgWidth = width * 0.9;
	        double imgHeight = height * 0.9;
	        */
	        
	        JPanel p = new JPanel(new GridLayout(4,1));
	        JPanel p2 = new JPanel(new GridLayout(4,1));
	        JPanel b = new JPanel();
	        b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));
	        //b.setBackground(Color.black);

	        //p.add(button2);
	        //p.add(mapButton);
	        //p.add(exitButton);
	        b.add(moneyButton);
	        b.add(itemButton);
	        b.add(button2);
	        b.add(mapButton);
	        //b.add(exitButton);
	        
	        panel.setLayout(new BorderLayout());
	        //panel.add(textBox, BorderLayout.AFTER_LAST_LINE);
	        panel.add(p, BorderLayout.WEST);
	        panel.add(p2, BorderLayout.EAST);
	        panel.add(b, BorderLayout.NORTH);
	       
	        panel.setPreferredSize(new Dimension((int)width, (int)height)); //bildstorlek, g�r om till att skala
	        panel.setMinimumSize(new Dimension((int)width, (int)height)); //ist�llet f�r att sk�ra av
	        
	        myFrame.getContentPane().add(panel, BorderLayout.NORTH);
	        
	        
	       button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if("center".equals(c)){
					//that.changeRoom("shop");
					//engine.changeCurrentRoom(engine.shop);		//rumsreferenser ist. f�r str�ngar
					engine.changeRoom("shop");				//flyttat till engine
					}
					//else that.changeRoom("center");
					//else engine.changeCurrentRoom(engine.center);		//rumsreferenser ist. f�r str�ngar
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
	            	if(engine.getCurrent().equals("center") )
	            		JOptionPane.showMessageDialog(null, "Du �r i centrum.", "Position", JOptionPane.INFORMATION_MESSAGE);
	            	else if(engine.getCurrent().equals("shop") )
	            		JOptionPane.showMessageDialog(null, "Du �r i aff�ren.", "Position", JOptionPane.INFORMATION_MESSAGE);
	            	else if(engine.getCurrent().equals("garden") )
	            		JOptionPane.showMessageDialog(null, "Du �r p� din tomt.", "Position", JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
	        
	        itemButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	invisPanels.createInventoryPanel();
	            }
	        });
	        
	        /*
	        moneyButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	JOptionPane.showMessageDialog(null, "Du har " + engine.getPlayer().getMoney() + " kr.", "Pengar", JOptionPane.INFORMATION_MESSAGE);
	            }
	        });*/
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
	 
	 public void updateMoneyButton(){	//inte anv�ndbar just nu
		 moneyButton.setText("     Pengar: " + engine.getPlayer().getMoney() + " kr     ");
	 }
	 
	 public void update(Observable obj, Object arg)
		{
			if(obj instanceof Player && arg instanceof Integer){
				moneyButton.setText("     Pengar: " + arg + " kr     ");
				
			}
			
			
				
		}
	 
	 /*
	 public void changeRoom(String current)
	 {
		 //Eftersom rumsbyte �r mer logik �n grafik, har jag flyttat rummen till gameengine. s� den h�r koden beh�vs inte egentligen
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
