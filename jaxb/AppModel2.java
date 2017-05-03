package jaxb;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "AppModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppModel2 {

	@XmlElement(name = "tml")
    private List<TimelineModel> tml;
   /* AppController c ;

    public void setController(AppController a){
        c=a;
    }

    public AppController getController(){
        return c;
    }*/

    public AppModel2() {
        tml = new ArrayList<TimelineModel>();
    }

    public void setApp(List<TimelineModel> newTml) {
        this.tml = newTml;
    }

    public List<TimelineModel> getApp() {
        return tml;
    }

    public void add(TimelineModel tm) {
        tml.add(tm);
    }

    public void remove(TimelineModel tm) {
        tml.remove(tm);
    }

}
