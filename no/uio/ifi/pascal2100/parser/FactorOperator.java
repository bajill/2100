
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class FactorOperator extends Operator{

    FactorOperator(String name, int lNum) {
    super(name, lNum);
    }

    
    @Override public String identify() {
    return "<factor operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }
    // DONE, BUT WORKING?
    static FactorOperator parse(Scanner s) {
        enterParser("factor operator"); 
        FactorOperator fo = new FactorOperator(s.curToken.id, s.curLineNum());
        System.out.println("kommer til FactorOperator? " + fo.name);
        s.readNextToken();
        leaveParser("factor operator");
        return fo;
    }
}
