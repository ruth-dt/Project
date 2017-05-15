import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class TimelineModel {
	// FIXME validate model on all changes

	private AppModel parentApp; // The AppModel that this TimelineModel belongs
								// to. Can be null.
	private TimelineController controller;
	private List<EventModel> eventModelList = new ArrayList<EventModel>(); // A
																			// timeline
																			// can
																			// contain
																			// many
																			// events

	// The actual timeline properties
	private Date startDate;
	private Date endDate;
	private String name;

	TimelineModel() {

	}

	/**
	 * FIXME this methos is not used
	 */
	public TimelineModel(Date _startDate, Date _endDate, String _name) {

		if (_startDate.before(_endDate)) {
			startDate = _startDate;
			endDate = _endDate;
			name = _name;
		} else {
			System.err.println("FIXME: TimelineModel validation failed");
		}
	}

	/**
	 * Gets the name of the timeline
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the timeline
	 * 
	 * @param _name
	 */
	public void setName(String _name) {
		name = _name;
		if (controller != null)
			controller.modelUpdated();
	}

	/**
	 * Sets the start date of the timeline
	 * 
	 * @param _startDate
	 */
	public void setStartDate(Date _startDate) {
		startDate = _startDate;
		if (controller != null)
			controller.modelUpdated();
	}

	/**
	 * Gets the startDate of the timeline
	 * 
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the end date of the timeline
	 * 
	 * @param _endDate
	 */
	public void setEndDate(Date _endDate) {
		endDate = _endDate;

		if (controller != null)
			controller.modelUpdated();

		eventModelList.stream().forEach(x -> {
			x.getController().adjustMargin();
		});
	}

	/**
	 * Gets the end date of the current timeline
	 * 
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Adds a new event model to the event list.
	 * 
	 * @param eventModel
	 */
	public void add(EventModel eventModel) {
		eventModelList.add(eventModel);
		eventModel.setParentTimeline(this);
		if (controller != null)
			controller.eventAdded(eventModel);
	}

	/**
	 * Removes an event model to the event list.
	 * 
	 * @param eventModel
	 */
	public void remove(EventModel eventModel) {
		eventModelList.remove(eventModel);
		eventModel.setParentTimeline(null);
		if (controller != null)
			controller.eventRemoved(eventModel);
	}

	/**
	 * Sets the controller for this model
	 * 
	 * @param _controller
	 */
	public void setController(TimelineController _controller) {
		controller = _controller;
	}

	/**
	 * Gets the current controller for this model.
	 * 
	 * @return
	 */
	public TimelineController getController() {
		return controller;
	}

	/**
	 * Sets the app which this timeline belongs to
	 * 
	 * @return
	 */
	public void setParentApp(AppModel appModel) {
		parentApp = appModel;
	}

	/**
	 * Gets the app which this timeline belongs to
	 * 
	 * @return
	 */
	public AppModel getParentApp() {
		return parentApp;
	}

}
