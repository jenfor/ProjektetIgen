package NyaProjektarbetet;

public class Money { //denna klass behövs nog inte längre

	private int money;
	
	public Money(){
		money = 0;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void changeMoney(int value){ //value kan förstås vara både positivt och negativt
		money = money + value;
	}
}
