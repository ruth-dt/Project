import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EventController {

    EventModel model;

    @FXML
    HBox rootView;
    @FXML
    Text name;
    @FXML
    Text startDate;
    @FXML
    Text endDate;
    @FXML
    Text description;
    @FXML
    HBox eventDetails;
    @FXML
    Pane eventBar;
    @FXML
    Pane emptySpace;
    @FXML
    VBox exceptSpacer;

    /**
     * Sets the EventModel that is connected to this controller
     */
    public void setModel(EventModel eventModel) {
        model = eventModel;
    }

    /**
     * Gets the EventModel that is connected to this controller
     * @return
     */
    public EventModel getModel(){
        return model;
    }

    /**
     * Edits an event
     */
    public void edit(){
        new EventPopup(this.model);
    }

    /**
     * Removes an event
     */
    public void remove(){
        model.getParentTimeline().remove(model);
    }

    /**
     * Shows(add) the event details window
     */
    public void showDetails(){
        exceptSpacer.getChildren().add(eventDetails);
    }

    /**
     * Closes(remove) the event details window
     */
    public void closeDetails(){
        exceptSpacer.getChildren().remove(eventDetails);
    }

    /**
     * fixme this method is not used
     */
    public void detialPopupClickHandler(){
        new EventPopup(model);
    }

    /**
     * Method is called when startDate, endDate, description, and the event bar are changed
     */
    public void modelUpdated() {
        name.setText(model.getName());
        String sdate = model.getStartDate().toString();
        startDate.setText(sdate);
        String edate = model.getEndDate().toString();
        endDate.setText(edate);
        description.setText(model.getDescription());
        adjustSize();
        adjustMargin();
        exceptSpacer.getChildren().remove(eventDetails); //The eventDetails is removed when a new event is created
    }

    /**
     * Resize the PrefWidth of eventBar
     */
    public void adjustSize(){
        long diff = model.getEndDate().getTime() - model.getStartDate().getTime();
        double diffDays = diff/(1000*60*60*24);
        eventBar.setPrefWidth(diffDays*30);
    }

    /**
     * Resize the prefWidth of emptySpace in front of eventBar
     */
    public void adjustMargin(){
        long diff = model.getStartDate().getTime() - model.getParentTimeline().getStartDate().getTime();
        double diffDays = diff/(1000*60*60*24);
        emptySpace.setPrefWidth(diffDays*30);

    }

    /**
     * Gets the eventView connected to this controller
     * @return
     */
    public HBox getView(){
        return rootView;
    }
	
}
