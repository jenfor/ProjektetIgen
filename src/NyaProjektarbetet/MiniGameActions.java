package NyaProjektarbetet;

import java.util.Random;

import javax.swing.JTextArea;

public class MiniGameActions {
	private MiniGame miniGame;
	private static int mathSolution = 0;
	private static int product1 = 0;
	private static int product2 = 0;
	
    private int wrongAnsweres;
    private int score;
    
	public MiniGameActions(MiniGame miniGame){
		this.miniGame = miniGame;
	}

    
	public void createMathProblemSolution(){
    	Random rand = new Random();
    	product1 = rand.nextInt(11); 
    	product2 = rand.nextInt(11); 
    	
    	mathSolution = product1 * product2;
    	miniGame.getMathProblemTextArea().setText(getMathProblemString());
    }
    
    public String getMathProblemString(){
    	String mathProbText = ("\n      " + product1 + "x"+ product2);
    	return mathProbText;
    }
    
    public String getMathSolutionString(){
    	String solution = ("" + mathSolution);
    	return solution;
    }
    
    public int getWrongAnswers(){
    	return wrongAnsweres;
    }
    
    public void setWrongAnswers(){
    	if(wrongAnsweres > 3){
    		wrongAnsweres = 0;
    	}
    	else{
    		wrongAnsweres += 1;
    	}
    }
    
    public int getScore(){
    	return score;
    }

    public void setScore(int value){
    	score += value;
    }
}
