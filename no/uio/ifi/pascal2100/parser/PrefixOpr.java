
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class PrefixOpr extends Operator{

    PrefixOpr(String name, int lNum) {
    super(name, lNum);
    }

    
    @Override public String identify() {
    return "<prefix opr> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);
    }

    static PrefixOpr parse(Scanner s) {
        enterParser("prefix opr"); 
        PrefixOpr po = new PrefixOpr(s.curToken.kind.toString(), s.curLineNum());
        s.readNextToken();
        leaveParser("prefix opr");
        return po;
    }
}
