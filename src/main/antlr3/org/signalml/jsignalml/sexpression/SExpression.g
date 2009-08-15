grammar SExpression;

options {
    output=AST;
    ASTLabelType=CommonTree; // type of $stat.tree ref etc...
}

tokens {
    LIST;
    TERN;
    CALL;
    INDEX;

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

    EQUALS      = '==';
    NOTEQUALS   = '!=';
    LESSTHAN    = '<';
    MORETHAN    = '>';
    LESSEQUALS  = '<=';
    MOREEQUALS  = '>=';

    ASSIGN      = '=';

    NEWLINE     = '\n';
}

@header{
package org.signalml.jsignalml.sexpression;
}

@lexer::header{
package org.signalml.jsignalml.sexpression;
}

script: line* EOF!
    ;

terminator: NEWLINE | EOF
    ;

line
    :	expr terminator!
    |   ID ASSIGN expr terminator -> ^(ASSIGN ID expr)
	;

singleexpr: expr EOF!
    ;

expr
    : andorexpr
        ( '?' s1=expr ':' s2=expr -> ^(TERN andorexpr $s1 $s2)
        |                         -> andorexpr
        )
    ;

andorexpr: notexpr ((LOGICAL_AND^|LOGICAL_OR^) notexpr)*
	;
	
notexpr
    : (LOGICAL_NOT^ notexpr)
    | compexpr
	;
	
compexpr
    : addexpr
        (   (EQUALS^
            |NOTEQUALS^
            |LESSTHAN^
            |MORETHAN^
            |LESSEQUALS^
            |MOREEQUALS^
            ) addexpr   )*
    ;

addexpr	:	multexpr ((ADD^ |SUBTRACT^) multexpr)*
	;
	
multexpr:	powexpr ((MULTIPLY^ | FLOORDIV^ | TRUEDIV^ | MODULO^ |
                      BINARY_AND^ | BINARY_OR^ | BINARY_XOR^) powexpr)*
	;
	

powexpr
    : accessor
        ( POWER powexpr -> ^(POWER accessor powexpr)
        |               -> accessor
        )
    ;

accessor
    : atom
        ( index -> ^(INDEX atom index)
        |       -> atom
        )
    | ID
        ( index -> ^(INDEX ^(CALL ID) index)
        | call  -> ^(CALL ID call)
        |       -> ^(CALL ID)
        )
    ;

index // TODO
    : '['! expr ']'!
    ;

call
    : '(' (expr (',' expr)*)? ')' -> expr*
    ;

atom
    :	INT
	|	FLOAT
	|	STRING
	|	'('! expr ')'!
    |   '[' expr (',' expr)* ','? ']' -> ^(LIST expr+)
	;

ID  :	(LETTER|'_') (LETTER|DIGIT|'_')*
    ;

INT :	('-'|'+')? DIGIT+
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
HEX_DIGIT : DIGIT | 'a'..'f'|'A'..'F' ;

fragment
ESC_SEQ
    :   '\\' ('t'|'n'|'r'|'\"'|'\''|'\\')
    ;

fragment
DIGIT : '0'..'9' ;

fragment
LETTER : 'a'..'z'|'A'..'Z' ;
