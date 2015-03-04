package NyaProjektarbetet;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Mole extends JButton implements ActionListener {
	MoleActions actions;
    private Timer timer;                        // för start/stop actions
    private int animationDuration = 2300; 		// varje animation tar 2,3 s
    private long animStartTime;    				// starta tiden för varje animation
    private int translateY = 0;                 // knappens y position
    private static final int MAX_Y = 100;
    
    private MiniGame miniGame;
    private String moleAns;
    private ImageIcon molePic;
    private ImageIcon wrongAnsPic;
    
    /**
    * Class-construktor
    * Creates a molebutton and starts a animation-timer.
    * 
    * @param label
    * @param miniGame
    */
    public Mole(String label, MiniGame miniGame) {
    	super(label); // lägger till strängen som innehåller en siffra på knappen
        this.miniGame = miniGame;
        actions = new MoleActions(miniGame);
        moleAns = label;    	
    	
        setFont(new Font("Serif", Font.BOLD, 40)); // ändrar textens storlek och färg
        setForeground(new Color(7800));
        //setBorder(BorderFactory.createLineBorder(Color.white));
        
        setOpaque(false);
    	setContentAreaFilled(false);
        setBorderPainted(false);
        
        timer = new Timer(30, this);
        
        wrongAnsPic = new ImageIcon("pictures/squirrel2.png");
        molePic = new ImageIcon("pictures/nymole2.png");
        setIcon(molePic);
        addActionListener(this);
        
        animStartTime = System.nanoTime() / 1000000;
        timer.start();
    }
    
    /**
     * Visar komponenten på position (0, translateY). Detta ändrar endast
     * den renderade positionen, icke den fysiska.
     * 
     * @param g
     */
    public void paint(Graphics g) {
        g.translate(0, translateY);
        super.paint(g);
    }
    
    /**
     * actionPerformed handles the pressed button that stops the animation, 
     * points when the button containing the answer is right or wrong and 
     * when the player loose.
     * 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
                timer.stop();

                // Om den klickade mullvadens tal = rätt svar, lägg till 10 poäng
                if(moleAns.equals(miniGame.miniGameActions.getMathSolutionString())){
                	actions.updateScore(miniGame.getPointsSoFarText());
                	Sound.playSomeSound("coin.wav");
                	miniGame.createMole();
                }
                //Förlorar vid 5 fel
                else if(miniGame.miniGameActions.getWrongAnswers() > 3){
                	miniGame.tryAgain();
                	miniGame.miniGameActions.setWrongAnswers();
                }
                else{
                	//miniGame.pan2.remove(this);
                	actions.wrongAnswere(miniGame.getPointsSoFarText());
                	setIcon(wrongAnsPic);
                	Sound.playSomeSound("boing3.wav");
                }
                //this.setText("Start Animation");
                // reset translation to 0
                translateY = 0;
            //}
        } else {
            // Timer event
            // calculate the elapsed fraction
            long currentTime = System.nanoTime() / 1000000;
            long totalTime = currentTime - animStartTime;
            if (totalTime > animationDuration) {
                animStartTime = currentTime;
            }
            float fraction = (float)totalTime / animationDuration;
            fraction = Math.min(1.0f, fraction);
            // This calculation will cause translateY to go from 0 to MAX_Y
            // as the fraction goes from 0 to 1
            if (fraction < .5f) {
                translateY = (int)(MAX_Y * (2 * fraction));
            } else {
                translateY = (int)(MAX_Y * (2 * (1 - fraction)));
            }
            // redisplay the component with the new location
            repaint();
        }
    }

}
