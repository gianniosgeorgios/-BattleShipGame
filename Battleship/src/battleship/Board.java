package battleship;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


// Create our Exceptions 

class OversizeException extends Exception{  
	/**Use this option to add a user-defined ID
	 * in combination with custom serialization code 
     *  if the type did undergo structural  
     *  changes since its first release.
	 */
	private static final long serialVersionUID = 1L;

	OversizeException(String s){  
	  super(s);  
	 }  
	}

class OverlapTilesException extends Exception{  
	/**Use this option to add a user-defined ID
	 * in combination with custom serialization code 
     *  if the type did undergo structural  
     *  changes since its first release.
	 */
	private static final long serialVersionUID = 1L;

	OverlapTilesException(String s){  
	  super(s);  
	 }  
	}

class AdjacentTilesException extends Exception{  
	
	/**Use this option to add a user-defined ID
	 * in combination with custom serialization code 
     *  if the type did undergo structural  
     *  changes since its first release.
	 */
	private static final long serialVersionUID = 1L;

	AdjacentTilesException(String s){  
	  super(s);  
	 }  
	}

class InvalidCountException extends Exception{  
	/**Use this option to add a user-defined ID
	 * in combination with custom serialization code 
     *  if the type did undergo structural  
     *  changes since its first release.
	 */
	private static final long serialVersionUID = 1L;

	InvalidCountException(String s){  
	  super(s);  
	 }  
	}


public class Board extends Parent {
	
    //Total Ships in a Board
    public int ships = 5;
	
	//Declare names of all ships in a Board
	String[] names = {"Carrier", "Battleship", "Cruiser", "Submarine","Destroyer"};
	
	//One VBox contains ten HBox
    private VBox rows = new VBox();
    
    //Flag the turn 
    private boolean enemy = false;
    
    //The ships of Each player
	public Ship carrier_ship;
	public Ship battleship_ship;
	public Ship cruiser_ship;
	public Ship submarine_ship;
	public Ship destroyer_ship;
    
    //Count the (%) on target_shots, in order to print to GUI
    public int total_shots = 0;
    public int target_shots = 0;
    public int shoot_points = 0;
    
    //Shots info is an array of 40 columns (equal to shots)
    //In each column we save
    
    //1st Column: x - coordinate
    //2nd Column: y - coordinate
    //3rd Column: 0 in the case of MISS, 1 in the case of HIT
    //4th Column: Type of the target ship ([0,1,2,3,4] or -1 if none
    
    public int [][] shots_info =  new int [4][40];
    
    //In this list we keep which types of ships have been placed
    // in order to avoid duplicates
    List<Integer> types_placed = new ArrayList<Integer>();
    
    
    
    /**
    * One Board consist of one VBox. Each VBox contains 10 HBoxes
    * Each HBox represents a row and consists of 10 Cells
    * Each Cell represents either Label either Play board Cell
    * There are 10 Vertical and 10  Horizontal Labels (In which we can't shoot)
    * There are 100 (10 x 10) Cells in which we can shoot.
    *
    * @param  enemy  true if enemy, false if player
    */
    
    //Board CONSTRUCTOR
    public Board(boolean enemy) {
        this.enemy = enemy;
        for (int y = 0; y < 11; y++) {
        	
        	// Create HBox (Each HBox represents a row)
            HBox row = new HBox();
            
            //Labels of grid
            String[] letters = {"0", "1", "2", "3","4","5","6","7","8","9"};
            String[] digits =  {"0", "1", "2", "3","4","5","6","7","8","9"};
            
            //Create 10 Cells for each HBox (Each Cell represents either Label either Playboard Cell)
            for (int x = 0; x < 11; x++) {
                Cell c = new Cell(x, y, this);
               
                // Vertical Labels (In which we can't shoot)
                if (y == 0 && x > 0 ) {
                    Text text = new Text (letters[x-1]);
                    StackPane stack = new StackPane();
                    stack.getChildren().addAll(c, text);
                    stack.setLayoutX(30);
                    stack.setLayoutY(30);
                    
                    row.getChildren().add(stack);
                	
                }
                
                // Horizontal Labels (In which we can't shoot)
                else if (x == 0 && y > 0)
                {
                    Text text = new Text (digits[y-1]);
                    StackPane stack = new StackPane();
                    stack.getChildren().addAll(c, text);
                    stack.setLayoutX(30);
                    stack.setLayoutY(30);
                    
                    row.getChildren().add(stack);
                }
                //Simple Cells (In which we can shoot)
                else {
                	row.getChildren().add(c);
                }
            }
            //Add HBox to VBox
            rows.getChildren().add(row);
        }
        //Add VBox to Board
        getChildren().add(rows);
    }
    
    
    /**
    * Place one ship to proper position using coordinates
    * First, check if it possible. If yes,based on the orientation
    * place the ship
    * 
    * @param ship The ship object that will be placed
    * @param  x  Line's coordinate
    * @param  y  Column's coordinate
    * @return true only if the ship can be placed
    * @see #canPlaceShip(Ship, int, int)
    */
    
    
    //Place one ship to proper position using coordinates
    //only if the placement is proper
    public boolean placeShip(Ship ship, int x, int y) {
        
    	//Check if it possible
    	if (canPlaceShip(ship, x, y)) {
            int length = ship.length;
            
            
            //Vertical Orientation
            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }
            //Horizontal Orientation
            else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    
    /**
    * Place one ship to proper position using coordinates
    * First, check if it possible. If yes,based on the orientation
    * place the ship
    * @param  x  Line's coordinate
    * @param  y  Column's coordinate
    * @return Cell with these coordinates
    */
    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }
    
    
    
    /**
    * Take the neighbors of a Cell and keep only valid points (inside the grid)
    * First, take four neighbors, add them to a list only if they are valid 
    * 
    * @param  x  Line's coordinate
    * @param  y  Column's coordinate
    * @return Cell neighbors to Array list 
    * 
    * @see #isValidPoint(Point2D)
    */
    //Take the neighbors of a Cell 
    //and keep only valid points (inside the grid)
    public Cell[] getNeighbors(int x, int y) {
    	
    	//Take four neighbors
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };
        
        //Add neighbors to list 
        List<Cell> neighbors = new ArrayList<Cell>();

        //Only if there are valid (inside the grid)
        for (Point2D p : points) {
        	try 
        	{
        		isValidPoint(p);
        		neighbors.add(getCell((int)p.getX(), (int)p.getY()));
        	}
        	catch (Exception m)
        	{
        		
        	}
        }
        return neighbors.toArray(new Cell[0]);
    }
    
    
    /**
    * Check all (4) restrictions in placement
    * 1st Restriction: All ships must be of different types 
    * 2nd Restriction: Each ship must be inside the bounds
    * 3rd Restriction: In a cell, cannot be simultaneously two ship
    * 4th Restriction: Two ships cannot be neighboring (Distance at least 2) 
    * 
    * @param  x  Line's coordinate
    * @param  y  Column's coordinate
    * @param  ship  Ship object
    * @return true if the placement can be done  
    * 
    * @see #isDifferentTypes(int)
    * @see #isValidPoint(double, double)
    * @see #isShipThere(Cell)
    * @see #isAdjacent(Cell)
    */
    
    
    //Check all (4) restrictions in placement
    private boolean canPlaceShip(Ship ship, int x, int y) {

    	int type =ship.type;
    	
    	//////// 1st Restriction: All ships must be of different types ////////
    	try
    	{
        	isDifferentTypes(type);
    	}
    	catch (Exception m)
    	{
    		SampleController.game_ended = true;
    		JOptionPane.showMessageDialog(null, "Warning: All ships must be different types. Please load a proper scenario" );
    		System.out.println("All ships must be different types "+m);
    		return false;
    	}
    	//Add this type to the list of seen types
    	this.types_placed.add(type);
    	
    	
    	
    	
    	
    	//////// 2nd Restriction: Each ship must be inside the bounds ////////
    	int length = ship.length;

        if (ship.vertical) {
        	//Check if the ship fits inside the grid
            for (int i = y; i < y + length; i++) {
            	
            	try {
            		isValidPoint(x, i);
            	}
            	catch(Exception  m)
            	{
            		SampleController.game_ended = true;
            		JOptionPane.showMessageDialog(null, "Warning: "+ names[ship.type]+ " cannot be placed there vertically  (Oversize Excpetion). Please load a proper scenario" );
            		System.out.println("A ship (vertical) cannot placed due to: "+m);
            		return false;
            	}
            	
            	
            	
            	//////// 3rd Restriction: In a cell, cannot be simultaneously two ships ////////
            	Cell cell = getCell(x, i);
               
            	try
                {
                	isShipThere(cell);
                }
                catch(Exception m)
                {
                	SampleController.game_ended = true;
            		JOptionPane.showMessageDialog(null, "Warning: "+ names[ship.type]+ " cannot be placed there vertically  (Overlap Excpetion). Please load a proper scenario" );
            		System.out.println("A ship (vertical) cannot placed due to: "+m);
                	return false;
                }
                
                

                
                //////// 4th Restriction: Two ships cannot be neighboring (Distance at least 2) ////////
                for (Cell neighbor : getNeighbors(x, i)) {
                	try {
                		isValidPoint(x, i);
                	}
                	catch(Exception  m)
                	{
                		SampleController.game_ended = true;
                		JOptionPane.showMessageDialog( null, "Exception occured" );
                		System.out.println("1.Exception occured: "+m);
                		return false;
                	}
                    try
                    {
                    	isAdjacent(neighbor);
                    }
                    catch(Exception m ){
                    	SampleController.game_ended = true;
                		JOptionPane.showMessageDialog(null, "Warning: "+ names[ship.type]+ " cannot be placed there vertically  (AdjacentTiles Excpetion). Please load a proper scenario" );
                		System.out.println("A ship (vertical) cannot placed due to: "+m);
                    	return false;
                    }
                }
                
            }
        }
        
        
        
        // The same for horizontal placement
        else {
        	
        	//////// 2nd Restriction: Each ship must be inside the bounds ////////
            for (int i = x; i < x + length; i++) {
            	try
            	{
            		isValidPoint(i, y);
            	}
            	catch(Exception m)
            	{
            		SampleController.game_ended = true;
            		JOptionPane.showMessageDialog( null, "Warning: "+ names[ship.type]+ " cannot be placed there horizontally (Oversize Excpetion). Please load a proper scenario" );
            		System.out.println("A ship (horizontal) cannot placed due to: "+m);
            		return false;
            	}
            	

            	
            	
            	//////// 3rd Restriction: In a cell, cannot be simultaneously two ships ////////
                Cell cell = getCell(i, y);
                
                try
                {
                	isShipThere(cell);
                }
                catch(Exception m)
                {
                	SampleController.game_ended = true;
            		JOptionPane.showMessageDialog( null, "Warning: "+ names[ship.type]+ " cannot be placed there horizontally (Overlap Excpetion). Please load a proper scenario" );
            		System.out.println("A ship (horizontal) cannot placed due to: "+m);
                	return false;
                }
                

                //////// 4th Restriction: Two ships cannot be neighboring (Distance at least 2) ////////
                for (Cell neighbor : getNeighbors(i, y)) {
                	try
                	{
                		isValidPoint(i, y);
                	}
                	catch (Exception m)
                	{
                		SampleController.game_ended = true;
                		JOptionPane.showMessageDialog( null, "Exception occured" );
                		System.out.println("2.Exception occured: "+m);
                		return false;
                	}
                    

                    
                    try
                    {
                    	isAdjacent(neighbor);
                    }
                    catch(Exception m ){
                    	SampleController.game_ended = true;
                		JOptionPane.showMessageDialog( null, "Warning: "+ names[ship.type]+ " cannot be placed there horizontally (AdjacentTiles Excpetion). Please load a proper scenario" );
                		System.out.println("A ship (horizontal) cannot placed due to: "+m);
                    	return false;
                    	
                    }
                }
                
            }
        }
        return true;
    }
    
    
    /**
    * 1st Restriction in placement
    * @see #canPlaceShip(Ship, int, int)
    * 
    * @param  type  One cell in which the ship will be placed 
    * @return true if the placement can be done
    * 
    * @throws InvalidCountException: Each ship is from a different type
    * @see #types_placed
    */
    
    private boolean isDifferentTypes (int type) throws InvalidCountException {
    	if (!this.types_placed.contains(type))
    		return true;
    	else {
    		throw new InvalidCountException("InvalidCount Exception");
    	}
    }
    
    
    /**
    * 2nd Restriction in placement
    * @see #canPlaceShip(Ship, int, int)
    * 
    * @param  x  Line's coordinate
    * @param  y  Column's coordinate
    * @return true if the placement can be done
    * 
    * @throws OversizeException:  A ship cannot be out of range
    */
    
    
    // OversizeMethod: A ship cannot be out of range
    private boolean isValidPoint(double x, double y) throws OversizeException  {
    	if (x >= 1 && x <= 10 && y >= 1 && y <= 10)
    		return true;
    	else {
    		throw new OversizeException("Oversize Excpetion");
    	}
    }

    
    /**
    * 3rd Restriction in placement
    * @see #canPlaceShip(Ship, int, int)
    * 
    * @param  cell  One cell in which the ship will be placed 
    * @return true if the placement can be done
    * 
    * @throws OverlapTilesException:: A ship cannot be placed on other ship
    */
    
    private boolean isShipThere(Cell cell) throws OverlapTilesException{
    	if (cell.ship == null)
    		return true;
    	else {
    		throw new OverlapTilesException("OverlapTiles Excpetion");
    	}
    }
    
    
    
    /**
    * 4th Restriction in placement
    * @see #canPlaceShip(Ship, int, int)
    * 
    * @param  cell  One cell in which is in the neighborhood of another Cell
    * @return true if the placement can be done
    * 
    * @throws AdjacentTilesException: A ship cannot be placed by another 
    */
     
    private boolean isAdjacent(Cell neighbor) throws AdjacentTilesException{
    	if (neighbor.ship == null)
    		return true;
    	else
    		throw new AdjacentTilesException("AdjacentTiles Exception");
    		
    }
    
    /**
    * Check if a Point is a valid (We take advantage of Polymorphism)
    * @see #isValidPoint(double, double)
    * 
    * @param  point  One cell of Board
    * @return true if the placement can be done
    * 
    * @throws OversizeException:  A ship cannot be out of range
    */

    private boolean isValidPoint(Point2D point) throws Exception {
    	return isValidPoint(point.getX(), point.getY());
    }
    
    /**
    * Each square in Board is represented by a Cell.
    * The Cell inherits by Rectangle. All Cells are BLUE and
    * Label's Cells are WHITE
    * In the case of hit the Cell will be RED 
    * In the case of miss the Cell will be BLACK 
    */
    
    //Each square in Vertical Box is represented by a Cell
    public class Cell extends Rectangle {
    	
    	// Current position of a cell
        public int x, y;
        
        //Reference to ship
        public Ship ship = null;
        
        //Situation of the cell
        public boolean wasShot = false;
        
        //Reference to Board
        private Board board;
        
        //CONSTRUCTOR of a Cell
        public Cell(int x, int y, Board board) {
        	
        	//Call parent's CONSTRUCTOR to create Rectangle (30,30)
            super(25, 25);
            this.x = x;
            this.y = y;
            this.board = board;
            
            // If the Cell is Label 
            if (this.x == 0 || this.y == 0)
            {
                setFill(Color.WHITE);
                setStroke(Color.BLACK);
            }
            
            // If the Cell is not Label 
            else
            {
            	setFill(Color.DARKBLUE);
            	setStroke(Color.BLUE);
            }
        }
        
        

        
        /** 
         * First increase total shots.
         * In the case of HIT the Cell will be RED. Also increase points
         * If the ship sunk, then decrease total ships and add points of sinking
         * In the case of MISS  the Cell will be BLACK 
         * <p>
         * 
         * To sum up, in this method we also count
         * a. Total shots in a board
         * b. On target shots in a board 
         * c. Points of a player 
         * d. Remain ships of a player
         * 
         * @return true in case of hit
         */
        
        
        public boolean shoot() {
        	
        	//If one cell is shot, it becomes BLACK (total_shots ++)
        	wasShot = true;
            setFill(Color.BLACK);
            board.total_shots = board.total_shots +1;
            
            //If in one cell there is a ship:
            if (ship != null) {
            	
            	// total shots in a board ++
            	board.target_shots = board.target_shots + 1;
            	
                //Increase points
                board.shoot_points = board.shoot_points + ship.on_target_shoot;
            	
                //If one cell is shot, it becomes RED (total_shots ++)
                ship.hit();
                setFill(Color.RED);
                
                
                //If the ship sink with this shot:
                if (!ship.isAlive()) {
                	//Increase points of sinking
                	board.shoot_points = board.shoot_points + ship.final_shoot;
                	//Decrease ships
                    board.ships--;
                }
                return true;
            }
            return false;
        }
    }
}