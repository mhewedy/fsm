package mhewedy.fsm.test;

import static mhewedy.fsm.Manager.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MyTestCase {

	List<Student> list = new ArrayList<>();
	City city = new City("Cairo");

	@Before
	public void setUp() throws Exception {
		list.add(new Student(100));
		list.add(new Student(90));
		list.add(new Student(80));
	}

	@Test
	public void testGetNull() {
		MyState myState = MyState.STATE_A;
		MyState outState = 
				given(myState)
				.in(MyState.STATE_B, MyState.STATE_C)
				.then(MyState.STATE_D)
				.get();
		
		assertNull(outState);
	}

	@Test
	public void testOfProxyObjectCache() {

		City cairo = new City("Cairo");
		City london = new City("London");

		assertEquals(of(cairo), of(cairo));
		assertEquals(of(london), of(london));
		assertNotEquals(of(cairo), of(london));
	}
	
	@Test
	public void testOfProxyListCache() {
		
		List<Student> stdList1 = new ArrayList<>();
		stdList1.add(new Student(100));
		stdList1.add(new Student(90));
		
		List<Student> stdList2 = new ArrayList<>();
		stdList2.add(new Student(10));
		stdList2.add(new Student(90));
		
		assertEquals(of(stdList1), of(stdList1));
		assertEquals(of(stdList2), of(stdList2));
		assertNotEquals(of(stdList1), of(stdList2));
		
	}
	

	// ------------- some data structure

	enum MyState {
		STATE_A, STATE_B, STATE_C, STATE_D
	}

	static class Student {
		int grade;

		Student(int grade) {
			this.grade = grade;
		}
	}

	static class City {
		String location;

		City(String location) {
			this.location = location;
		}
	}
}