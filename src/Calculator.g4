grammar Calculator;

expr: expr op=('*'|'/') expr # MulDiv
    | expr op=('+'|'-') expr # AddSub
    | FLOAT                  # Float
    | '('expr')'             # Parens
    ;

FLOAT: [+-]?[0-9]+'.'[0-9]+ 
     | [+-]?[0-9]+('.'[0-9]+)?'e'[+-]?[0-9]+
     | [+-]?[0-9]+ 
     ;

MUL: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
WS : [ \t\r\n]+ -> skip ;
