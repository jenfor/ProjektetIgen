package NyaProjektarbetet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

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
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
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
        
        Sound.soundInLoop("start.wav");
		
        myFrame.pack();
        myFrame.setVisible(true);
    }
	
	public void createMenu() {
    	//GUI'n skapas
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;


        //Menyn skapas
        menuBar = new JMenuBar();

        //Första menyn
        menu = new JMenu("Meny");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Den enda menyn som innehåller något");
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

        //Öppna en sparad fil
		menuItem = new JMenuItem("Öppna", new ImageIcon("pictures/small_arrow.gif"));
        menuItem.setMnemonic(KeyEvent.VK_T);
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
            	int svar = JOptionPane.showConfirmDialog(null, "Vill du spara innan du avslutar?", "Avsluta", JOptionPane. YES_NO_OPTION);
            	if(svar == JOptionPane.YES_OPTION){
            		engine.save();
            		System.exit(0);
            	}
            	else
            		System.exit(0);
            }
        });
        
        menu.add(menuItem);


        //Fler menydelar vi kanske kan vilja använda till något
        /*
        JMenu submenu;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
        
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
        menu = new JMenu("Inställningar");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "Innehåller inget än");
        menuBar.add(menu);

        myFrame.setJMenuBar(menuBar);
        
        //En tredje meny i menyn
        menu = new JMenu("Hjälp");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "Hjälpmeny");
        
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
        			JOptionPane.showMessageDialog( null, "Varsågod, 1000 kr","???",JOptionPane.OK_CANCEL_OPTION); 
        			engine.getPlayer().changeMoney(1000);
        		}
        });
        menu.add(menuItem);
        
        myFrame.setJMenuBar(menuBar);
        
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	int svar = JOptionPane.showConfirmDialog(null, "Vill du spara innan du avslutar?", "Avsluta", JOptionPane. YES_NO_OPTION);
            	if(svar == JOptionPane.YES_OPTION){
            		engine.save();
            		System.exit(0);
            	}
            	else
            		System.exit(0);
            	
            }
        });
		
        myFrame.pack();
	}
	
	 public void createGUI() {
		 	
		
	        image ="pictures/startbackground.jpg";
	        
	        panel = new JPanelWithBackground(image); 
	        addBorderLayout(panel, engine.getCurrent());
	       	               	        
	        myFrame.pack();
	              
	        
	    }
	 
	 //***************************Spelmenyn med pengar, föremål etc*****************************
	 private void addBorderLayout(JPanel pa, String current)
	 {
		 	engine.getPlayer().addObserver(this);
		 	
		 	String nextRoom = "Centrum";
		 	if("center".equals(current)){ nextRoom = "Affär";}
		 	final String c = current;
		 			 	
		 	exitButton = new JButton("Avsluta");
	        JButton button2 = new JButton(nextRoom);
	        JButton infoButton = new JButton("Info");
	        JButton itemButton = new JButton("Föremål");
	        moneyButton = new JLabel("     Pengar: " + engine.getPlayer().getMoney() + " kr     ");
	        
	                
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        double width = screenSize.getWidth();
	        double height = screenSize.getHeight();
	        
	        
	        JPanel p = new JPanel(new GridLayout(4,1));
	        JPanel p2 = new JPanel(new GridLayout(4,1));
	        JPanel b = new JPanel();
	        b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));

	        b.add(moneyButton);
	        b.add(itemButton);
	        b.add(button2);
	        b.add(infoButton);
	        
	        panel.setLayout(new BorderLayout());
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
						engine.changeRoom("shop");				//flyttat till engine
					}
					else 
						engine.changeRoom("center");				//flyttat till engine
				}
			});
	       	
	        exitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	System.exit(0);
	            }
	        });
	        
	        infoButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	
	            	String name = "Ditt spelarnamn är: " + engine.getPlayer().getUserName() + "\n";
	            	String level = "Du är level: " + engine.getPlayer().getLevel() + "\n";
	            	
	            	if(engine.getCurrent().equals("center") )
	            		JOptionPane.showMessageDialog(null, name + level + "Du är i centrum.", "Info", JOptionPane.INFORMATION_MESSAGE);
	            	else if(engine.getCurrent().equals("shop") )
	            		JOptionPane.showMessageDialog(null, name + level + "Du är i affären.", "Info", JOptionPane.INFORMATION_MESSAGE);
	            	else if(engine.getCurrent().equals("garden") )
	            		JOptionPane.showMessageDialog(null, name + level + "Du är på din tomt.", "Info", JOptionPane.INFORMATION_MESSAGE);
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
	 }
	 

	 public void update(Observable obj, Object arg)
		{
			if(obj instanceof Player && arg instanceof Integer){
				moneyButton.setText("     Pengar: " + arg + " kr     ");
				
			}
			
			
				
		}
	 

}
