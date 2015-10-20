package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
class ProcCallStatm extends Statement {
    CharLiteral name;
    ArrayList<Expression> expression;
    boolean arguments;
    ProcCallStatm(int lNum) {
        super(lNum);
        arguments = false;
        expression = new ArrayList<Expression>();
    }

    @Override public String identify() {
        return "<proc call statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        name.prettyPrint();
        if (arguments) {
            Main.log.prettyPrint("((");
            for (int i = 0; i < expression.size(); i++) {
                expression.get(i).prettyPrint();
                if (i < expression.size() - 1)
                    Main.log.prettyPrint(", ");
            }
            Main.log.prettyPrint(") ");
        }
    }

    static ProcCallStatm parse(Scanner s) {
        enterParser("proc call statm"); 
        ProcCallStatm pcs = new ProcCallStatm(s.curLineNum());
        pcs.name = CharLiteral.parse(s);
        if(s.curToken.kind == leftParToken){
            pcs.arguments = true;
            s.skip(leftParToken);
            while(true){
                pcs.expression.add(Expression.parse(s));
                if(s.curToken.kind == commaToken)
                    s.skip(commaToken);
                else
                    break;
            }
            s.skip(rightParToken);
        }
        leaveParser("proc call statm");
        return pcs;
    }
}
