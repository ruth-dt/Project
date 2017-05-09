import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDateTime;
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
	@FXML
	CheckBox rangedEventCheckBox;
	@FXML
	Label startDateLabel;
	@FXML
	Label endDateLabel;
	@FXML
	VBox inputContainer;

	public EventPopuptController() {
	}

	/**
	 * When click the save button, this eventModel is updated
	 */
	public void save() {

		String _name = name.getCharacters().toString();
		String _description = description.getCharacters().toString();
		Date start;
		Date end;

		try {
			start = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		} catch (Exception e) {
			invalidInput();
			return;
		}
		if(!rangedEventCheckBox.isSelected()){
			end = start;
		}else{
			try {
				end = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			} catch (Exception e) {
				invalidInput();
				return;
			}
		}

		if (end.before(start) || _name.length() == 0 || _description.length() == 0 
				|| eventModel.getParentTimeline().getStartDate().after(start) || eventModel.getParentTimeline().getEndDate().before(end) || 
				eventModel.getParentTimeline().getEndDate().before(start)) {
			invalidInput();
			return;
		}

		eventModel.setName(_name);
		eventModel.setDescription(_description);
		eventModel.setEndDate(end);
		eventModel.setStartDate(start);

		((Stage) rootView.getScene().getWindow()).close();// Finished creating
															// new event, window
															// closed
		callback.run();
	}

	private void invalidInput() { 
		new Alert(Alert.AlertType.WARNING, "Invalid input").show();
	}

	/**
	 * Closes window without update this eventModel
	 */
	public void cancel() {
		((Stage) rootView.getScene().getWindow()).close();
	}

	public void setCallback(Runnable _callback) {
		callback = _callback;
	}

	public void setModel(EventModel _eventModel) {
		eventModel = _eventModel;
		
		Date sdate = eventModel.getStartDate();
		Date edate = eventModel.getEndDate();

		if (sdate != null && edate != null) {
			name.setText(eventModel.getName());
			description.setText(eventModel.getDescription());
			Instant instant = Instant.ofEpochMilli(sdate.getTime());
			startDate.setValue(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
			Instant instant2 = Instant.ofEpochMilli(edate.getTime());
			endDate.setValue(LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate());
		
			if (edate.equals(sdate)){
				rangedEventCheckBox.setSelected(false);
				onCheckboxAction();
			}
		}
	}

	public void onCheckboxAction() {
		if (rangedEventCheckBox.isSelected()) {
			inputContainer.getChildren().addAll(endDateLabel, endDate);
		} else
			inputContainer.getChildren().removeAll(endDateLabel, endDate);
	}

}
