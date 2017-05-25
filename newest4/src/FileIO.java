
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import javafx.stage.FileChooser;

public class FileIO {
	private File lastFile;
	
	public void save(AppModel am){
		if(lastFile != null)
			jaxbObjectToXML(am, lastFile);
		else
			saveAs(am);
	}
	public void saveAs(AppModel am) {
		FileChooser chooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.tlp)", "*.tlp");
		chooser.getExtensionFilters().add(extFilter);

		File file = chooser.showSaveDialog(null);
		lastFile = file;
		jaxbObjectToXML(am, file);
	}

	public AppModel load() {
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(null);
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.tlp)", "*.tlp");
		chooser.getExtensionFilters().add(extFilter);
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
			//e.printStackTrace();
			//User cancelled
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
			//e.printStackTrace();
			//User cancelled
		}
	}
}
