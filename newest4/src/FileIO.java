import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class FileIO {
	private File lastFile;
	
	/**
	 * Method is called with current AppModel when a user clicks "save" in the menu
	 * If lastFile is null (no file has been opened or saved to), then saveAs is called instead
	 * @param am
	 */
	public void save(AppModel am){
		if(lastFile != null)
			jaxbObjectToXML(am, lastFile);
		else
			saveAs(am);
	}
	/**
	 * Method is called with current AppModel when a user clicks "Save as" in the menu
	 * A File Chooser is opened allowing the user to select where to save the .tlp (Timeline Project) file
	 * @param am
	 */
	public void saveAs(AppModel am) {
		FileChooser chooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Timeline Project Filest (*.tlp)", "*.tlp");
		chooser.getExtensionFilters().add(extFilter);

		File file = chooser.showSaveDialog(null);

		if(file == null)
			return; //Cancelled
		
		lastFile = file;
		jaxbObjectToXML(am, file);
	}

	/**
	 * Method is called when a user clicks "Open" in the menu (via AppController)
	 * A file chooser is shown, allowing the user to open a .tlp file
	 * @return
	 */
	public AppModel load() {
		FileChooser chooser = new FileChooser();
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Timeline Project Files (*.tlp)", "*.tlp");
		chooser.getExtensionFilters().add(extFilter);
		
		File file = chooser.showOpenDialog(null);
		
		if(file == null)
			return null; //Cancelled
		
		lastFile = file;
		return jaxbXMLToObject(file);
	}

	private AppModel jaxbXMLToObject(File loadthisfile) {
		try {
			JAXBContext context = JAXBContext.newInstance(AppModel.class);
			Unmarshaller un = context.createUnmarshaller();
			AppModel emp = (AppModel) un.unmarshal(loadthisfile);
			return emp;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("I/O Error");
			alert.setContentText("An error was encountered while loading the file: " + e.getMessage());
			alert.showAndWait();
		}
		return null; //Fixme
	}

	private void jaxbObjectToXML(AppModel emp, File loadthisfile) {

		try {
			JAXBContext context = JAXBContext.newInstance(AppModel.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(emp, loadthisfile);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("I/O Error");
			alert.setContentText("An error was encountered while saving the file: " + e.getMessage());
			alert.showAndWait();
		}
	}
}
