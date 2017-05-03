import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class AppController {

    AppModel model;

    @FXML
    VBox rootView;
    @FXML
    VBox timelineContainer;

    /**
     * Method is called when user clicks the "New Timeline" in the "File" menu
     * @throws IOException
     */
    public void addTimeline() throws IOException {
    	new TimelinePopup(this.model); //Give the popup a reference to appModel so that it can add the timeline
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
    public void savaTimelines() { //FIXME name spelling
        System.out.print("lxvccvxvclijlkxllllll");
    }

    /**
     * Method is called when the user clicks "Save as" in the "File" menu
     */
    public void savaTimelinesAs() { //FIXME name spelling
        System.out.print("lxvccvxvc908890xllllll");
    }

    /**
     * Gets the current AppModel that is connected to this controller
     * @return
     */
    public AppModel getModel() {
        return model;
    }
    
    /**
     * Sets the AppModel that is connected to this controller
     * @return
     */
    public void setModel(AppModel appModel) {
        model = appModel;
    }
    
    /**
     * Method is called when a timeline is removed from the AppModel
     * Since the timeline view is _inside_ the app view, the App controller is responsible for removing it
     * @param timelineModel
     */
    public void timelineRemoved(TimelineModel timelineModel){
    	//timelineContainer is the child element of this view that contains the timeline views
    	timelineContainer.getChildren().remove(timelineModel.getController().getView());
    }
    
    /**
     * Method is called when a timeline is added to the AppModel
     * Since the timeline view is _inside_ the app view, the App controller is responsible for creating/adding it
     * @param timelineModel
     */
    public void timelineAdded(TimelineModel timelineModel){
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TimeLineView.fxml")
        );
        
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
    }
    
    /**
     * Gets the appview connected to this controller
     * @return
     */
    public VBox getView(){
    	return rootView;
    }
}
