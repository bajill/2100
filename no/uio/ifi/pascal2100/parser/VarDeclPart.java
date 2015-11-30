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

    @Override void check(Block curScope, Library lib) {
        for (VarDecl vd: varDecl)
            vd.check(curScope, lib);
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
        enterParser("var decl part"); 
        s.skip(varToken);
        VarDeclPart vdp = new VarDeclPart(s.curLineNum());

        while(true){
            vdp.varDecl.add(VarDecl.parse(s));
            if (s.curToken.kind == nameToken)
                continue;
            else
                break;
        }
        leaveParser("var decl part");
        return vdp;
    }
}
