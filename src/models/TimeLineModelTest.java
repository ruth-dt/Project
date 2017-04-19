import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TimeLineModelTest {

    List<EventModel> e;
    EventModel E;
    EventModel F;
    EventModel G;
    EventModel I;

    @Test
    void add() {

        e=new ArrayList<>();
        E=new EventModel();
        F=new EventModel();
        G=new EventModel();
        I=new EventModel();

        TimeLineModel test = new TimeLineModel();
        test.setTimeLine(e,2008,01,01,2018,01,01);

        //add one
        test.add(E);
        System.out.println("add test ");
        System.out.println("Size after adding 1 EventModel:  " + (test.getTimeLine()).size());

        // add many
        for (int i=0; i<10000; i++){
            test.add(E);
            test.add(F);
            test.add(G);
        }

        System.out.println("Size after adding 30000 more EventModel:  "+(test.getTimeLine()).size());

    }

    @Test
    void remove() {

        e=new ArrayList<>();
        E=new EventModel();
        F=new EventModel();
        G=new EventModel();
        I=new EventModel();

        TimeLineModel test = new TimeLineModel();
        test.setTimeLine(e,2008,01,01,2018,01,01);

        for (int i=0; i<10000; i++){
            test.add(E);
            test.add(F);
            test.add(G);
        }

        System.out.println("------------------------------");
        System.out.println("remove test");
        System.out.println("Size after adding 30000 EventModel:  "+(test.getTimeLine()).size());

        test.remove(E);
        System.out.println("Size after remove one EventModel:  "+(test.getTimeLine()).size());

        //remove many
        test.remove(E);
        test.remove(E);
        test.remove(E);
        System.out.println("Size after remove 3 more EventModel:  "+(test.getTimeLine()).size());
        int i=6000;
        while(i>0){
            test.remove(E);
            i--;
        }
        System.out.println("Size after remove 6000 more EventModel:  "+(test.getTimeLine()).size());

        //remove non-existent
        test.remove(I);
        System.out.println("Size after remove non-existent EventModel:  "+(test.getTimeLine()).size());
    }

}