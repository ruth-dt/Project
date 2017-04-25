import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class AppController {

    AppModel model;

    @FXML
    VBox rootView;
    @FXML
    VBox timelineContainer;

    void newTimelineWindowCallback(TimelineModel timelineModel) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TimeLineView.fxml")
        );
        loader.load();
        TimelineController tc = loader.getController();
        timelineModel.setController(tc);
        tc.setModel(timelineModel);
        tc.modelUpdated();

		model.add(timelineModel);
    }

    /**
     * Method is called when user clicks the "New Timeline" in the "File" menu
     * @throws IOException
     */
    public void addTimeline() throws IOException {
    	//FIXME make this new TimelinePopup(callback)
        FXMLLoader loader = new FXMLLoader( getClass().getResource("NewTimelineView.fxml") );

        VBox root = loader.load();
        ((NewTimelineController) loader.getController()).setOwner(this);

        Stage stage = new Stage();
        stage.setTitle("New Timeline");
        stage.setScene(new Scene(root,415,250));
        stage.show();
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
     * Since the timeline view is _inside_ the app view, the App controller is responsible for adding it
     * @param timelineModel
     */
    public void timelineAdded(TimelineModel timelineModel){
    	//timelineContainer is the child element of this view that contains the timeline views
    	timelineContainer.getChildren().add(timelineModel.getController().getView());
    }
    
    /**
     * Gets the appview connected to this controller
     * @return
     */
    public VBox getView(){
    	return rootView;
    }
}
