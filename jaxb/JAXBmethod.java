package jaxb;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import jaxb.AppModel;
import jaxb.EventModel;
import jaxb.TimelineModel;
public class JAXBmethod {

	public static void main(String[] args) throws JAXBException, ParseException {  
		AppModel ap=new AppModel();
		EventModel em= new EventModel(new Date(0), new Date(10),"event","abc123123123123123123");
		TimelineModel tml = new TimelineModel(new Date(0), new Date(10));
		ap.add(tml);
		tml.add(em);
        JAXBContext context = JAXBContext.newInstance(AppModel.class);  
         
        Marshaller marshaller = context.createMarshaller();  
       
        marshaller.marshal(ap, System.out);  
        System.out.println();  
   
        System.out.println(ap.toString()); 
        FileIO io = new FileIO();
        io.jaxbObjectToXML(ap, new File("savehere.xml"));
    }  
}

