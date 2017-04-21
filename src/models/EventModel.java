
import java.text.ParseException;
import java.util.Date;


public class EventModel {

    private   Date  startDate;
    private   Date endDate;
    private  String  description;
    private String name;


    public EventModel(Date _startDate, Date _endDate, String name, String description) throws ParseException{

		if(_startDate.before(_endDate)) {

            this.name = name;
            this.description = description;
            startDate = _startDate;
            endDate = _endDate;
        }

    }

    public EventModel() {
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public void setName(String n){
        this.name = n;
    }
    public void setDescription(String d){
        this.description= d;
    }
    public void setStartDate(Date startDate){
        this.startDate= startDate;
    }
    public void setEndDate(Date endDate){
        this.endDate= endDate;
    }
}
