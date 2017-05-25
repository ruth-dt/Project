import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "AppModel")
@XmlAccessorType(XmlAccessType.NONE)
public class AppModel {

	@XmlElement(name = "Timeline")
    private List<TimelineModel> timelineModelList = new ArrayList<TimelineModel>(); //An app can contain many timelines
    private AppController controller;

    /**
     * Sets the controller for this model
     * @param _controller
     */
    public void setController(AppController _controller){
    	controller=_controller;
    }
    
    /**
     * Gets the current controller for this model.
     * @return
     */
    public AppController getController() {
    	return controller;
    }

    /**
     * Adds a new timeline model to the timeline list.
     * @param timelineModel
     */
    public void add(TimelineModel timelineModel) {

        timelineModelList.add(timelineModel);	//Add timeline model to list
        timelineModel.setParentApp(this);		//Make the timeline model aware that it belongs to _this_ AppModel
        controller.timelineAdded(timelineModel);
        timelineModelList.stream().forEach(x -> {
            x.getController().adjustMargin();
        });
    }

    /**
     * Removes a timeline model from the timeline list.
     * @param timelineModel
     */
    public void remove(TimelineModel timelineModel) {
    	
    	timelineModelList.remove(timelineModel); //Remove timeline model from list
        timelineModel.setParentApp(null);		 //Make this model aware that it no longer belongs to any particular AppModel
        controller.timelineRemoved(timelineModel);	 //Notify (App)controller that this (Timeline)model has been removed so that it can remove the (Timeline)view
    }

    /**
     * Gets the minimum start date from timelines.
     * The minimum start date is for setting the timeline position.
     * @return
     */
    public long getMinStartDate(){    	
        return timelineModelList.stream().mapToLong(x -> {return x.getStartDate().getTime();}).min().getAsLong();
    }
    public long getMaxEndDate(){    	
    	return timelineModelList.stream().mapToLong(x -> {return x.getEndDate().getTime();}).max().getAsLong();
    }
    
    /**
     * Clears the TimelineModel list gracefully, notifying the controller about each removal
     */
    public void removeAll(){
    	timelineModelList.stream().forEach((tm) -> controller.timelineRemoved(tm));
    	timelineModelList.clear();
    }
    
    /**
     * Gets the timeline model list. Internal.
     * @return 
     */
    public List<TimelineModel> getTimelineModelList(){
    	return timelineModelList;
    }    
}





