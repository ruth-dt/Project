import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

public class AppController {

	FileIO fio = new FileIO();
	AppModel model;

	@FXML
	VBox rootView;
	@FXML
	VBox timelineContainer;
	@FXML
	HBox dayNumberContainer;
	@FXML
	HBox monthNumberContainer;


	/**
	 * Method is called when user clicks the "New Timeline" in the "File" menu
	 * 
	 * @throws IOException
	 */
	public void addTimeline() throws IOException {
		new TimelinePopup(this.model); // Give the popup a reference to appModel
										// so that it can add the timeline
	}

	/**
	 * Method is called when the user clicks "Open" in the "File" menu
	 */
	public void openTimelines() {
		AppModel loadedModel = fio.load();
		
		if(loadedModel == null) //Cancelled
			return;
		
		model.removeAll();
		model = loadedModel;
		model.setController(this);
		model.getTimelineModelList().stream().forEach((tm) -> {
			tm.setParentApp(model);
			timelineAdded(tm);
			tm.getChildEvents().forEach((em) -> {
				em.setParentTimeline(tm);
				tm.getController().eventAdded(em);
			});
		});
	}

	/**
	 * Method is called when the user clicks "Save" in the "File" menu
	 */
	public void saveTimelines() {
		fio.save(model);
	}

	/**
	 * Method is called when the user clicks "Save as" in the "File" menu
	 */
	public void saveTimelinesAs() {
		fio.saveAs(model);
	}

	/**
	 * Gets the current AppModel that is connected to this controller
	 * 
	 * @return
	 */
	public AppModel getModel() {
		return model;
	}

	/**
	 * Sets the AppModel that is connected to this controller
	 * 
	 * @return
	 */
	public void setModel(AppModel appModel) {
		model = appModel;
	}

	/**
	 * Method is called when a timeline is removed from the AppModel Since the
	 * timeline view is _inside_ the app view, the App controller is responsible
	 * for removing it
	 * 
	 * @param timelineModel
	 */
	public void timelineRemoved(TimelineModel timelineModel) {
		// timelineContainer is the child element of this view that contains the
		// timeline views
		timelineContainer.getChildren().remove(timelineModel.getController().getView());
		adjustDateBar();
		model.getTimelineModelList().stream().forEach(x -> {
			x.getController().adjustMargin();
		});
	}

	/**
	 * Method is called when a timeline is added to the AppModel Since the
	 * timeline view is _inside_ the app view, the App controller is responsible
	 * for creating/adding it
	 * 
	 * @param timelineModel
	 */
	public void timelineAdded(TimelineModel timelineModel) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TimeLineView.fxml"));

		try {
			loader.load();
			TimelineController tc = loader.getController();

			timelineModel.setController(tc);
			tc.setModel(timelineModel);

			tc.modelUpdated();
			timelineContainer.getChildren().add(timelineModel.getController().getView());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adjustDateBar();
	}

	public void adjustDateBar() {
		dayNumberContainer.getChildren().clear();
		monthNumberContainer.getChildren().clear();
		
		if(model.getTimelineModelList().isEmpty())
			return;
		
		Date minStart = new Date(model.getMinStartDate());
		Date maxEnd = new Date(model.getMaxEndDate());

		Calendar cal = Calendar.getInstance();
		cal.setTime(minStart);
		Date curDate = new Date(minStart.getTime());

		int daysInMonth = 0;

		while (!curDate.after(maxEnd)) {
			Label dayNumberTemplate = new Label();
			dayNumberTemplate.setAlignment(Pos.CENTER);
			dayNumberTemplate.setPrefHeight(30);
			dayNumberTemplate.setPrefWidth(30);
			dayNumberTemplate.setMinHeight(Control.USE_PREF_SIZE);
			dayNumberTemplate.setMinWidth(Control.USE_PREF_SIZE);
			dayNumberTemplate.setMaxHeight(Control.USE_PREF_SIZE);
			dayNumberTemplate.setMaxWidth(Control.USE_PREF_SIZE);
			
			cal.setTime(curDate);
			dayNumberTemplate.setText( ((Integer) cal.get(Calendar.DAY_OF_MONTH)).toString() );
			dayNumberContainer.getChildren().add(dayNumberTemplate);

			daysInMonth++;

			int firstDayOfMonth = cal.get(Calendar.MONTH);
			int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

			if(cal.get(Calendar.DAY_OF_MONTH) == lastDayOfMonth ){

				Label monthNumberTemplate = new Label();
				monthNumberTemplate.setAlignment(Pos.CENTER);
				monthNumberTemplate.setPrefHeight(30);
				monthNumberTemplate.setMinHeight(Control.USE_PREF_SIZE);
				monthNumberTemplate.setMinWidth(Control.USE_PREF_SIZE);
				monthNumberTemplate.setMaxHeight(Control.USE_PREF_SIZE);
				monthNumberTemplate.setMaxWidth(Control.USE_PREF_SIZE);

				monthNumberTemplate.setPrefWidth(daysInMonth*30);
				daysInMonth = 0;

				monthNumberTemplate.setText( new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)] );
				monthNumberContainer.getChildren().add(monthNumberTemplate);



			}

			curDate.setTime(curDate.getTime() + 1000*60*60*24);

		}


		cal.setTime(curDate);


		Label monthNumberTemplate = new Label();
		monthNumberTemplate.setAlignment(Pos.CENTER);
		monthNumberTemplate.setPrefHeight(30);
		monthNumberTemplate.setMinHeight(Control.USE_PREF_SIZE);
		monthNumberTemplate.setMinWidth(Control.USE_PREF_SIZE);
		monthNumberTemplate.setMaxHeight(Control.USE_PREF_SIZE);
		monthNumberTemplate.setMaxWidth(Control.USE_PREF_SIZE);

		monthNumberTemplate.setPrefWidth(daysInMonth*30);

		monthNumberTemplate.setText( new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)] );
		monthNumberContainer.getChildren().add(monthNumberTemplate);

	}
	
	/**
	 * Gets the appview connected to this controller
	 * 
	 * @return
	 */
	public VBox getView() {
		return rootView;
	}
}
