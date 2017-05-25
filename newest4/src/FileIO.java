import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileIO {

	public void saveAs(AppModel am) {

		FileChooser chooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		chooser.getExtensionFilters().add(extFilter);

		File file = chooser.showSaveDialog(null);
		if (file != null) {

			jaxbObjectToXML(am, file);

		}
	}

	public AppModel load() {
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(null);

		if (file != null) {

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}

		}
		System.out.print("FILE IS" + file);
		return jaxbXMLToObject(file);
	}

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

	public void jaxbObjectToXML(AppModel emp, File loadthisfile) {

		try {
			JAXBContext context = JAXBContext.newInstance(AppModel.class);
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
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
