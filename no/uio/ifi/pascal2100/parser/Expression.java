package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/* expression ::= <simple expression> [<rel opr> <simple expression>] */

class Expression extends PascalSyntax {
    SimpleExpr simpleExpr;
    SimpleExpr additionalSimpleExpr;
    Operator operator;

    Expression(int lNum) {
        super(lNum);
    }


    @Override public String identify() {
        return "<expression> on line " + lineNum;
    }
    
    @Override void check(Block curscope, Library lib){
        simpleExpr.check(curscope, lib);
        if(additionalSimpleExpr != null){
            operator.check(curscope, lib);
            additionalSimpleExpr.check(curscope, lib);
        }
    }

    @Override void prettyPrint() {
        simpleExpr.prettyPrint();
        if (operator != null) {
            operator.prettyPrint();
            additionalSimpleExpr.prettyPrint();
        }
    }

    static Expression parse(Scanner s) {
        enterParser("expression"); 
        /* simple expression */ 
        Expression ex = new Expression(s.curLineNum());
        ex.simpleExpr = SimpleExpr.parse(s);
        /* <rel opr> <simple expression> */
        if(s.curToken.kind.isRelOpr()){
            ex.operator = RelOperator.parse(s); 
            ex.additionalSimpleExpr = SimpleExpr.parse(s);
        }
        leaveParser("expression");
        return ex;
    }
}
