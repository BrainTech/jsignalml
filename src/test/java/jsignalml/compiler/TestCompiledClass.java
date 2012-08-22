package jsignalml.compiler;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class TestCompiledClass {
	static final String source =
		"public class HelloWorld {\n" +
		"    public String toString() {\n" +
		"        return \"'Hello, World!' by \" + \n" +
		"             getClass().getSimpleName();\n" +
		"    }\n" +
		"}\n";

	public void testCompilation()
		throws Exception
	{
		CompiledClass klass = new CompiledClass("HelloWorld", source);
		Object instance = klass.newInstance();
		assertEquals(instance.toString(),
			     "'Hello, World!' by HelloWorld");
	}

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
}
