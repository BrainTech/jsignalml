import java.io.*;
import com.sun.codemodel.*;
import com.sun.codemodel.writer.*;

public class CodemodelTest {
	void gen(OutputStream out) throws Exception {
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

		codeModel.build( new SingleStreamCodeWriter( out ) );
	}

	public static void main(String...args) throws Exception {
		new CodemodelTest().gen(System.out);
	}
}
