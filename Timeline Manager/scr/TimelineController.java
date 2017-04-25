import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class TimelineController {

    @FXML
    VBox rootView;
    @FXML
    Text name;
    @FXML
    Text startDate;
    @FXML
    Text endDate;


    TimelineModel model;



    public void addEventClickHandler(){

    }

    public void editClickHandler(){

    }

    public void deleteClickHandler(){
    	model.getParentApp().remove(model);
    }

    public void setModel(TimelineModel timelineModel){
        model = timelineModel;

    }
    public TimelineModel getModel(){
    	return model;
    }

    public VBox getView(){
        return rootView;
    }

	public void eventAdded(EventModel eventModel){}
	public void eventRemoved(EventModel eventModel){}
	
    /**
     * Method is called the timeline name, start- or enddate is changes
     */
    public void modelUpdated(){
        name.setText(model.getName());
        String sdate = model.getStartDate().toString();
        startDate.setText(sdate);
        String edate = model.getEndDate().toString();
        endDate.setText(edate);
    }
}
