package JavaFXDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;



public class EventModel {
	
	private   Date  startDate;
	private  Date endDate;
	private  String  description;
	private String name;

	
	public EventModel(Date startDate, Date endDate, String name, String description) throws ParseException{
		
          
          Date StartDate = new Date(07/8/2017);
          Date EndDate = new Date(04/05/2017);
          
          if(startDate.after(EndDate)){
        	  throw new IllegalArgumentException();
          }else if(startDate.after(EndDate)){
        	  System.out.println("correct");
          }
        	  
		this.name = name;
		  this.description = description;
		  this.startDate = startDate;
		  this.endDate =  endDate;
		  
		  
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
	public void setStartDate(Date sd){
		this.startDate = sd;
		
	if(startDate.after(endDate)){
		throw new IllegalArgumentException();
	}else 
		System.out.println("the date is correct");
		
		
	}
	public void setEndDate(Date ed){
		this.endDate  = ed;
		if(endDate.before(startDate)){
			throw new IllegalArgumentException();
			
		}else 
			System.out.println("the date is correct");
	}

     
	
	
	
		
		
		
	}
	

