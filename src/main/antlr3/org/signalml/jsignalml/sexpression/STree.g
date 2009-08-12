tree grammar STree;

options {
    tokenVocab=SExpression;
    ASTLabelType=CommonTree;
}

@header{
    package org.signalml.jsignalml.sexpression;

    import java.util.LinkedList;
}

//START
expr returns [Expression value]
    : ^(op=(ADD|SUBTRACT) a=expr b=expr)
        { $value = new Expression.BinaryOp($op.type, $a.value, $b.value); }
    | ^(CALL ID argslist)
        { $value = new Expression.Call($ID.text, $argslist.value); }
    | INT { $value = new Expression.Const(new Type.Int($INT.text)); }
    | FLOAT { $value = new Expression.Const(new Type.Float($FLOAT.text)); }
    | STRING { $value = new Expression.Const(new Type.String($STRING.text)); }
    ;

argslist returns [List<Expression> value]
@init { $value = new LinkedList<Expression>(); }
    : (expr { $value.add($expr.value); })*
    ;
