import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class TimelineModel {

    TimelineController tc;

    private List <EventModel> eml;
    public Date startDate;
    public Date endDate;


    public TimelineModel( Date start , Date end){

        if(start.before(end)){
            startDate = start;
            endDate = end;
            eml = new ArrayList<EventModel>();
        }
    }


    public Date getStartDate(){
        return startDate;
    }


    public Date getEndDate(){
        return endDate;
    }


    public List<EventModel> getTimeLine(){
        return eml;
    }


    public void add(EventModel e){
        eml.add(e);
    }


    public void remove(EventModel e){
        eml.remove(e);
    }

    public void setController(TimelineController a){
        tc=a;
    }

    public TimelineController getController(){
        return tc;
    }

}
