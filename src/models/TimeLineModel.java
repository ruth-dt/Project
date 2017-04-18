import java.util.List;
import java.util.ArrayList;

public class TimeLineModel {

    private List <EventModel> eml;

    public TimelineController c;

    private int sD;
    private int sM;
    private int sY;

    private int eD;
    private int eM;
    private int eY;


    public TimeLineModel(){
        eml = new ArrayList<EventModel>();
    }


    public setTimeLine(List<EventModel> neweml, int startD, int startM, int startY, int endD, int endM, int endY){

        if((startY+startM+startD)<(endY+endM+endD)){
            sD=startD;
            sM=startM;
            sY=startY;

            eD=endD;
            eM=endM;
            eY=endY;
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
