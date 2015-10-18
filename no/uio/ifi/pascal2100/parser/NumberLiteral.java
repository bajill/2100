
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class NumberLiteral extends Constant {
    int numValue;
    NumberLiteral(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<numeric literal> on line " + lineNum;
    }

    @Override void prettyPrint(){
    }

    static NumberLiteral parse(Scanner s) {
        enterParser("numeric literal"); 
        NumberLiteral nl = new NumberLiteral(s.curLineNum());
        s.test(intValToken);
        nl.numValue = s.curToken.intVal;
        //System.out.println(s.curToken.identify());
        s.readNextToken();
        //System.out.println(s.curToken.identify());
        leaveParser("numeric literal");
        return nl;
    }
}
