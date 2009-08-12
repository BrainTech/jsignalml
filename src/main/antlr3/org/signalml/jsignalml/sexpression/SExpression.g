grammar SExpression;

options {
    output=AST;
    ASTLabelType=CommonTree; // type of $stat.tree ref etc...
}

tokens {
    LIST;
    TERN;
    CALL;

    ADD         = '+';
    SUBTRACT    = '-';

    MULTIPLY    = '*';
    FLOORDIV    = '//';
    TRUEDIV     = '/';
    MODULO      = '%';

    BINARY_AND  = '&';
    BINARY_OR   = '|';
    BINARY_XOR  = '^';

    POWER       = '**';

    LOGICAL_AND = 'and';
    LOGICAL_OR  = 'or';
    LOGICAL_NOT = 'not';

    NEWLINE     = '\n';
}

@header{
package org.signalml.jsignalml.sexpression;
}

@lexer::header{
package org.signalml.jsignalml.sexpression;
}

multiline: line*
    ;

line:	expr NEWLINE
	;

singleexpr: expr EOF!
    ;

expr: andorexpr
    ( '?' s1=expr ':' s2=expr -> ^(TERN andorexpr $s1 $s2)
    |                         -> andorexpr
    )
    ;

andorexpr: notexpr ((LOGICAL_AND^|LOGICAL_OR^) notexpr)*
	;
	
notexpr: LOGICAL_NOT* addexpr
	;
	
addexpr	:	multexpr ((ADD^ |SUBTRACT^) multexpr)*
	;
	
multexpr:	powexpr ((MULTIPLY^ | FLOORDIV^ | TRUEDIV^ | MODULO^ |
                      BINARY_AND^ | BINARY_OR^ | BINARY_XOR^) powexpr)*
	;
	

// fixme: precedence is wrong!
powexpr: atom (POWER^ atom)*
    ;

atom	:	INT
	|	FLOAT
	|	STRING
	|	call
	|	'('! expr ')'!
    |   '[' expr (',' expr)* ','? ']' -> ^(LIST expr+)
	;

call: ID
    ( '(' (expr (',' expr)*)? ')' -> ^(CALL ID expr*)
    |                             -> ^(CALL ID)
    )
    ;

ID  :	(LETTER|'_') (LETTER|DIGIT|'_')*
    ;

INT :	DIGIT+
    ;

FLOAT
    :   DIGIT+ '.' DIGIT* EXPONENT?
    |   '.' DIGIT+ EXPONENT?
    |   DIGIT+ EXPONENT
    ;

COMMENT
    :   '#' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    | '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? DIGIT+ ;

fragment
HEX_DIGIT : DIGIT | LETTER ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    ;

fragment
DIGIT : '0'..'9' ;

fragment
LETTER : 'a'..'f'|'A'..'F' ;
