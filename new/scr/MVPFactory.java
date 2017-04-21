import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Date;

public class MVPFactory {


    public AppModel appFactory() throws IOException {
        AppModel appModel = new AppModel();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("AppView.fxml")
        );

        VBox root = loader.load();

        AppController ac = loader.getController();


        ac.setAppModel(appModel);

        appModel.setController(ac);
        return appModel;
    }

    public TimelineModel TimelineFactory(Date startDate, Date endDate) throws IOException {
        TimelineModel timelineModel = new TimelineModel(startDate,endDate);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TimeLineView.fxml")
        );

        VBox root = loader.load();

        TimelineController tc = loader.getController();

        tc.setTimelineModel(timelineModel);

        timelineModel.setController(tc);

        return timelineModel;


    }


    public EventModel EventFactory (){
        throw new NotImplementedException();

    }
}
