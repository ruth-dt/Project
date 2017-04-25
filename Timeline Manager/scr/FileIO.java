package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileIO {

	 public AppModel jaxbXMLToObject(File loadthisfile) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(AppModel.class);
	            Unmarshaller un = context.createUnmarshaller();
	            AppModel emp = (AppModel) un.unmarshal(loadthisfile);
	            return emp;
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	    public void jaxbObjectToXML(AppModel emp,File loadthisfile) {

	        try {
	            JAXBContext context = JAXBContext.newInstance(AppModel.class);
	            Marshaller m = context.createMarshaller();
	            //for pretty-print XML in JAXB
	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	            // Write to System.out for debugging
	           m.marshal(emp, System.out);

	            // Write to File
	            m.marshal(emp, loadthisfile);
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
	    }
}