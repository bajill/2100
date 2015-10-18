
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class StringLiteral extends Constant {
    String id;
    StringLiteral(int lNum, String id) {
    super(lNum);
    this.id = id;
    }

    
    @Override public String identify() {
    return "<string literal> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint(" '" + id +"'");
    }

    static StringLiteral parse(Scanner s) {
        enterParser("string literal"); 
        s.test(stringValToken);
        StringLiteral sl = new StringLiteral(s.curLineNum(), s.curToken.strVal);
        s.readNextToken();
        leaveParser("string literal");
        return sl;
    }
}
