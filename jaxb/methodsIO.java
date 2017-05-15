package jaxb;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class methodsIO {

	public void SaveAs(AppModel am){
		
		FileChooser chooser = new FileChooser();
		
		
	    chooser.getExtensionFilters().addAll(
	    new ExtensionFilter("Text Files","txt"),
	    new ExtensionFilter("Image Files","*.png", "*.jpg","*gif"),
	    new ExtensionFilter("Audio Files","*.mp3","*.aac"),
	    new ExtensionFilter("All Files","*.*"));
	    
	    File file = chooser.showOpenDialog(null);		
	    if(file != null){
		/*Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		FileIO io1 = new FileIO();
		io1.jaxbObjectToXML(am, file);
		
	}
	}
	public AppModel load(){
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(null);
		
		if(file != null){
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				while((line = br.readLine()) != null){
					System.out.println(line);
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
				
			}
		
			/*Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
			
		}
		System.out.print("FILE IS"+ file);
		FileIO io = new FileIO();
		return io.jaxbXMLToObject(file);
		//return (AppModel)load();
		
		
		
	}
}
