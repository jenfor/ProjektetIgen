package NyaProjektarbetet;

import javax.swing.JTextArea;


public class MoleActions {
	private MiniGame miniGame;
    private static final String rightAns = "Antal poäng: ";
    
    /**
    * Class-constructor
    * 
    * @param    
    */
	public MoleActions(MiniGame miniGame){
		this.miniGame = miniGame;
		
	}

    /**
    * Updates the textarea which contains the score
    * 
    * @a    the score-textarea that gets updated  
    */
	public void updateScore(JTextArea a) {
		miniGame.miniGameActions.setScore(10);
		a.setText("\n " + rightAns + miniGame.miniGameActions.getScore());
	}
	
    /**
    * Decreases the score by 2 and updates the amount of answers 
    * 
    * @a    the score-textarea that gets updated     
    */
	public void wrongAnswere(JTextArea a) {
		miniGame.miniGameActions.setScore(-2);
		miniGame.miniGameActions.setWrongAnswers();
		a.setText("\n " + rightAns + miniGame.miniGameActions.getScore());
	}
}
