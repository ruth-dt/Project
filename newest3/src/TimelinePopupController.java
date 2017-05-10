import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

	public TimelinePopupController() {

	}

	/**
	 * When click the save button, this timelineModel is updated
	 */
	public void save() throws IOException {

		String name = timelineName.getCharacters().toString();
		Date start;
		Date end;

		try {
			start = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			end = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		} catch (Exception e) {
			invalidInput();
			return;
		}
		if (end.before(start) || name.length() == 0) {
			invalidInput();
			return;
		}

		timelineModel.setName(name);
		timelineModel.setEndDate(end);
		timelineModel.setStartDate(start);

		((Stage) rootView.getScene().getWindow()).close();
		callback.run();
	}

	private void invalidInput() {
		new Alert(Alert.AlertType.WARNING, "Invalid input").show();
	}

	/**
	 * Closes window without update this timelineModel
	 */
	public void cancel() {
		((Stage) rootView.getScene().getWindow()).close();
	}

	public void setCallback(Runnable _callback) {
		callback = _callback;
	}

	public void setModel(TimelineModel _timelineModel) {
		timelineModel = _timelineModel;
		Date sdate = timelineModel.getStartDate();
		Date edate = timelineModel.getEndDate();

		if (sdate != null && edate != null) {
			timelineName.setText(timelineModel.getName());
			Instant instant = Instant.ofEpochMilli(sdate.getTime());
			startDate.setValue(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
			Instant instant2 = Instant.ofEpochMilli(edate.getTime());
			endDate.setValue(LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate());
		}
	}

}
