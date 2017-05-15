package jaxb;

import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class testingsaveas extends Application{

	
	public void start(Stage primaryStage) throws Exception {
		methodsIO tmp= new methodsIO();
		
		AppModel ap=new AppModel();
		EventModel em= new EventModel(new Date(0), new Date(10),"event","·ÉÈ÷·¢653u986612312312asfdfadsfasfasdfasdfsadfsfsfdfsad3bfdsfdgdfsdfsdafsadsffsgfadsfasdfdfasfasfd");
		TimelineModel tml = new TimelineModel(new Date(0), new Date(10));
		ap.add(tml);
		tml.add(em);
		
		

		Button btn = new Button();
		btn.setText("SaveAs");
		btn.setPrefSize(70, 20);

		btn.setOnAction((ActionEvent event)->{
			
			tmp.SaveAs(ap);

		});
		
		Button btn1 = new Button();
		btn1.setText("load");
		btn1.setPrefSize(70, 20);
		
	
		

		btn1.setOnAction((ActionEvent event)->{
			AppModel am = tmp.load();
			System.out.println(" ");
			am.getChildTimelines().stream().map((t) -> {return t.getStartDate().toString();}).forEach(System.out::println);
			
			

		});

		VBox root = new VBox();
		root.setPrefSize(350, 240);
		root.getChildren().addAll(btn,btn1);
		
        Scene scene1 = new Scene(root, 350, 240);
		primaryStage.setScene(scene1);
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
	launch(args);
	}
	
	
		
		
	
	}