
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Constant extends Factor {
    StringLiteral strintLiteral;
    Constant(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<constant> on line " + lineNum;
    }

    static Constant parse(Scanner s) {
        enterParser("constant"); 
        Constant c;
        c = StringLiteral.parse(s);
        leaveParser("constant");
        return c;
    }
}
