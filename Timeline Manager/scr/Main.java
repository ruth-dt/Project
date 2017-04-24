import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class Main extends Application{
    MVPFactory mvp = new MVPFactory();
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        AppModel am = mvp.appFactory();

        Scene scene = new Scene(am.getController().getRoot(), 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
