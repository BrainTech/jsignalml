package jsignalml;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * This is mostly to test my understanding of method overloading and to serve as
 * a guide for people reading the classes implementing visitor patterns.
 */
public class TestJavaMethodOverloading {
	private class A {
		String call(){ return "A"; }
	}

	private class B extends A {
		String call(){ return "B"; }
	}

	@Test public void method_resolution_is_dynamic() {
		A a = new B();
		B b = new B();

		assertEquals(a.call(), "B");
		assertEquals(b.call(), "B");
	}

	private String func1(A a){ return "A"; }
	private String func1(B b){ return "B"; }

	@Test public void overloading_is_static() {
		A a = new B();
		B b = new B();

		assertEquals(func1(a), "A");
		assertEquals(func1(b), "B");
	}
}
