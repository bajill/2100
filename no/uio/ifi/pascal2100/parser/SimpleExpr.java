
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class SimpleExpr extends PascalSyntax {
    Term term;
    SimpleExpr(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<simpleExpr> on line " + lineNum;
    }

    static SimpleExpr parse(Scanner s) {
        enterParser("simpleExpr"); 
        SimpleExpr se = new SimpleExpr(s.curLineNum());
        se.term = Term.parse(s);
        leaveParser("simpleExpr");
        return se;
    }
}
