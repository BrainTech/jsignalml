tree grammar STree;

options {
    tokenVocab=SExpression;
    ASTLabelType=CommonTree;
}

@header{
    package jsignalml;

    import java.util.List;
    import java.util.LinkedList;
}

@members {
        static final Logger log = new Logger(STree.class);

        @Override public void recover(IntStream input, RecognitionException re){
            throw new SyntaxError.RuntimeFlavour(re);
        }

        @Override
        public void displayRecognitionError(String[] tokenNames,
                                            RecognitionException e)
        {
            log.exception("displayRecognitionError()", e);
            super.displayRecognitionError(tokenNames, e);
        }

        @Override
        public void emitErrorMessage(String msg){
            log.error(msg);
        }
}

script: line*
    ;

line returns [Expression value]
    : ^( ASSIGN ID e=expr )
        { $value = new Expression.Assign($ID.text, $e.value); }
    | expr
        { $value = $expr.value; }
    ;

expr returns [Expression value]
    : ^( ( op=LOGICAL_NOT
         | op=UNARY_ADD
         | op=UNARY_SUBTRACT ) a=expr)
        { $value = new Expression.UnaryOp($op.type, $a.value); }
    | ^(    ( op=ADD
            | op=SUBTRACT | op=MULTIPLY
            | op=FLOORDIV | op=TRUEDIV | op=MODULO
            | op=BINARY_AND | op=BINARY_OR | op=BINARY_XOR
            | op=EQUALS | op=NOTEQUALS
            | op=LESSTHAN | op=MORETHAN
            | op=LESSEQUALS| op=MOREEQUALS
            | op=POWER
            )     a=expr b=expr)
        { $value = new Expression.BinaryOp($op.type, $a.value, $b.value); }
    | ^(    ( op=LOGICAL_AND | op=LOGICAL_OR )     a=expr b=expr)
        { $value = new Expression.LogicalBinaryOp($op.type, $a.value, $b.value); }
    | ^(LIST alist)
        { $value = new Expression.List_($alist.value); }
    | ID
        { $value = new Expression.Call($ID.text); }
    | ^(CALL ID alist)
        { $value = new Expression.Call($ID.text, $alist.value); }
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

alist returns [List<Expression> value]
@init { $value = new LinkedList<Expression>(); }
    : (expr { $value.add($expr.value); })*
    ;
