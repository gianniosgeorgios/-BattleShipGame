package battleship;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;



public class BattleshipMain extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	 // Just load FXML file of the Start Window, and display it in the stage:
        Parent root = FXMLLoader.load((getClass().getResource("Sample.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Medialab Battleship");;
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
