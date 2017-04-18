package models;

import java.util.ArrayList;
import java.util.List;

public class AppModel {
	private List<TimelineModel> tml;
	AppController c; // ???

	public AppModel() {
		tml = new ArrayList<TimelineModel>();
	}

	public void setApp(List<TimelineModel> newTml) {
		this.tml = newTml;
	}

	public List<TimelineModel> getApp() {
		return tml;
	}

	public void add(TimelineModel tm) {
		tml.add(tm);
	}

	public void remove(TimelineModel tm) {
		tml.remove(tm);
	}
}
