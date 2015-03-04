package NyaProjektarbetet;

import javax.swing.JTextArea;

public class MoleActions {
	private MiniGame miniGame;
    private static final String rightAns = "Antal poäng: ";

	public MoleActions(MiniGame miniGame){
		this.miniGame = miniGame;
		
	}

	public void updateScore(JTextArea a) {
		miniGame.miniGameActions.setScore(10);
		a.setText("\n " + rightAns + miniGame.miniGameActions.getScore());
	}
	
	public void wrongAnswere(JTextArea a) {
		miniGame.miniGameActions.setScore(-2);
		miniGame.miniGameActions.setWrongAnswers();
    	System.out.println(" Fel: " + miniGame.miniGameActions.getWrongAnswers());
		a.setText("\n " + rightAns + miniGame.miniGameActions.getScore());
	}
}
