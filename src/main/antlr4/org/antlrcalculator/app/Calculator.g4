grammar Calculator;

init: expr EOF ;

expr
    : '(' inner=expr ')'                   # Parens
    | op=(PLUS|MINUS) expr                 # Unary
    | left=expr op=(MUL|DIV|MOD) right=expr    # MulDiv
    | left=expr op=(PLUS|MINUS) right=expr # AddSub
    | value=NUM                            # Number
    ;

NUM: [0-9]+ ('.' [0-9]+)? ([eE] [+-]? [0-9]+)? ;

MUL: '*' ;
DIV: '/' ;
MOD: '%' ;
PLUS: '+' ;
MINUS: '-' ;
WS : [ \t\r\n]+ -> skip ;
