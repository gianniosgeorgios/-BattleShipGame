package battleship;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
* This window shows up when we press Application /Load
* It contains a form in which we type scenario 
* example if we press "george" the scenario 
* player_george.txt and enemy_george.txt will be loaded
* 
* If the scenario does not exist, then an Exception is thrown
* 
* @see SampleController#LoadMethod(ActionEvent)
*/

public class LoadController {
	

    @FXML
    private TextField input_scenario;
    @FXML
    private Button close;

    @FXML
    void LoadButton(ActionEvent event) {
    	
    	//Read the scenario
    	String scenario = input_scenario.getText();
    	//Save it
    	SampleController.scenario = scenario;
    	//And close window
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();

    }
    
	public void initialize ()
	{
		
	}
    
    

}
