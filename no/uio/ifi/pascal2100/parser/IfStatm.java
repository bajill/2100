package no.uio.ifi.pascal2100.parser; 
import no.uio.ifi.pascal2100.main.* ;
import no.uio.ifi.pascal2100.scanner.* ; 
import static no.uio.ifi.pascal2100.scanner.TokenKind.* ; 
class IfStatm extends Statement {
    Expression expr;
    Statement body;
    IfStatm(int lNum) {
        super(lNum);
    }
    @Override public String identify() {
        return "<while-statm> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint("if ");
        expr.prettyPrint();
        Main.log.prettyPrintLn("do ");
        Main.log.prettyIndent();
        body.prettyPrint();
        Main.log.prettyOutdent();
    }
    static IfStatm parse(Scanner s) {
        enterParser("if-statm");

        IfStatm is = new IfStatm(s.curLineNum());
        s.skip(whileToken);

        is.expr = Expression.parse(s);
        s.skip(doToken);
        is.body = Statement.parse(s);

        leaveParser("if-statm");
        return is;
    }
}
