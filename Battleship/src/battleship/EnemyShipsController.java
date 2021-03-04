package battleship;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;


/**
* This window shows up when we press Details/Enemy Ships
*/
public class EnemyShipsController {
	
	
	//The situation of each (Enemy's) ship
	
    @FXML
    private TextField carrier;
    @FXML
    private TextField battleship;
    @FXML
    private TextField cruiser;
    @FXML
    private TextField submarine;
    @FXML
    private TextField destroyer;

    
    
    /**
    * Initialize the Window of Details/Enemy Ships
    * */
	
    public void initialize ()
	{
		// Create a reference to enemy's Board
		Board enBoard;
		enBoard = SampleController.enemyBoard;
		
		String situation;
		
		//Check the situation of carrier_ship
		situation = ship_situation(enBoard.carrier_ship);
		carrier.setText(situation);
		
		//Check the situation of battleship_ship
		situation = ship_situation(enBoard.battleship_ship);
		battleship.setText(situation);
		
		//Check the situation of cruiser_ship
		situation = ship_situation(enBoard.cruiser_ship);
		cruiser.setText(situation);
		
		//Check the situation of submarine_ship
		situation = ship_situation(enBoard.submarine_ship);
		submarine.setText(situation);
		
		//Check the situation of destroyer_ship
		situation = ship_situation(enBoard.destroyer_ship);
		destroyer.setText(situation);


	}
    
    
    /**
    * Check the situation of a Ship
    *
    * 
    * @param  ship  Take as argument one ship, and check its fields
    * @return  a string: "Sunken" or "In Danger" or "Safe"
    * 
    * @see Ship
    */
    
    private String ship_situation(Ship ship) {
    	
    	String situation;
    	//Sunken ship
		if (ship.health == 0)
			situation = new String("Sunken");	
		
		//In danger (at least one shot)
		else if (ship.health < ship.length)
			situation = new String("In Danger");
		
		//Safe (was not shot)
		else
			situation = new String("Safe");
		
		return situation;
    }
	

}
