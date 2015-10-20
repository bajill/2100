
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class RelOperator extends Operator{
    RelOperator(String name, int lNum) {
    super(name, lNum);
    }

    
    @Override public String identify() {
    return "<rel operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {
       super.prettyPrint();
       // Main.log.prettyPrint(name); 
    }

    static RelOperator parse(Scanner s) {
        enterParser("rel operator"); 
        System.out.println(s.curToken.id);
        RelOperator ro = new RelOperator(s.curToken.id, s.curLineNum());
        s.readNextToken();
        leaveParser("rel operator");
        return ro;
    }
}
