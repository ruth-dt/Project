package JavaFXCode;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class App extends Application{

	public static void main(String[] args) {
		launch(args);
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FileChooser chooser = new FileChooser();
		AppModel ap = new AppModel();
		EventModel em = new EventModel(new Date(0),new Date(10),"event","dfsgsdfgsfgdfg");
		TimelineModel tml = new TimelineModel(new Date(0),new Date(0));
		ap.add(tml);
		tml.add(em);
		
		chooser.getExtensionFilters().addAll(
		    new ExtensionFilter("Text Files","txt"),
		    new ExtensionFilter("Image Files","*.png", "*.jpg","*gif"),
		    new ExtensionFilter("Audio Files","*.mp3","*.aac"),
		    new ExtensionFilter("All Files","*.*"));



		Button btn = new Button("SaveAS");
		
		
		btn.setOnAction(e->{
			File file = chooser.showOpenDialog(primaryStage);			if(file != null){
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		Button button = new Button("Load");
		button.setOnAction(e->{
			File file = chooser.showOpenDialog(primaryStage);
			if(file != null){
				
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line = null;
					while((line = br.readLine()) != null){
						System.out.println(line);
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					
				}
			
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
			
			
			
				
			
			
			});
		VBox layout = new VBox();
		layout.setPadding(new Insets(10,10,10,10));
		layout.getChildren().addAll(button,btn);
		Scene scene = new Scene(layout,400,300);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
