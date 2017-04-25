import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
public class NewTimelineController {

    AppController owner;
    @FXML
    VBox rootView;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    TextField timelineName;

    public NewTimelineController(){

    }
    public void save() throws IOException {

        String name = timelineName.getCharacters().toString();
        Date start = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());


        TimelineModel timelineModel = new TimelineModel(start,end,name);
        owner.newTimelineWindowCallback(timelineModel);

        ((Stage) rootView.getScene().getWindow()).close();

    }

    public void cancel(){
    	//FIXME
    }

    public void setOwner(AppController _owner){
        owner=_owner;
    }







}
