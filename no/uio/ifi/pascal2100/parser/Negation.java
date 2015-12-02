package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class Negation extends Factor{
    Factor factor;

    Negation(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f){
        factor.genCode(f);
        f.genInstr("", "xorl", "$0x1,%eax", "not");
    }
    @Override void check(Block curscope, Library lib){
        factor.check(curscope, lib);
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
