tree grammar STree;

options {
    tokenVocab=SExpression;
    ASTLabelType=CommonTree;
}

@header{
    package jsignalml;

    import java.util.List;
    import java.util.LinkedList;
    import java.util.Map;
    import java.util.AbstractMap.SimpleImmutableEntry;
}

@members {
        static final Logger log = new Logger(STree.class);

        @Override public void recover(IntStream input, RecognitionException re){
            throw new SyntaxError(re);
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


keyvalue returns [Map.Entry<Expression, Expression> value] 
    : ^(KEYVALUE key=expr val=expr)
	{ $value = new SimpleImmutableEntry($key.value, $val.value); }
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
    | ^(MAP keyvalues)
	{ $value = new Expression.Map_($keyvalues.value); }
    | ID
        { $value = new Expression.Ref($ID.text); }
    | ^(CALL a=expr alist)
        { $value = new Expression.Call($a.value, $alist.value); }
    | ^(INDEX s=expr a=expr)
        { $value = new Expression.Index($a.value, $s.value); }
    | ^(SLICE start=expr stop=expr step=expr a=expr)
        { $value = new Expression.Slice($a.value, $start.value, $stop.value, $step.value); }
    | ^(ACCESS a=expr ID)
	{ $value = new Expression.Access($a.value, $ID.text); }
    | INT
        { $value = new Expression.Const(new TypeInt($INT.text)); }
    | FLOAT
        { $value = new Expression.Const(new TypeFloat($FLOAT.text)); }
    | STRING
        { TypeString tmp = TypeString.fromQuoted($STRING.text);
          $value = new Expression.Const(tmp); }
    | ^(TERN q=expr a=expr b=expr)
        { $value = new Expression.Ternary($q.value, $a.value, $b.value); }
    | NIL
	{ $value = null; }
    ;

alist returns [List<Expression> value]
@init { $value = new LinkedList<Expression>(); }
    : (expr { $value.add($expr.value); })*
    ;

keyvalues returns [List<Map.Entry<Expression, Expression>> value]
@init { $value = new LinkedList<Map.Entry<Expression, Expression>>(); }
    : (keyvalue { $value.add($keyvalue.value); })*
    ;
