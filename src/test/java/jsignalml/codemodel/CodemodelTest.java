import java.io.*;
import com.sun.codemodel.*;
import com.sun.codemodel.writer.*;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class CodemodelTest {
	@Test
	public void testTrivialGeneration() throws Exception {
		JCodeModel codeModel = new  JCodeModel();
		JDefinedClass foo = codeModel._class( "a.b.c.Foo" ); //Creates a new class

		JMethod method = foo.method( JMod.PUBLIC, Void.TYPE, "doFoo" ); //Adds a method to the class
		JBlock body = method.body();
		JVar var = body.decl(JMod.NONE, codeModel.DOUBLE, "x",
				   JExpr.lit(42).plus(JExpr.lit(-15)));
		JVar str = body.decl(JMod.NONE, codeModel._ref(String.class), "s",
				     JExpr.lit("googoo\\" + '"'));

		JVar locvar = body.decl(JMod.NONE, codeModel.DOUBLE, "y", JExpr.direct("Double.SIZE"));

		JType list_t = codeModel._ref(java.util.List.class);
		JInvocation list = JExpr._new(list_t);
		list.arg(JExpr.lit(42).plus(JExpr.lit(-15)));
		list.arg(JExpr.lit(42).plus(JExpr.lit(-15)));
		list.arg(JExpr.lit(42).plus(JExpr.lit(-15)));
		body.decl(JMod.NONE, list_t, "list", list);

		body._return(locvar.plus(JExpr.direct("Double.SIZE")));

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		codeModel.build(new SingleStreamCodeWriter(stream));
		assertTrue(stream.toString().contains("class Foo"));
	}
}
