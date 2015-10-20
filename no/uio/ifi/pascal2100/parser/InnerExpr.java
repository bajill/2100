package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class InnerExpr extends Factor{
    Expression expression;
    InnerExpr(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<inner expr> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("(");
        expression.prettyPrint();
        Main.log.prettyPrint(") ");

    }

    static InnerExpr parse(Scanner s) {
        enterParser("inner expr"); 
        s.skip(leftParToken);
        InnerExpr ie = new InnerExpr(s.curLineNum());
        ie.expression = Expression.parse(s);
        s.skip(rightParToken);
        leaveParser("inner expr");
        return ie;
    }
}
