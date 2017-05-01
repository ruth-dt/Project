import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
public class TimelinePopupController {
    @FXML
    VBox rootView;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    TextField timelineName;
    
    Runnable callback;
    TimelineModel timelineModel;

    public TimelinePopupController(){}
    public void save() throws IOException {

        String name = timelineName.getCharacters().toString();
        Date start = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        timelineModel.setName(name);
        timelineModel.setEndDate(end);
        timelineModel.setStartDate(start);

        ((Stage) rootView.getScene().getWindow()).close();
        callback.run();
    }

    public void cancel(){
        ((Stage) rootView.getScene().getWindow()).close();
    }

    public void setCallback(Runnable _callback){
    	callback = _callback;
    }
    public void setModel(TimelineModel _timelineModel){
    	timelineModel = _timelineModel;
    }







}
