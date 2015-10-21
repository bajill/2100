package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

/* simple expr ::= [<prefix opr>] <term> [<term opr> loop] */

class SimpleExpr extends PascalSyntax {
    /* term and operator belongs together */
    ArrayList<Term> term;
    ArrayList<Operator> operator;
    /* prefix operator*/
    Operator optionalOperator;

    SimpleExpr(int lNum) {
        super(lNum);
        term = new ArrayList<Term>();
        operator = new ArrayList<Operator>();
    }

    @Override public String identify() {
        return "<simpleExpr> on line " + lineNum;
    }

    @Override void prettyPrint() { 
        if (optionalOperator != null)
            optionalOperator.prettyPrint();
        for (int i = 0; i < term.size(); i++) {
            term.get(i).prettyPrint();
            if (i < term.size() - 1)
                operator.get(i).prettyPrint();
        }
    }

    static SimpleExpr parse(Scanner s) {
        enterParser("simpleExpr"); 
        SimpleExpr se = new SimpleExpr(s.curLineNum());

        if(s.curToken.kind.isPrefixOpr()){
            se.optionalOperator = PrefixOpr.parse(s);
        }

        while(true){
            se.term.add(Term.parse(s));
            if(!s.curToken.kind.isTermOpr())
                break;
            se.operator.add(TermOperator.parse(s));
        }    

        leaveParser("simpleExpr");
        return se;
    }
}
