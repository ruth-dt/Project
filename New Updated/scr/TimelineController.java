import com.sun.jdi.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class TimelineController {

    TimelineModel model;

    @FXML
    HBox rootView;
    @FXML
    Text name;
    @FXML
    Text startDate;
    @FXML
    Text endDate;
    @FXML
    VBox eventContainer;

    @FXML
    Pane emptySpace;

    /**
     * An event popup window shows up when click add event button
     */
    public void addEventClickHandler(){
        new EventPopup(model);
    }

    /**
     * A timeline popup window shows up when click edit timeline button
     */
    public void editClickHandler(){
    	new TimelinePopup(this.model);
    }

    /**
     * A timeline is removed when click remove button
     */
    public void deleteClickHandler(){
    	model.getParentApp().remove(model);
    }

    /**
     * Sets the timelineModel that is connected to this controller
     */
    public void setModel(TimelineModel timelineModel){
        model = timelineModel;
    }

    /**
     * Gets the timelineModel that is connected to this controller
     * @return
     */

    public TimelineModel getModel(){
    	return model;
    }
	
    /**
     * Method is called the timeline name, start- or enddate is changes
     */
    public void modelUpdated(){
        name.setText(model.getName());
        String sdate = model.getStartDate().toString();
        startDate.setText(sdate);
        String edate = model.getEndDate().toString();
        endDate.setText(edate);
        adjustSize();
        adjustMargin();
    }

    /**
     * Resize the PrefWidth of timeline
     */
    public void adjustSize(){
        long diff = model.getEndDate().getTime() - model.getStartDate().getTime();
        double diffDays = diff/(1000*60*60*24);
        eventContainer.setPrefWidth(diffDays*30);
    }

    /**
     * Resize the PrefWidth of the empty space before timeline
     */
    public void adjustMargin(){
        long diff = model.getStartDate().getTime() - model.getParentApp().getMinStartDate();
        double diffDays = diff/(1000*60*60*24);
        emptySpace.setPrefWidth(diffDays*30);

    }

    public void eventRemoved(EventModel eventModel){
        //timelineContainer is the child element of this view that contains the timeline views
        eventContainer.getChildren().remove(eventModel.getController().getView());
    }

    /**
     * Adds a new eventBar inside timeline
     */
    public void eventAdded(EventModel eventModel){
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("EventView.fxml")
        );

        try {
            loader.load();
            EventController ec = loader.getController();
            eventModel.setController(ec);
            ec.setModel(eventModel);
            ec.modelUpdated();
            eventContainer.getChildren().add(eventModel.getController().getView());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Gets the timeline view connected to this controller
     * @return
     */
    public HBox getView(){
        return rootView;
    }
}
