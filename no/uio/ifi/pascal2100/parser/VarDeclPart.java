package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

/* var delc part ::= 'var' <var decl> */

class VarDeclPart extends Statement {
    ArrayList<VarDecl> varDecl;

    VarDeclPart(int lNum) {
        super(lNum);
        varDecl = new ArrayList<VarDecl>();
    }

    @Override public String identify() {
        return "<var decl part> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrintLn("var");
        Main.log.prettyIndent();
        for (int i = 0; i < varDecl.size(); i ++) {
            varDecl.get(i).prettyPrint();
        } 
        Main.log.prettyOutdent();
    }

    static VarDeclPart parse(Scanner s) {
        /* These tokens are the valid tokens that can exist in VarDeclPart */
        TokenKind[] tokenKind = {functionToken, procedureToken, beginToken};
        enterParser("var decl part"); 
        s.skip(varToken);
        VarDeclPart vdp = new VarDeclPart(s.curLineNum());

        /* 
        // We should have used this one instead of testIfToken, but dont have
        // the time to test all files again.

        while(true){
            vdp.varDecl.add(VarDecl.parse(s));
            if (s.curToken.kind == nameToken)
                continue;
            else
                break;
                */

        /* Tests if curToken is valid */
            if (testIfToken(s, tokenKind))
                break;
            else
                continue;
        }

        leaveParser("var decl part");
        return vdp;
    }
}
