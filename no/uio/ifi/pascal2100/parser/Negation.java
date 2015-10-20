
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class Negation extends Factor{
    String name;
    Negation(String name, int lNum) {
    super(lNum);
    this.name = name;
    }

    
    @Override public String identify() {
    return "<negation> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Negation parse(Scanner s) {
        enterParser("negation"); 
        Negation n = new Negation(s.curToken.kind.toString(), s.curLineNum());
        leaveParser("negation");
        return n;
    }
}
