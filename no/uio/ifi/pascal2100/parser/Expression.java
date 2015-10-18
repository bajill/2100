
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class Expression extends PascalSyntax {
    SimpleExpr simpleExpr;
    Expression(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<expression> on line " + lineNum;
    }

    @Override void prettyPrint() {
        simpleExpr.prettyPrint();
    }

    static Expression parse(Scanner s) {
        enterParser("expression"); 
        Expression ex = new Expression(s.curLineNum());
        ex.simpleExpr = SimpleExpr.parse(s);
        
        leaveParser("expression");
        return ex;
    }
}
