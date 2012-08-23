package jsignalml.compiler;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;

public class TestCompiledClass {
	static final String source =
		"public class HelloWorld {\n" +
		"    public String toString() {\n" +
		"        return \"'Hello, World!' by \" + \n" +
		"             getClass().getSimpleName();\n" +
		"    }\n" +
		"}\n";

	@Test
	public void testCompilation()
		throws Exception
	{
		CompiledClass klass = new CompiledClass("HelloWorld", source);
		Object instance = klass.newInstance();
		assertEquals(instance.toString(),
			     "'Hello, World!' by HelloWorld");
	}

	@Test
	public void testCompilationInPackage()
		throws Exception
	{
		String source2 = "package goo.goo.goo;\n\n" + source;
		CompiledClass klass = new CompiledClass("goo.goo.goo.HelloWorld",
							source2);
		Object instance = klass.newInstance();
		assertEquals(instance.toString(),
			     "'Hello, World!' by HelloWorld");
	}

	@Test(dataProvider="dependency")
	public void testCompilationRequiringPackages(final String dependency)
		throws Exception
	{
		String source2 = "import " + dependency + ";\n\n" + source;
		CompiledClass klass = new CompiledClass("HelloWorld", source2);
		Object instance = klass.newInstance();
		assertEquals(instance.toString(),
			     "'Hello, World!' by HelloWorld");
	}

	public static final String[] DEPENDENCIES = {
		"java.io.ByteArrayOutputStream",
		"org.apache.log4j.BasicConfigurator",
		"jsignalml.logging.Logger",
	};

	@DataProvider
	public static Object[][] dependency() {
		Object[][] ar = new Object[DEPENDENCIES.length][];
		for(int i=0; i<ar.length; i++)
			ar[i] = new Object[] { DEPENDENCIES[i] };
		return ar;
	}

	@Test(dataProvider="level")
	public void testNestedCompilation(Integer level)
		throws Exception
	{
		final String
			fmt = "Goo%03d%03d",
			opening = "public class " + fmt + " {\n",
			clname = String.format(fmt, level, 0),
			// Class name is 9 chars, or 10 with the period
			closing = "}\n";

		StringBuilder source = new StringBuilder();
		for(int i=0; i<level; i++)
			source.append(String.format(opening, level, i));
		for(int i=0; i<level; i++)
			source.append(closing);
		CompiledClass klass = new CompiledClass(clname, source);
		final Object instance;
		instance = klass.newInstance();
		assertEquals(instance.getClass().getSimpleName(), clname);
	}

	public static final int[] LEVELS = {
		1, 2, 3, 4, 5, 6, 10, 20, 30, 50, 100, 300};

	@DataProvider
	public static Object[][] level() {
		Object[][] ar = new Object[LEVELS.length][];
		for(int i=0; i<ar.length; i++)
			ar[i] = new Object[] { new Integer(LEVELS[i]) };
		return ar;
	}
}
