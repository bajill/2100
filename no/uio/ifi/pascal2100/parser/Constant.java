package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Constant extends Factor {

    Constant(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<constant> on line " + lineNum;
    }

    @Override abstract void genCode(CodeFile f);


    static Constant parse(Scanner s) {
        enterParser("constant"); 
        Constant c = null;
        switch (s.curToken.kind){
            case nameToken:
                c = NamedConst.parse(s);
                break;
            case stringValToken:
                c = StringLiteral.parse(s);
                break;
            case intValToken:
                c = NumberLiteral.parse(s);
                break;
            default:
        }
        leaveParser("constant");
        return c;
    }
}
