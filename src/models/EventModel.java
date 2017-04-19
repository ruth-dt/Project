

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;



public class EventModel {

	private static Date Date;
	private    Date  startDate;
	private   Date endDate;
	private  String  description;
	private String name;


	public EventModel(Date startDate, Date endDate, String name, String description) throws ParseException{


		/*   Date StartDate = new Date(07/8/2017);
          Date EndDate = new Date(04/05/2017);

          if(StartDate.after(EndDate)){
        	  throw new IllegalArgumentException();
          }else if(StartDate.after(EndDate)){
        	  System.out.println("correct");
          }*/

		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate =  endDate;


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

	
	
	public void StartDate(Date startDate ) throws ParseException{
		if(startDate.after(endDate)){
			throw new IllegalArgumentException();
		}else
			System.out.println("correct");
		


	}
	public void EndDate(Date endDate ) throws ParseException {

		if(endDate.before(startDate)){
			throw new IllegalArgumentException();
		}else 
       System.out.println("correct");
		
			

	}
}











