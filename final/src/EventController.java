import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;


public class EventController {

    EventModel model;
    boolean detailsVisible = true;

    @FXML
    HBox rootView;
    @FXML
    Label name;
    @FXML
    Label startDate;
    @FXML
    Label endDate;
    @FXML
    Label description;
    @FXML
    HBox eventDetails;
    @FXML
    Pane eventBar;
    @FXML
    Pane emptySpace;
    @FXML
    VBox exceptSpacer;
    @FXML
    HBox buttonsContainer;
    @FXML
    HBox parentNameButtons;

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
        if(detailsVisible){
            exceptSpacer.setStyle(" -fx-border-color:  transparent; -fx-background-color: transparent");
            exceptSpacer.getChildren().remove(eventDetails);
            parentNameButtons.getChildren().remove(buttonsContainer);
            detailsVisible = false;
        }else{

            exceptSpacer.setStyle("-fx-border-color:  #575757; -fx-background-color: rgba(244,244,244,1)");
            exceptSpacer.getChildren().add(eventDetails);
            parentNameButtons.getChildren().add(buttonsContainer);
            detailsVisible = true;
        }
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
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String sdate = simpleDateFormat.format(model.getStartDate());
        startDate.setText(sdate);
        String edate = simpleDateFormat.format(model.getEndDate());
        endDate.setText(edate);
        description.setText(model.getDescription());
        adjustSize();
        adjustMargin();
        if(detailsVisible){
            showDetails();
        }

    }

    /**
     * Resize the PrefWidth of eventBar
     */
    public void adjustSize(){
        long diff = model.getEndDate().getTime() - model.getStartDate().getTime() + 1000*60*60*24;
        double diffDays = diff/(1000*60*60*24);
        eventBar.setPrefWidth(Math.max(diffDays*30,1));
    }

    /**
     * Resize the prefWidth of emptySpace in front of eventBar
     */
    public void adjustMargin(){
        long diff = model.getStartDate().getTime() - model.getParentTimeline().getStartDate().getTime();
        double diffDays = diff/(1000*60*60*24);
        emptySpace.setPrefWidth(diffDays*30-2);

    }

    /**
     * Gets the eventView connected to this controller
     * @return
     */
    public HBox getView(){
        return rootView;
    }
	
}
