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
    : ^(    ( op=ADD
            | op=SUBTRACT | op=MULTIPLY
            | op=FLOORDIR | op=TRUEDIV | op=MODULO
            | op=BINARY_AND | op=BINARY_OR | op=BINARY_XOR
            | op=LOGICAL_AND | op=LOGICAL_OR
            | op=EQUALS | op=NOTEQUALS
            | op=LESSTHAN | op=MORETHAN
            | op=LESSEQUALS| op=MOREEQUALS
            | op=POWER
            )     a=expr b=expr)
        { $value = new Expression.BinaryOp($op.type, $a.value, $b.value); }
    | ^(op=LOGICAL_NOT a=expr)
        { $value = new Expression.UnaryOp($op.type, $a.value); }
    | ^(CALL ID argslist)
        { $value = new Expression.Call($ID.text, $argslist.value); }
    | ^(INDEX a=expr s=expr)
        { $value = new Expression.Index($a.value, $s.value); }
    | INT
        { $value = new Expression.Const(new Type.Int($INT.text)); }
    | FLOAT
        { $value = new Expression.Const(new Type.Float($FLOAT.text)); }
    | STRING
        { Type.String tmp = Type.String.fromQuoted($STRING.text);
          $value = new Expression.Const(tmp); }
    | ^(TERN q=expr a=expr b=expr)
        { $value = new Expression.Ternary($q.value, $a.value, $b.value); }
    ;

argslist returns [List<Expression> value]
@init { $value = new LinkedList<Expression>(); }
    : (expr { $value.add($expr.value); })*
    ;
