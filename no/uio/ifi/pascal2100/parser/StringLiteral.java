
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class StringLiteral extends Constant {
    StringLiteral(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<string literal> on line " + lineNum;
    }

    static StringLiteral parse(Scanner s) {
        enterParser("string literal"); 
        s.test(stringValToken);
        StringLiteral sl = new StringLiteral(s.curLineNum());
        s.readNextToken();
        leaveParser("string literal");
        return sl;
    }
}
