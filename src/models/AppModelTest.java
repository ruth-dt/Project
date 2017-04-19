package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AppModelTest {
	TimelineModel first;
	TimelineModel second;
	List<TimelineModel> tml;
	AppModel app;

	@Before
	public void setUp() {

		first = new TimelineModel();
		second = new TimelineModel();
		tml = new ArrayList<TimelineModel>();
		tml.add(first);
		tml.add(second);
		app = new AppModel();
		app.setApp(tml);
	}

	@Test
	public void testAdd() {
		tml.add(first);
		assertEquals(app.getApp(), tml);
		
		for (int i = 0; i < 10000; i++) {
			tml.add(second);	
		}
		assertTrue(!tml.isEmpty());
		assertEquals(tml.size(), 10003);
	}

	@Test
	public void testRemove() {
		tml.remove(first);
		assertEquals(app.getApp(), tml);
		tml.remove(second);
		assertEquals(app.getApp(), tml);
		
		for (int i = 0; i < 10000; i++) {
			tml.add(second);	
		}
		assertTrue(!tml.isEmpty());
		assertEquals(tml.size(), 10000);
		
		for (int i = 0; i < 5000; i++) {
			tml.remove(second);	
		}
		assertEquals(tml.size(), 5000);
		
	}
}

