
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class Term extends PascalSyntax  {
    Factor factor;
    Term(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<term> on line " + lineNum;
    }

    static Term parse(Scanner s) {
        enterParser("term"); 
        Term t = new Term(s.curLineNum());
        t.factor = Factor.parse(s);
        leaveParser("term");
        return t;
    }
}
