package battleship;

import javafx.scene.Parent;


public class Ship extends Parent {
	
	//"on target shoot" point for each ship 
	int[] shoot_points_array = {350,250,100,100,50};
	
	//sinking points 
	int[] final_points = {1000,500,250,0,0};
	
	//array length: Length of each ship
	public int[] array_length = {5,4,3,3,2};
	
	//length: Length of the ship in [5,4,3,3,2]
    public int length;
    
    // type: Type of a ship in [0: "Carrier",1: "Battleship",2 : "Cruiser",3: "Submarine",4: "Destroyer"]
    public int type;
    
    // On_target_shoot : Point that you collect if a shoot is on target
    public int on_target_shoot;
    
    // final_shoot : Points you gain if the ship sink 
    public int final_shoot;
    
    // vertical: orientation of the ship 
    public boolean vertical = true;

    // health: Parts of the ship remain healthy
    public int health;

    //Constructor 
    public Ship(int type, boolean vertical) {
    	
    	this.type = type;
    	this.length = array_length[this.type];
        this.vertical = vertical;
        this.on_target_shoot = shoot_points_array[this.type];
        this.final_shoot = final_points[this.type];
        this.health = length;
    }
    
    //Reduce healthy part 
    public void hit() {
        health--;
    }
    
    //Check if a ship is alive
    public boolean isAlive() {
        return health > 0;
    }
}