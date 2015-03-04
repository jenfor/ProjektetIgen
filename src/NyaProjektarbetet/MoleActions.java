package NyaProjektarbetet;

import javax.swing.JTextArea;

public class MoleActions {
	private MiniGame miniGame;
    private static final String rightAns = "Antal poäng: ";

	public MoleActions(MiniGame miniGame){
		this.miniGame = miniGame;
		
	}

	public void updateScore(JTextArea a) {
		miniGame.setScore(10);
		a.setText("\n " + rightAns + miniGame.getScore());
	}
	
	public void wrongAnswere(JTextArea a) {
		miniGame.setScore(-2);
		miniGame.setWrongAnswers();
    	System.out.println(" Fel: " + miniGame.getWrongAnswers());
		a.setText("\n " + rightAns + miniGame.getScore());
	}
}
