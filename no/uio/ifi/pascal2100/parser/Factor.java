
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Factor extends PascalSyntax {
    Factor(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<factor> on line " + lineNum;
    }

    @Override void prettyPrint() {
    }

    static Factor parse(Scanner s) {
        enterParser("factor"); 
        Factor f = null;
        switch (s.curToken.kind) {
            case stringValToken:
                f = StringLiteral.parse(s);  break;
                //st = IfStatm.parse(s);  break;
        } 
        leaveParser("factor");
        return f;
    }
}
