grammar SExpression;

options {
    output=AST;
    ASTLabelType=CommonTree; // type of $stat.tree ref etc...
}

tokens {
    LIST;
    MAP;
    KEYVALUE;
    TERN;

    UNARY_ADD;
    UNARY_SUBTRACT;

    INDEX       = '[';
    SLICE;
    NIL;
    CALL        = '(';
    ACCESS      = '.';

    ADD         = '+';
    SUBTRACT    = '-';

    MULTIPLY    = '*';
    FLOORDIV    = '//';
    CEILDIV    = '///';
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

    SEMICOLON   = ';';
}

@lexer::header{
    package jsignalml;

    import jsignalml.logging.Logger;
}

@parser::header{
    package jsignalml;

    import jsignalml.logging.Logger;
}

@lexer::members {
        static final Logger log = new Logger(SExpressionLexer.class);

        @Override
        public void displayRecognitionError(String[] tokenNames,
                                            RecognitionException e)
        {
            log.error("displayRecognitionError()", e);
            super.displayRecognitionError(tokenNames, e);
            throw new SyntaxError(e);
        }

        @Override
        public void emitErrorMessage(String msg)
	{
            log.error(msg);
        }

        @Override
        public Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
        {
            throw new SyntaxError(new MismatchedTokenException(ttype, input));
        }
    }

@parser::members {
        static final Logger log = new Logger(SExpressionParser.class);

        @Override public void recover(IntStream input, RecognitionException re)
        {
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
        public void emitErrorMessage(String msg)
        {
            log.error(msg);
        }

        @Override
        public Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
        {
            throw new SyntaxError(new MismatchedTokenException(ttype, input));
        }
    }


script: line* EOF!
    ;

terminator: EOF
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

multexpr:	unaryexpr ((MULTIPLY^ | FLOORDIV^ | CEILDIV^ | TRUEDIV^ | MODULO^ |
                       BINARY_AND^ | BINARY_OR^ | BINARY_XOR^) unaryexpr)*
	;

unaryexpr
    : ADD unaryexpr      -> ^(UNARY_ADD unaryexpr)
    | SUBTRACT unaryexpr -> ^(UNARY_SUBTRACT unaryexpr)
    | powexpr
    ;

powexpr
    : baseitem
        ( POWER powexpr -> ^(POWER baseitem powexpr)
        |               -> baseitem
        )
    ;

baseitem
    : (atom | ID)
	(indexexpr^
        | CALL^ calltail
	| ACCESS^ ID
        )*
    ;

indexexpr
    : INDEX
	( start=expr
	    (                                  -> ^(INDEX $start)
	    | ':' stop=expr
		( ':' step=expr                -> ^(SLICE $start $stop $step)
		| ':'?                         -> ^(SLICE $start $stop NIL)
		)
	    | ':'
		( ':' step=expr                -> ^(SLICE $start NIL $step)
		| ':' ?                        -> ^(SLICE $start NIL NIL)
		)
	    )
	| ':'
	    (                                  -> ^(SLICE NIL NIL NIL)
	    | stop=expr
		( ':' step=expr                -> ^(SLICE NIL $stop $step)
		| ':'?                         -> ^(SLICE NIL $stop NIL)
		)
	    | ':'
		( step=expr                    -> ^(SLICE NIL NIL $step)
		|                              -> ^(SLICE NIL NIL NIL)
		)
	    )
	) ']'
    ;

calltail
    : (expr (',' expr)*)? ')' -> expr*
    ;

keyvalue
    : s1=expr ':' s2=expr -> ^(KEYVALUE $s1 $s2)
    ;

atom
    :	INT
    |	BOOL
    |	FLOAT
    |   BYTES
    |	STRING
    |	'('! expr ')'!
    |   '[' ']' -> ^(LIST)
    |   '[' expr (',' expr)* ','? ']' -> ^(LIST expr+)
    |   '{' '}' -> ^(MAP)
    |   '{' keyvalue (',' keyvalue)* ','? '}' -> ^(MAP keyvalue+)
	;

BOOL
    : 'False'
    | 'True'
    ;

ID  :	(LETTER|'_') (LETTER|DIGIT|'_')*
    ;

INT
    : ('0x'|'0o'|'0b') HEX_DIGIT+
    | DIGIT+
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
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    | '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
    ;

BYTES
    : ('B'|'b') STRING
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
