
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventPopup {

    private TimelineModel timelineModel;
    private EventModel eventModel;

    /**
     * When the popup is instantiated using a TimelineModel, this means that it is modifying an existing model
     * @param timelineModel
     */
    EventPopup(TimelineModel _timelineModel){
        timelineModel = _timelineModel;
        eventModel = new EventModel();
        eventModel.setParentTimeline(timelineModel);
        buildWindow();
    }

    /**
     * When the popup is instantiated using an AppModel, this means that it should create a new TimelineModel
     * @param eventModel
     */
    EventPopup(EventModel _eventModel){
        eventModel = _eventModel;
        buildWindow();
    }

    /**
     * Create a new event popup window
     */
    private void buildWindow(){
        FXMLLoader loader = new FXMLLoader( getClass().getResource("EventPopupView.fxml") );

        VBox root;
        try {
            root = loader.load();
            ((EventPopuptController) loader.getController()).setCallback(this::save);

            ((EventPopuptController) loader.getController()).setModel(this.eventModel);
            Stage stage = new Stage();
            stage.setTitle("New Event");
            stage.setScene(new Scene(root,415,250));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If there is timelineModel, add eventModel to timelineModel
     */
    void save(){
        if(timelineModel != null){
            timelineModel.add(eventModel);
        } else {
        	eventModel.getController().modelUpdated();
        }
    }

}
