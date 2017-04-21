import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Date;

public class AppController {

    AppModel appModel;

    MVPFactory mvp = new MVPFactory();

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
        TimelineModel timelineModel = mvp.TimelineFactory(new Date(0),new Date(1000*3600*24));
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