/*package no.uio.ifi.pascal2100.parser; 
import no.uio.ifi.pascal2100.main.* ;
import no.uio.ifi.pascal2100.scanner.* ; 
import static no.uio.ifi.pascal2100.scanner.TokenKind.* ; 
class WhileStatm extends Statement {
    Expression expr;
    Statement body;
    WhileStatm(int lNum) {
        super(lNum);
    }
    @Override public String identify() {
        return "<while-statm> on line " + lineNum;
    }
    static WhileStatm parse(Scanner s) {
        enterParser("while-statm");

        WhileStatm ws = new WhileStatm(s.curLineNum());
        s.skip(whileToken);

        ws.expr = Expression.parse(s);
        s.skip(doToken);
        ws.body = Statement.parse(s);

        leaveParser("while-statm");
        return ws;
    }
}*/
