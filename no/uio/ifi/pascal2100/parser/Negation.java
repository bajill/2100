package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class Negation extends Factor{
    Factor factor;

    Negation(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<negation> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("not ");
        factor.prettyPrint();
    }

    static Negation parse(Scanner s) {
        enterParser("negation"); 
        s.skip(notToken);
        Negation n = new Negation(s.curLineNum());
        n.factor = Factor.parse(s);
        leaveParser("negation");
        return n;
    }
}
