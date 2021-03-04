package battleship;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javafx.scene.paint.Color;
import battleship.Board.Cell;
import javafx.event.ActionEvent;
import javafx.scene.Scene;


class CoordinatesException extends Exception{  
	/**Use this option to add a user-defined ID
	 * in combination with custom serialization code 
     *  if the type did undergo structural  
     *  changes since its first release.
	 */
	private static final long serialVersionUID = 1L;

	CoordinatesException(String s){  
	  super(s);  
	 }  
	}


/**
* This is the Controller of MAIN WINDOW
* Attention: Enemy Shots must at least 5 
* 
* 
* One game ends if:
* One of the player used 40 shots
* One of the player has not remain ships
* The user press Application->Exit
**/


public class SampleController {
	
	
	//Print information about game progress in GUI  
    @FXML
    private TextField system_log;
    
	//Define this scenario as default 
	public static String scenario = new String ("default");
    
	//Progress of a game
    public static boolean game_ended = false;
    
    
    //Declare variables that will show up in GUI (UPPER WINDOW)
    @FXML
    private TextField player_ships;  
    @FXML
    private TextField enemy_ships;   
    
    @FXML
    private TextField player_points;
    @FXML
    private TextField enemy_points;
    
    @FXML
    private TextField enemy_per;
    @FXML
    private TextField player_per;

	@FXML
	private BorderPane grid_id;
	
	
	//Board (MEDIAN STAGE OF WINDOW)
	public static Board enemyBoard, playerBoard;
	private int x_player, y_player;
	
	// The player that plays first is selected randomly
	private Random random = new Random();
	Random playing_first = new Random();
	private boolean enemyTurn = playing_first.nextBoolean();
	
	
	//Monitor when the Shoot_Key is pressed
	private boolean key_pressed = false;
	
	

	
	
    /**
    * If the game ends, show hidden ships of enemy
    * (with FUCSHIA color) 
    */
    public void ShowSolution()
    {
    	for (int y = 1; y < 11; y++) {
    		for (int x =1; x <11 ; x++) {
    			if (enemyBoard.getCell(x, y).ship != null) {
    				if(!enemyBoard.getCell(x, y).wasShot) {
    					enemyBoard.getCell(x, y).setFill(Color.FUCHSIA);
    					enemyBoard.getCell(x, y).setFill(Color.FUCHSIA);
    				}
    			}
    					
    				
    		}
    			
    	}
    		
    	
    }
	
	
    
    
    

    /**
    * It is called if we press Application/Start 
    * 
    * In UPPER STAGE OF WINDOW we see Information about players 
    * In MEDIAN STAGE OF WINDOW we see Boards
    * After reading default scenario (first)
    * Then, the player watch which player is playing first
    * 
    */
   
    @FXML
    void StartMethod(ActionEvent event) {
    	game_ended=false;
    	

    	// Show up which player is playing first
		if (!enemyTurn) {
			system_log.setText("A new Scenario started syccesfully. You are playing fisrt");
		}
		else
		{
			system_log.setText("New Scenario started! Enemy played, and it's your turn");
		}
		
		//UPPER STAGE OF WINDOW: Information about players 
		
		//Show up information about enemy (GUI)
        enemy_ships.setText(Integer.toString(enemyBoard.ships));
        enemy_points.setText(Integer.toString(enemyBoard.shoot_points));
        enemy_per.setText(Integer.toString(enemyBoard.target_shots)+"/"+Integer.toString(enemyBoard.total_shots));
        
        //Show up information about player (GUI)
        player_ships.setText(Integer.toString(playerBoard.ships));
        player_points.setText(Integer.toString(playerBoard.shoot_points)); 
        player_per.setText(Integer.toString(playerBoard.target_shots)+"/"+Integer.toString(playerBoard.total_shots));
		
		
		//MEDIAN STAGE OF WINDOW:  Create Boards
		enemyBoard =  new Board(true);
		playerBoard =  new Board(false);
		
		HBox hbox = new HBox(20, playerBoard, enemyBoard);
		hbox.setAlignment(Pos.CENTER);
		
		grid_id.setCenter(hbox);
		
		//Read default scenario
		String player_str = new String("player_"+scenario+".txt");
		ReadTxt(player_str, false);

		String enemy_str = new String("enemy_"+scenario+".txt");
		ReadTxt(enemy_str, true);
		

		if (enemyTurn) {
			enemyMove();
		}

    }
    
    /**
    * It is called if we press Application->Exit 
    * Then the game ends
    * 
    */
	
	
    @FXML
    void ExitMethod(ActionEvent event) {
    	
    	System.out.println("Goodbye!");
    	System.exit(0);
    }
    
    
    /**
    * It is called if we press Application->Load
    * 
    * It contains a form in which we type scenario 
    * example if we press "george" the scenario 
    * player_george.txt and enemy_george.txt will be loaded.
    * This window is closed automatically
    * 
    * If the scenario does not exist, then an Exception is thrown
    * @see Read
    * 
    */
    
    @FXML
    void LoadMethod(ActionEvent event) {
    	
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("Load.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		
    		stage.setTitle("Load Window");
    		stage.setScene(new Scene(root1));
    		stage.show();	
    		
    		System.out.println("New Scenario is:"+scenario);	
    	}
    	catch (Exception e) {
    		System.out.println("This window is not openning right now!");
    	}
    	
    }
    
    
    
    /**
    * It is called if we press Details ->PlayerShots 
    * This window shows up if shots at least 5  
    * @see PlayerShotsController
    */
    
    @FXML
    void PlayerShotsMethod(ActionEvent event) {
    	
    	
    	if(enemyBoard.total_shots>=5) {
	    	try {
	    		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("PlayerShots.fxml"));
	    		Parent root1 = (Parent) fxmlLoader.load();
	    		Stage stage = new Stage();
	    		
	    		stage.setTitle("Details for five Last Shots of player");
	    		stage.setScene(new Scene(root1));
	    		stage.show();	
	    	}
	    	catch (Exception e) {
	    		System.out.println("This window is not openning right now!");
	    	}
    	}
    	else {
    		system_log.setText("You should make at least 5 shots, to open this window");
    	}
    	

    }
    

    
    /**
    * It is called if we press Details->Enemy Shots
    * This window shows up if shots at least 5  
    * @see EnemyShotsController
    */
    
    @FXML
    void EnemyShotsMethod(ActionEvent event) {
    	
    	
    	if (playerBoard.total_shots>=5) {
	    	try {
	    		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("EnemyShots.fxml"));
	    		Parent root1 = (Parent) fxmlLoader.load();
	    		Stage stage = new Stage();
	    		
	    		stage.setTitle("Details for five Last Shots of Enemy");
	    		stage.setScene(new Scene(root1));
	    		stage.show();	
	    	}
	    	catch (Exception e) {
	    		System.out.println("This window is not openning right now!");
	    	}
    	}
    	else {
    		system_log.setText("Enemy should make at least 5 shots, to open this window");
    	}
    		

    }
    

    
    /**
    * It is called if we press Details ->Enemy Ships
    * @see EnemyShipsController
    * 
    */
    
    @FXML
    void EnemyShipsMethod(ActionEvent event) {
    	
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("EnemyShips.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		
    		stage.setTitle("Details for Enemy Ships");
    		stage.setScene(new Scene(root1));
    		stage.show();	
    	}
    	catch (Exception e) {
    		System.out.println("This window is not openning right now!");
    	}
    }

	
    /**
    * Initialize Board for each player, according to file ".TXT"
    * The TXT is read line by line. For each line  
    * we split strings using comma, and save each digit in array as string.
    * Then we convert string values to integers.
    * <p>
    * In the case that the scenario does not exist,
    * load default instead !
    * 
    * @param s The read scenario from Load Method
    * @param isEnemy If the scenario refers to player or enemy
    * @see Board#placeShip(Ship, int, int)
    */

    
    private void ReadTxt(String s,boolean isEnemy) {
		try {
        	
        	// Try to open player_default.txt
    		File myObj = new File(s);
    		Scanner myReader = new Scanner(myObj);
    		
    		
    		//Read line by line 
    		while (myReader.hasNextLine()) {
    			
    			//Read Line
    			String data = myReader.nextLine();
    			
 
    			
    			//Split strings using comma  
    			String[] parts = data.split(",");
    			
    			//Save into array (example: ["1","7","0","1"])
    			int[] ints = new int[parts.length];
    			
    			//Convert to integers (example: [1,7,0,1])
    			for (int i = 0; i < parts.length; i++) {
    				ints[i] = Integer.parseInt(parts[i]);
    			}
    			
    			
    			//Save contents of list into variables
    			int type = ints[0]-1;
    			int x = ints[1];
    			int y = ints[2];
    			boolean vertical = (ints[3] == 2);

                
    			
    			//Place ship for Player
    			if (isEnemy == false) {
    				playerBoard.placeShip(new Ship(type, vertical), y+1, x+1);

    			}
    			
    			//Place ship for Enemy according to type
    			else {
    				
    				if (type == 0 )
    				{
    					enemyBoard.carrier_ship = new Ship(0,vertical);
    					enemyBoard.placeShip(enemyBoard.carrier_ship, y+1, x+1);
    				}
    				else if (type == 1)
    				{
    					enemyBoard.battleship_ship = new Ship(1,vertical);
    					enemyBoard.placeShip(enemyBoard.battleship_ship, y+1, x+1);
    				}
    				else if (type == 2)
    				{
    					enemyBoard.cruiser_ship = new Ship(2,vertical);
    					enemyBoard.placeShip(enemyBoard.cruiser_ship, y+1, x+1);
    				}
    				else if (type == 3)
    				{
    					enemyBoard.submarine_ship = new Ship(3,vertical);
    					enemyBoard.placeShip(enemyBoard.submarine_ship, y+1, x+1);
    				}
    				else if (type == 4)
    				{
    					enemyBoard.destroyer_ship = new Ship(4,vertical);
    					enemyBoard.placeShip(enemyBoard.destroyer_ship, y+1, x+1);
    				}

    			}
    			}
    		myReader.close();
        } catch (Exception e) {
        	
        	// In the case that the scenario does not exist,
        	// load default instead 
        	
    		System.out.println("An error occurred.");
    		system_log.setText("This scenario does not exist. Pleas load a proper scenario");
    		
    		game_ended = true;
    		
    		
    		//String player_str = new String("./src/player_default.txt");
    		//ReadTxt(player_str, false);

    		
    		//String enemy_str = new String("./src/enemy_default.txt");
    		//ReadTxt(enemy_str, true);
    		
    		
    	}
	}
	
	
	
	// These variables help enemy 
    // to shoot proper in case of hit
	
    private boolean i_found = false;
	private int x = 0;
	private int y = 0;
	private Cell saved1 [] = null;
	
	
    /**
    * The enemy is playing again if he shoots a ship.
    * In this case (HIT), the next shot is near the previous successful
    * In the other case (MISS), the next shot is selected randomly
    * <p>
    * The game ends if:
    * The remain ships are zero
    * The shots of a player are 40 
    * <p>
    * We also refresh GUI information in the upper stage of window
    * @see Board#getNeighbors(int, int)
    */
	
	
	//EnemyMoveMethod
    public void enemyMove() {
    	
    	//If the enemy find a ship 
    	boolean first = true;
    	
        while (enemyTurn) {
        	
        	
        	//if the enemy has not found a ship
        	// it shoots randomly
        	if (i_found == false) {
        		
        		//x,y between 0,9 
        		x = random.nextInt(10);
        		y = random.nextInt(10);
            
        		System.out.print("x: "+ y);
        		System.out.println("y: "+ x);

        		//x,y between 1,10 
        		x = x+1;
        		y = y+1;
        		
        		//proper cell
        	}
        	
        	//if the enemy has  found a ship
        	// it shoots near this 
        	else {
        		
        		x=x-1;
        		y=y-1;
        		System.out.print("x: "+ y +" ");
        		System.out.println("y: "+ x);
        		//to shoot
        		x=x+1;
        		y=y+1;
        	}
        	
        	
        	//Select the Cell according to coordinates
            Cell cell = playerBoard.getCell(x, y);
            
            
            //if the cell Shot, pick another one 
            if (cell.wasShot) {
            	i_found = false;
            	continue;
            	
            } 
            
            
            // We save into array (Check "Board.java" 
            // all the movements of player so as to 
            // view in Details menu
            
            int index = playerBoard.total_shots;            
            	
            
            
            
            
  
            //Save line's coordinate
            playerBoard.shots_info[0][index]=y-1;
            //Save column's coordinate
            playerBoard.shots_info[1][index]=x-1;
            
            
            
            //Save 1 in the case of HIT and type of ship
            if (playerBoard.getCell(x, y).ship != null) {
            	
            	//HIT: Take the neighbors 
            	Cell [] a = playerBoard.getNeighbors(y,x);
            	
            	
            	//n1,n2: we keep next shot (in case of hit)
            	//it will be one neighbor
                int n1=0,n2=0;
                
                for (Cell element: a) {
                	//System.out.print("Next Shot: ");
                	//System.out.print(element.x-1);
                	//System.out.println(element.y-1);
                	n1 = element.x-1;
                	n2 = element.y-1;

                }
                
                //Keep the last element of the list 
                i_found = true;

                
            	//hit
            	playerBoard.shots_info[2][index]=1;
            	//type of ship 
            	playerBoard.shots_info[3][index] = playerBoard.getCell(x,y).ship.type;
            	
            	
            	//Next shot
                x = n2+1;
                y = n1+1;
                
              
                // Delete last element (which contains next shot)
                if (first) {
                	saved1 = Arrays.copyOf(a, a.length-1);
                	System.out.println("Length of array is:"+saved1.length);
                	first = false;
                }
                
                
            }
            	
            else {
            	// in the case of miss
            	// we load neighbors of last hit cell
            	if (i_found) {
            		int n11=0,n22=0;
                    for (Cell element: saved1) {
                    	n11 = element.x-1;
                    	n22 = element.y-1;

                    }

                    x = n22 +1;
                    y = n11 +1;
                    
                    //Delete this neighbor
                    
                    if (saved1.length>0)
                    	saved1 = Arrays.copyOf(saved1, saved1.length-1);
            	}
            	System.out.println("MISS");

            	//miss
            	playerBoard.shots_info[2][index]=0;
            	//type of ship (-1 means than non type: We don't use 0 since 0 declares type 0  )
            	playerBoard.shots_info[3][index] = -1;
            }
            	   
            
            // if the enemy shoot a ship, plays again 
            enemyTurn = cell.shoot();
            
            if (enemyTurn) {
            	System.out.println("Enemy plays again!");
            }
            
            
            //END OF GAME

            
            //Case 1: Remain Ships: 0 
            if (playerBoard.ships == 0) {
                system_log.setText("YOU LOSE ! Press Application -> Start/Load to play again ! ");
                game_ended=true;
            }
            
             //Case 2: Shots are 40 
            else if (playerBoard.total_shots == 40 ) {
            	int pl_pts,en_pts;
            	
            	pl_pts = enemyBoard.shoot_points;
            	en_pts = playerBoard.shoot_points;
            	
            	
            	if(pl_pts > en_pts) {
            		JOptionPane.showMessageDialog(null, "Enemy has not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You win!");
            		system_log.setText("Enemy has not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You win!");
            	}
            	else if (pl_pts < en_pts) {
            		JOptionPane.showMessageDialog(null, "Enemy has not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You lose!");
            		system_log.setText("Enemy has not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You lose!");
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Enemy has not any shots! Enemy_Points: "+en_pts+" Player Points"+pl_pts+" Ôie!!!");
            		system_log.setText("Enemy has not any shots! Enemy_Points: "+en_pts+" Player Points"+pl_pts+" Ôie!!!");
            	}
                game_ended = true;
                ShowSolution();
            }
            //Refresh GUI (Information about player)
            player_ships.setText(Integer.toString(playerBoard.ships));            
            player_points.setText(Integer.toString(playerBoard.shoot_points));     
            player_per.setText(Integer.toString(playerBoard.target_shots)+"/"+Integer.toString(playerBoard.total_shots));
        }
        
    }
    
    
    
    
    /**
    * The player is playing again if he shoots a ship.
    * <p>
    * The game ends if:
    * The remain ships are zero
    * The shots of a player are 40 
    * <p>
    * We also refresh GUI information in the upper stage of window
    */
    
    //PlayerMoveMethod
    
    public void playerMove() {
        while (!enemyTurn && key_pressed == true) {
        	
        	
        	//x_player,y_player are taken from FORM  
            int x = x_player;
            int y = y_player;
            
            
            //Increase by one (remember that we have labels in the first Cell of Each Row - Column 
            x = x+1;
            y = y+1;
            
            
            //Select the cell with these coordinates 
            Cell cell = enemyBoard.getCell(x, y);
            
            
            //If the Cell has been selected already
            if (cell.wasShot) {
            	if(cell.ship == null)
            		system_log.setText("You have already check this cell. Try another one");
            	key_pressed = false;
            	enemyTurn = false;
            	continue;
            }
            
            // We save into array (Check "Board.java" 
            // all the movements of player so as to 
            // view in Details menu
            int index = enemyBoard.total_shots;
  
            //Save line's coordinate
            enemyBoard.shots_info[0][index]=y-1;
            //Save column's coordinate
            enemyBoard.shots_info[1][index]=x-1;
            
            //Save 1 in case of HIT and type of ship
            if (enemyBoard.getCell(x, y).ship != null) {
            	//hit
            	enemyBoard.shots_info[2][index]=1;
            	//type of ship
            	enemyBoard.shots_info[3][index] = enemyBoard.getCell(x, y).ship.type;
            }
 
            //Save 0 in case of MISS and "-1" which means "none"
            else {
            	//miss
            	enemyBoard.shots_info[2][index]= 0;
            	//type of ship (-1 means than non type: We don't use 0 since 0 declares type 0  )
            	enemyBoard.shots_info[3][index] =-1;
            }
            	


            // if the player shoot a ship, plays again
            enemyTurn = !cell.shoot();
            if (!enemyTurn) {
            	system_log.setText("On target, it is your turn again! ");
            }
            
            
            
            //End of Game
            
            //Case 1: Remain Ships: 0 

            if (enemyBoard.ships == 0) {
                system_log.setText("YOU WIN ! Press Application -> Start/Load to play again ! ");
                game_ended = true;
            }
            
            
            //Case 2: Shots are 40 
            else if (enemyBoard.total_shots == 40 ) {
            	
            	int pl_pts,en_pts;
            	
            	pl_pts = enemyBoard.shoot_points;
            	en_pts = playerBoard.shoot_points;
            	
            	
            	if(pl_pts<en_pts) {
            		JOptionPane.showMessageDialog(null, "You have not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You lose!");
            		system_log.setText("You have not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You lose!");
            	}
            	else if (pl_pts > en_pts) {
            		JOptionPane.showMessageDialog(null, "You have not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You win!");
            		system_log.setText("You have not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" You win!");
            	}
            	else {
            		JOptionPane.showMessageDialog(null,"You have not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" Ôie!!!");
            		system_log.setText("You have not any shots! Enemy_Points: "+en_pts+" Player Points "+pl_pts+" Ôie!!!");
            	}
                game_ended = true;
                ShowSolution();
            }
            
            
            //Refresh GUI (Information about enemy)
            enemy_ships.setText(Integer.toString(enemyBoard.ships));
            enemy_points.setText(Integer.toString(enemyBoard.shoot_points));
            enemy_per.setText(Integer.toString(enemyBoard.target_shots)+"/"+Integer.toString(enemyBoard.total_shots));
  
        }        
        
    }
	
    
    
    /**
     * Defines the flow of the game
     */
    
	public void startGame() {
		
		if (!enemyTurn)
		{
    		playerMove();
    		key_pressed = false;
    		if (enemyTurn)
    		{
    			enemyMove();
    		}
		}
    }
	
	
	
	/**
	 * Initialization of Main Window
	 * Define who is playing first 
	 * Create Boards
	 * Read Scenario
	 */
	
	public void initialize ()
	{
		
		//Check who is playing first and inform player
		if (!enemyTurn) {
			system_log.setText("You are playing first! ");
		}
		else {
			system_log.setText("Enemy played first. It is your turn!");
		}
		
		//Create Boards
		
		enemyBoard =  new Board(true);
		playerBoard =  new Board(false);
		
		HBox hbox = new HBox(20, playerBoard, enemyBoard);
		hbox.setAlignment(Pos.CENTER);
		
		grid_id.setCenter(hbox);
	
	
		
		//Read Scenario
		String player_str = new String("player_"+scenario+".txt");
		ReadTxt(player_str, false);

		String enemy_str = new String("enemy_"+scenario+".txt");
		ReadTxt(enemy_str, true);
		
		//Initialize GUI information (enemy)
        enemy_ships.setText(Integer.toString(enemyBoard.ships));        
        enemy_points.setText(Integer.toString(enemyBoard.shoot_points));
        enemy_per.setText(Integer.toString(enemyBoard.target_shots)+"/"+Integer.toString(enemyBoard.total_shots));;
        
		//Initialize GUI information (player)
        player_ships.setText(Integer.toString(playerBoard.ships));        
        player_points.setText(Integer.toString(playerBoard.shoot_points));        
        player_per.setText(Integer.toString(playerBoard.target_shots)+"/"+Integer.toString(playerBoard.total_shots));
   
		
		
		if (enemyTurn) {
			enemyMove();
		}
		
	}
	
	
    @FXML
    private TextField coordinates;
    
    
    
    /**
     * Read data from TxtField (as string "2,2")
     * Separate string to comma ["2","2"]
     * Create a new integer array to save coordinates
     * Check if coordinates are in proper form
     * @see #AreValidCoordinates(String[])
     * 
     * 
     * @param event Press Shoot Button 
     */

    @FXML
    void ShootMethod(ActionEvent event) {
    	
    	
    	
    	
    	if (!game_ended) {
    		system_log.setText(" ");
    		
    	}
    	
    	// Read data from TxtField (as string "2,2")
    	String user_message = coordinates.getText();
    	coordinates.setText("");
    	
    	// Separate string to comma ["2","2"]
    	String[] parts = user_message.split(",");
    	
    	// Create a new integer array to save coordinates
		int[] ints = new int[parts.length];
		
		//Check Coordinates
		try {
			AreValidCoordinates(parts);
			
			// Convert string to integer [2,2] 
			for (int i = 0; i < parts.length; i++) {
				ints[i] = Integer.parseInt(parts[i]);
			}
			
			//changed !
			x_player = ints[1];
			y_player = ints[0];
	    	
	    	
	    	key_pressed = true;
	    	
	    	if (!game_ended)
	    		startGame();
		}
		catch (Exception m)
		{
			system_log.setText("Invalid Coordinates. Coordinates muste be in the form x,y ");
			System.out.println("Invalid Coordinates " + m);
		}
		
    }
    
    
    /**
     * Check if coordinates are in proper form.
     * Else throw Exception and notify user
     * 
     * @throws CoordinatesException
     * @param parts an array of strings
     */
    
    private boolean AreValidCoordinates (String[] parts) throws CoordinatesException {
    	if (parts.length == 2) 
    	{
    		if (parts[0].length() == 1 && parts[1].length() == 1)
    		{
    			char x1,x2;
    			x1=parts[0].charAt(0);
    			x2=parts[1].charAt(0);
    			int asc_num1 = x1;
    			int asc_num2 = x2;
    			if ((asc_num1 >= 48 && asc_num1 <= 57) && (asc_num2 >= 48 && asc_num2 <= 57))
    				return true;
    		}
    			
    	}
    	throw new CoordinatesException("");	
    }


}
