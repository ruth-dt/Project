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


    TimelineModel tm;

    public TimelineController(){

    }

    public void modelUpdated(){
        name.setText(tm.getName());
        String sdate = tm.getStartDate().toString();
        startDate.setText(sdate);
        String edate = tm.getEndDate().toString();
        endDate.setText(edate);
    }

    public void addEventClickHandler(){

    }

    public void editClickHandler(){

    }

    public void deleteClickHandler(){

    }

    public void setTimelineModel(TimelineModel a){
        tm = a;

    }

    public VBox getView(){
        return rootView;
    }



}
