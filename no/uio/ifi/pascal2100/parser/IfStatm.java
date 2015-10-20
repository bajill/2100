package no.uio.ifi.pascal2100.parser; 
import no.uio.ifi.pascal2100.main.* ;
import no.uio.ifi.pascal2100.scanner.* ; 
import static no.uio.ifi.pascal2100.scanner.TokenKind.* ; 
class IfStatm extends Statement {
    Expression expression;
    Statement statement;
    Statement additionalStatement;
    IfStatm(int lNum) {
        super(lNum);
    }
    @Override public String identify() {
        return "<while-statm> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint("if ");
        expression.prettyPrint();
        Main.log.prettyPrintLn("do ");
        Main.log.prettyIndent();
        statement.prettyPrint();
        Main.log.prettyOutdent();
    }

    // DONE, BUT WORKING?
    static IfStatm parse(Scanner s) {
        enterParser("if-statm");

        IfStatm is = new IfStatm(s.curLineNum());
        s.skip(ifToken);

        is.expression = Expression.parse(s);
        s.skip(thenToken);
        is.statement = Statement.parse(s);
        if(s.curToken.kind == elseToken){
            s.skip(elseToken);
            is.additionalStatement = Statement.parse(s);
        }
            
        leaveParser("if-statm");
        return is;
    }
}
