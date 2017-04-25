import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class TimelineModel {

    TimelineController tc;

    private List <EventModel> eml;
    public Date startDate;
    public Date endDate;
    public String name;


    public TimelineModel( Date start , Date end, String _name){

        if(start.before(end)){
            startDate = start;
            endDate = end;
            eml = new ArrayList<EventModel>();
            name = _name;
        }
    }

    public void setName(String n){

        name = n;
        tc.modelUpdated();
    }

    public String getName(){
        return name;
    }


    public void setStartDate(Date s){
        startDate = s;
        tc.modelUpdated();


    }

    public void setEndDate(Date e){
        endDate = e;
        tc.modelUpdated();

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
