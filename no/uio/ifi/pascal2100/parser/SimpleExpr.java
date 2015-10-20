
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
/* simple expr ::= [<prefix opr>] <term> [<term opr> loop] */
class SimpleExpr extends PascalSyntax {
    ArrayList<Term> term;
    ArrayList<Operator> operator;
    SimpleExpr(int lNum) {
    super(lNum);
    term = new ArrayList<Term>();
    operator = new ArrayList<Operator>();
    }

    
    @Override public String identify() {
    return "<simpleExpr> on line " + lineNum;
    }

    @Override void prettyPrint() {
       for (int i = 0; i < term.size(); i++)
           term.get(i).prettyPrint();
    }

    static SimpleExpr parse(Scanner s) {
        enterParser("simpleExpr"); 
        SimpleExpr se = new SimpleExpr(s.curLineNum());
        while(true){
            se.term.add(Term.parse(s));
            if(!s.curToken.kind.isTermOpr())
                break;
            //TODO se.operator.add(TermOperator.parse());
        }    
        leaveParser("simpleExpr");
        return se;
    }
}
