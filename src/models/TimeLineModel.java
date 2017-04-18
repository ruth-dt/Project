import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class TimeLineModel {

    private List <EventModel> eml;
    public TimelineController c;
    public Date startDate;
    public Date endDate;


    public TimeLineModel(){
        eml = new ArrayList<EventModel>();
    }


    public setTimeLine(List<EventModel> neweml, int startY, int startM, int startD, int endY, int endM, int endD){
        Date start = new Date(startY,startM,startD);
        Date end = new Date(endY,endM,endD);

        if(start.before(end)){
            startDate = start;
            endDate = end;
        }
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


}
