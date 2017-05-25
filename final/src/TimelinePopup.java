import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TimelinePopup {

	private AppModel appModel;
	private TimelineModel timelineModel;
	
	/**
	 * When the popup is instantiated using an AppModel, this means that it should create a new TimelineModel
	 * @param appModel
	 */
	TimelinePopup(AppModel _appModel){
		appModel = _appModel;
		timelineModel = new TimelineModel();
		buildWindow();
	}
	
	/**
	 * When the popup is instantiated using a TimelineModel, this means that it is modifying an existing model 
	 * @param timelineModel
	 */
	TimelinePopup(TimelineModel _timelineModel){
		timelineModel = _timelineModel;
		buildWindow();
	}

	/**
	 * Create a new timeline popup window
	 */
	private void buildWindow(){
        FXMLLoader loader = new FXMLLoader( getClass().getResource("TimelinePopupView.fxml") );

        VBox root;
		try {
			root = loader.load();
	        ((TimelinePopupController) loader.getController()).setCallback(this::save);
	        ((TimelinePopupController) loader.getController()).setModel(this.timelineModel);

	        Stage stage = new Stage();
	        stage.setTitle("New Timeline");
	        stage.setScene(new Scene(root,415,250));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * If there is appModel, add a new timelineModel to appModel
	 */
	void save(){
		if(appModel != null){
			appModel.add(timelineModel);
		}else{
			timelineModel.getController().modelUpdated();
		}
	}

}
