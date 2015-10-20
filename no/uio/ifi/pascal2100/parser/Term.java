
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
class Term extends PascalSyntax  {
    ArrayList<Operator> operator;
    ArrayList<Factor> factor;

    Term(int lNum) {
    super(lNum);
    factor = new ArrayList<Factor>();
    }

    
    @Override public String identify() {
    return "<term> on line " + lineNum;
    }

    @Override void prettyPrint() {
        for (int i = 0; i < factor.size(); i++) {
            factor.get(i).prettyPrint();
        }
    }

    // DONE, BUT WORKING?
    static Term parse(Scanner s) {
        enterParser("term"); 
        Term t = new Term(s.curLineNum());

        while(true){

            Factor f = Factor.parse(s);
            t.factor.add(f);
            /* if more factors */
            if(s.curToken.kind.isFactorOpr())
                t.operator.add(FactorOperator.parse(s));
            else
                break;
        }
        leaveParser("term");
        return t;
    }
}
