package NyaProjektarbetet;

import java.io.Serializable;
import java.util.Observable;

public class Player extends Observable implements Serializable {
	private String userName;
	private int level;
	private int money;
	public Inventory myInventory;
	
	public Player(){
		//myInventory = new Inventory();
		level = 1;
		money = 100;
	}
	
	public void setInventory(Inventory in){
		myInventory = in;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public int getLevel(){
		return level;
	}
	
	/*
	public void setLevel(int newLevel){
		level = newLevel;
	}*/
	
	//eventuell metod för att levla upp beroende på hur levlandet ska fungera
	public void levelUp(){
		level = level++;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void changeMoney(int value){ //value kan förstås vara både positivt och negativt
		money = money + value;
		//engine.gui.updateMoneyButton();	//player kan inte nå engine dock
		setChanged();
		notifyObservers(money);
	}
}
