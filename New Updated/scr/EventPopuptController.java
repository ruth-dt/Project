import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.ZoneId;
import java.util.Date;

public class EventPopuptController {

    EventModel eventModel;
    Runnable callback;

    @FXML
    VBox rootView;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    TextField name;
    @FXML
    TextField description;

    public EventPopuptController(){
    }

    /**
     * When click the save button, this eventModel is updated
     */
    public void save(){

        String _name = name.getCharacters().toString();
        String _description = description.getCharacters().toString();
        Date start = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        eventModel.setName(_name);
        eventModel.setDescription(_description);
        eventModel.setEndDate(end);
        eventModel.setStartDate(start);

        ((Stage) rootView.getScene().getWindow()).close();//Finished creating new event, window closed
        callback.run();
    }

    /**
     * Closes window without update this eventModel
     */
    public void cancel(){
        ((Stage) rootView.getScene().getWindow()).close();
    }

    public void setCallback(Runnable _callback){
        callback = _callback;
    }

    public void setModel(EventModel _eventModel){
        eventModel = _eventModel;
    }

}
