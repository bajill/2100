package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class EmptyStatm extends Statement {

    EmptyStatm(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<empty statm> on line " + lineNum;
    }

    
     /*

       @Override void check(Block curScope, Library lib) {
// Til del 3 av prosjektet
       }


       @Override void genCode(CodeFile f) {
// Til del 4 av prosjektet
       }

*/

    @Override void prettyPrint() {
        Main.log.prettyPrint(";");
    }

    static EmptyStatm parse(Scanner s) {
        enterParser("empty statm"); 
        EmptyStatm ess = new EmptyStatm(s.curLineNum());
        leaveParser("empty statm");
        return ess;
    }
}
