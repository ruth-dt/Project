import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class AppController {

    AppModel appModel;

    @FXML
    VBox rootView;
    @FXML
    VBox timelineContainer;

    public VBox getRoot() {
        return rootView;
    }

    public void addTimeline() throws IOException {
        /*
        * In the completed application, this part should open
        * a popup that allows the user to input start and end date
        * */
/*
        TimelineModel timelineModel = new TimelineModel(new Date (0),new Date(0));

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TimeLineView.fxml")

        );

        */

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("NewTimelineView.fxml")
        );

        VBox root = loader.load();
        ((NewTimelineController) loader.getController()).setOwner(this);

        Stage stage = new Stage();
        stage.setTitle("New Timeline");
        stage.setScene(new Scene(root,415,250));
        stage.show();


        /*
        timelineContainer.getChildren().add(timelineModel.getController().getView());
        appModel.add(timelineModel);
        */


    }

    void newTimelineWindowCallback(TimelineModel timelineModel) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TimeLineView.fxml")
        );
        loader.load();
        TimelineController tc = loader.getController();
        timelineModel.setController(tc);
        tc.setTimelineModel(timelineModel);
        tc.modelUpdated();


        timelineContainer.getChildren().add(timelineModel.getController().getView());
        appModel.add(timelineModel);
    }

    public void openTimelines() {
        System.out.print("ll43554345ll");
    }

    public void savaTimelines() {
        System.out.print("lxvccvxvclijlkxllllll");
    }

    public void savaTimelinesAs() {
        System.out.print("lxvccvxvc908890xllllll");
    }


    public AppModel getAM() {
        return appModel;
    }

    public void setAppModel(AppModel a) {
        appModel = a;

    }
}