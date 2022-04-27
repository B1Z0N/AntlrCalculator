grammar Calculator;

init: expr EOF ;

expr
    : '(' inner=expr ')'                   # Parens
    | op=(PLUS|MINUS) expr                 # Unary
    | left=expr op=(MUL|DIV) right=expr    # MulDiv
    | left=expr op=(PLUS|MINUS) right=expr # AddSub
    | value=NUM                            # Number
    ;

NUM: [0-9]+ ('.' [0-9]+)? ([eE] [+-]? [0-9]+)? ;

MUL: '*' ;
DIV: '/' ;
PLUS: '+' ;
MINUS: '-' ;
WS : [ \t\r\n]+ -> skip ;
