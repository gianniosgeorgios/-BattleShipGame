package battleship;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
* This window shows up when we press Details/EnemyShots
* Attention: Enemy Shots must at least 5 
* This window contains 5 columns (as the Shots)
* 1st Column: [x,y] coordinates
* 2nd Column: HIT / MISS
* 3rd Column: Type of ship that was shot
*/

public class PlayerShotsController {
	
	//1st column: coordinates ([x,y])
    @FXML
    private TextField coord_1;
    @FXML
    private TextField coord_2;
    @FXML
    private TextField coord_3;
    @FXML
    private TextField coord_4;
    @FXML
    private TextField coord_5;
    
    
    
    //2nd column: result ( "гит" or "MISS")
    @FXML
    private TextField result_1;
    @FXML
    private TextField result_2;
    @FXML
    private TextField result_3;
    @FXML
    private TextField result_4;
    @FXML
    private TextField result_5;
    
    
    
    //3rd column: On ship  (if "HIT" the name of ship)
    // if "MISS" -> none  
    @FXML
    private TextField target_1;
    @FXML
    private TextField target_2;
    @FXML
    private TextField target_3;
    @FXML
    private TextField target_4;
    @FXML
    private TextField target_5;
    
    
	
	public void initialize ()
	{
		//Create reference to enemy's Board in which the Player shots 
		Board enBoard;
		enBoard = SampleController.enemyBoard;
		
		String [] result_info = {"Miss","Hit"};
		String [] ships_info = {"Carrier","Battleship","Cruiser","Submarine","Destroyer"};

		//WARNING: This window will open only if the enemy shots are >= 5 !
		if (enBoard.total_shots>=5)
		{
			
			
			
			// Line 1 
			int x1     = enBoard.shots_info[0][enBoard.total_shots-5];
			int y1     = enBoard.shots_info[1][enBoard.total_shots-5];
			int res1   = enBoard.shots_info[2][enBoard.total_shots-5];
			int ship_1 = enBoard.shots_info[3][enBoard.total_shots-5];
			
			coord_1.setText("[" + Integer.toString(x1) + "," + Integer.toString(y1) + "]");
			result_1.setText(result_info[res1]);
			
			if (ship_1 == -1)
				target_1.setText("None");
			else
				target_1.setText(ships_info[ship_1]);
			
			
	
			
			
			// Line 2
			int x2     = enBoard.shots_info[0][enBoard.total_shots-4] ;
			int y2     = enBoard.shots_info[1][enBoard.total_shots-4];
			int res2   = enBoard.shots_info[2][enBoard.total_shots-4] ;
			int ship_2 = enBoard.shots_info[3][enBoard.total_shots-4];
			
			coord_2.setText("[" + Integer.toString(x2) + "," + Integer.toString(y2) + "]");
			result_2.setText(result_info[res2]);
			
			if (ship_2 == -1)
				target_2.setText("None");
			else
				target_2.setText(ships_info[ship_1]);
			
			
			
			
			// Line 3
			int x3     = enBoard.shots_info[0][enBoard.total_shots-3];
			int y3     = enBoard.shots_info[1][enBoard.total_shots-3];
			int res3   = enBoard.shots_info[2][enBoard.total_shots-3];
			int ship_3 = enBoard.shots_info[3][enBoard.total_shots-3];
			
			coord_3.setText("[" + Integer.toString(x3) + "," + Integer.toString(y3) + "]");
			result_3.setText(result_info[res3]);
			
			if (ship_3 == -1)
				target_3.setText("None");
			else
				target_3.setText(ships_info[ship_3]);
			
			
			
			// Line 4
			int x4     = enBoard.shots_info[0][enBoard.total_shots-2];
			int y4     = enBoard.shots_info[1][enBoard.total_shots-2];
			int res4   = enBoard.shots_info[2][enBoard.total_shots-2];
			int ship_4 = enBoard.shots_info[3][enBoard.total_shots-2];
			
			coord_4.setText("[" + Integer.toString(x4) + "," + Integer.toString(y4) + "]");
			result_4.setText(result_info[res4]);
			
			if (ship_4 == -1)
				target_4.setText("None");
			else
				target_4.setText(ships_info[ship_4]);
			
			
			
			
			// Line 5
			int x5     = enBoard.shots_info[0][enBoard.total_shots-1];
			int y5     = enBoard.shots_info[1][enBoard.total_shots-1];
			int res5   = enBoard.shots_info[2][enBoard.total_shots-1];
			int ship_5 = enBoard.shots_info[3][enBoard.total_shots-1];
			
			coord_5.setText("[" + Integer.toString(x5) + "," + Integer.toString(y5) + "]");
			result_5.setText(result_info[res5]);
			
			if (ship_5 == -1)
				target_5.setText("None");
			else
				target_5.setText(ships_info[ship_5]);
		}			
	}

}
