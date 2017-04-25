import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application{
	private AppModel appModel;
	
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        appModel = new AppModel();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("AppView.fxml")
        );

        VBox root = loader.load();

        AppController ac = loader.getController();


        ac.setModel(appModel);
        appModel.setController(ac);

        Scene scene = new Scene(appModel.getController().getView(), 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
