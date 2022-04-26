grammar Calculator;

expr
    : NUMBER                            # Number
    | left=expr op=(MUL|DIV) right=expr # MulDiv
    | left=expr op=(ADD|SUB) right=expr # AddSub
    | '(' inner=expr ')'                # Parens
    ;

NUMBER: [+-]?[0-9]+('.'[0-9]+)?([eE][+-]?[0-9]+)? ;

MUL: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
WS : [ \t\r\n]+ -> skip ;
