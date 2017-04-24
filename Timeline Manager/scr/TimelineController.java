import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TimelineController {
    @FXML
    VBox rootView;
    TimelineModel tm;

    public TimelineController(){

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
