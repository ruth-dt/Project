import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
		
		if (end.before(start) || name.length() == 0 || 
				(timelineModel.getChildEvents().size() != 0 && timelineModel.getChildEvents().stream().map((EventModel event) -> {return event.getStartDate();}).min(Date::compareTo).get().before(start))
				|| (timelineModel.getChildEvents().size() != 0 && timelineModel.getChildEvents().stream().map((EventModel event) -> {return event.getEndDate();}).max(Date::compareTo).get().after(end))) {
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
		updateLimits();
	}

	/**
	 * Gray out dates that before maxEventEndDate and after minEventStartDate
	 * Gray out startDates after endDates and endDates before startDates
	 */
	public void updateLimits(){
		LocalDate minEventStartDate = DateHelper.DateToLocalDate(timelineModel.getMinStartDate());
		LocalDate maxEventEndDate = DateHelper.DateToLocalDate(timelineModel.getMaxEndDate());

		final Callback<DatePicker, DateCell> sdayCellFactory =new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell(){
					@Override
					public void updateItem(LocalDate item, boolean empty){
						super.updateItem(item, empty);

						if((endDate.getValue() != null && item.isAfter(endDate.getValue())) ||
								(minEventStartDate != null && item.isAfter(minEventStartDate)) ||
								(maxEventEndDate != null && item.isAfter(maxEventEndDate)) ){
							setDisable(true);
							setStyle("-fx-background-color: #a6a6a6");
						}
					}
				};
			}
		};
		startDate.setDayCellFactory(sdayCellFactory);

		final Callback<DatePicker, DateCell> edayCellFactory =new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell(){
					@Override
					public void updateItem(LocalDate item, boolean empty){
						super.updateItem(item, empty);

						if((startDate.getValue() != null && item.isBefore(startDate.getValue())) ||
								(minEventStartDate != null && item.isBefore(minEventStartDate)) ||
								(maxEventEndDate != null && item.isBefore(maxEventEndDate)) ){

							setDisable(true);
							setStyle("-fx-background-color: #a6a6a6");
						}
					}
				};
			}
		};
		endDate.setDayCellFactory(edayCellFactory);
	}

}
