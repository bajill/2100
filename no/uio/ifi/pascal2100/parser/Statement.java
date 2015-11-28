package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Statement extends PascalSyntax {

    Statement(int lNum) {
        super(lNum);
    }
    
    @Override void genCode(CodeFile f) {
    }

    @Override public String identify() {
        return "<empty statm> on line " + lineNum;
    }

    static Statement parse(Scanner s) {
        enterParser("statement");
        Statement st = null;
        switch (s.curToken.kind) {
            case beginToken:
                st = CompoundStatm.parse(s);  break;
            case ifToken:
                st = IfStatm.parse(s);  break;
            case nameToken:
                switch (s.nextToken.kind) {
                    case assignToken:
                    case leftBracketToken:
                        st = AssignStatm.parse(s);  break;
                    default:
                        st = ProcCallStatm.parse(s);  break;
                } break;
            case whileToken:
                st = WhileStatm.parse(s);  break;
            default:
                st = EmptyStatm.parse(s);  break;
        }
        leaveParser("statement");
        return st;
    }
    @Override void check(Block curScope, Library lib) {
    }
}
