package NyaProjektarbetet;

import java.io.Serializable;

public class Item implements Serializable{
	
	private int price;
	private int level;
	private String name;
	private String pictureFile;
	
	public Item(int price, int level, String pictureFile, String name){
		this.price = price;
		this.level = level;
		this.name = name;
		this.pictureFile = pictureFile;
	}
	
	public String getItemPicture(){
		return pictureFile;
	}
	
	public int getItemPrice(){
		return price;
	}
	
	public int getItemLevel(){
		return level;
	}
	
	public String getItemName(){
		return name;
	}
	
	
	
}
