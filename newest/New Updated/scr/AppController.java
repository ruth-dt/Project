import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class AppController {

	AppModel model;

	@FXML
	VBox rootView;
	@FXML
	VBox timelineContainer;
	@FXML
	HBox dayNumberContainer;


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
		System.out.print("ll43554345ll");
	}

	/**
	 * Method is called when the user clicks "Save" in the "File" menu
	 */
	public void savaTimelines() { // FIXME name spelling
		System.out.print("lxvccvxvclijlkxllllll");
	}

	/**
	 * Method is called when the user clicks "Save as" in the "File" menu
	 */
	public void savaTimelinesAs() { // FIXME name spelling
		System.out.print("lxvccvxvc908890xllllll");
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
		Date minStart = new Date(model.getMinStartDate());
		Date maxEnd = new Date(model.getMaxEndDate());
		
		Date curDate = new Date(minStart.getTime());
		
		Calendar cal = Calendar.getInstance();
		
		while (!curDate.after(maxEnd)) {
			Label dayNumberTemplate = new Label();
			dayNumberTemplate.setAlignment(Pos.CENTER);
			dayNumberTemplate.setPrefHeight(20);
			dayNumberTemplate.setPrefWidth(20);
			dayNumberTemplate.setMinHeight(Control.USE_PREF_SIZE);
			dayNumberTemplate.setMinWidth(Control.USE_PREF_SIZE);
			dayNumberTemplate.setMaxHeight(Control.USE_PREF_SIZE);
			dayNumberTemplate.setMaxWidth(Control.USE_PREF_SIZE);
			
			cal.setTime(curDate);
			dayNumberTemplate.setText( ((Integer) cal.get(Calendar.DAY_OF_MONTH)).toString() );
			dayNumberContainer.getChildren().add(dayNumberTemplate);
			
			curDate.setTime(curDate.getTime() + 1000*60*60*24);
		}
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
