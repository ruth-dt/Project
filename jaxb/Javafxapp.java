package jaxb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Javafxapp extends Application{
	

	public void start(Stage primaryStage) throws JAXBException {
	
		Group root = new Group();
		AppModel ap=new AppModel();
		EventModel em= new EventModel(new Date(0), new Date(10),"event","653u9866123123123bfdsfdgdfsdfsdafsadsffsgfadsfasdfdfasfasfd");
		//TimelineModel tml = new TimelineModel(new Date(0), new Date(10));
	//	ap.add(tml);
		//tml.add(em);
  
        Button btn2 = new Button();
        btn2.setText("save");
        btn2.setPrefSize(70, 20);
        btn2.setOnAction((ActionEvent event)->{
        	
	        	  FileIO io = new FileIO();
	              io.jaxbObjectToXML(ap, new File("savehere.xml"));
        	
        	
        });
		  
		Button btn1 = new Button();
		btn1.setText("save as a file");
		btn1.setPrefSize(70, 20);
		btn1.setOnAction((ActionEvent event)->{
			FileChooser fileChooser = new FileChooser();
			
	      
	          FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	          fileChooser.getExtensionFilters().add(extFilter);

	          File file = fileChooser.showSaveDialog(primaryStage);
	          System.out.println("file is " + file.getName());
	          if(file != null){
	        	  FileIO io = new FileIO();
	              io.jaxbObjectToXML(ap, file);
              }

			
			
			
		});
		
		
		Button btn = new Button();
		btn.setText("Choose one file");
		btn.setPrefSize(70, 20);
		
		btn.setOnAction((ActionEvent event)->{
			    FileChooser chooser = new FileChooser();
			    chooser.setTitle("Open File");
			    File data1 = chooser.showOpenDialog(primaryStage);
			    try{
					
					Scanner st=new Scanner(data1);
			       
			        
			      
				st.close();
				}catch(Exception ioe){
					System.err.println("We can not find this file!please set right path");
				}
			    
			

			
		});
                

		
		// Cut for space
	

	
		HBox hbox = new HBox();
		hbox.getChildren().addAll(btn,btn1,btn2);
		
		
		Scene scene = new Scene(hbox, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

		
	}

	/*private void SaveFile(String content, File file) {
		 try {
	            FileWriter fileWriter = null;
	             
	            fileWriter = new FileWriter(file);
	            fileWriter.write(content);
	            fileWriter.close();
	        } catch (IOException ex) {
	            Logger.getLogger(Javafxapp.class.getName()).log(Level.SEVERE, null, ex);
	        }
	         
	    }
		*/
	

	public static void main(String[] args) {
		launch(args);

	}
	

}



