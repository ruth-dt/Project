
import java.text.ParseException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlAccessorType(XmlAccessType.NONE)
public class EventModel {

	@XmlElement(name = "StartDate")
	private Date startDate;
	@XmlElement(name = "EndDate")
	private Date endDate;
	@XmlElement(name = "Description")
	private String description;
	@XmlElement(name = "Name")
	private String name;
	
	private EventController controller;
	private TimelineModel parentTimeline;

	public void setParentTimeline(TimelineModel timelineModel) {
		parentTimeline = timelineModel;
	}

	/**
	 * Gets the app which this timeline belongs to
	 * 
	 * @return
	 */
	public TimelineModel getParentTimeline() {
		return parentTimeline;
	}

	/**
	 * fixme this method is not used
	 */
	public EventModel(Date _startDate, Date _endDate, String name, String description) throws ParseException {

		if (_startDate.before(_endDate)) {

			this.name = name;
			this.description = description;
			startDate = _startDate;
			endDate = _endDate;
		}
	}

	public EventModel() {
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setName(String _name) {
		name = _name;
		if (controller != null)
			controller.modelUpdated();
	}

	public void setDescription(String d) {
		this.description = d;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setController(EventController _controller) {
		controller = _controller;
	}

	public EventController getController() {
		return controller;
	}

}
