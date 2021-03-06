package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

/* const decl part ::= 'const' <const decl> <const decl> ect... */

class ConstDeclPart extends PascalSyntax {
    ArrayList<ConstDecl> constDecl;

    ConstDeclPart(int lNum) {
        super(lNum);
        constDecl = new ArrayList<ConstDecl>();
    }

    @Override public String identify() {
        return "<const decl part> on line " + lineNum;
    }

    @Override void genCode(CodeFile f) {
    }
    
    @Override void check(Block curScope, Library lib) {
        for (ConstDecl cd: constDecl) 
            cd.check(curScope, lib);
    }

    @Override void prettyPrint(){
        Main.log.prettyPrintLn("const");
        Main.log.prettyIndent();
        for (int i = 0; i < constDecl.size(); i++) {
            constDecl.get(i).prettyPrint();
        }
        Main.log.prettyOutdent();
    }

    static ConstDeclPart parse(Scanner s) {
        /* These tokens are the valid tokens that can exist in ConstDeclPart */
        TokenKind[] tokenKind = {varToken, functionToken, procedureToken,
            typeToken, beginToken}; enterParser("const decl part"); 
        s.skip(constToken);

        /* loop trough all const decl */
        ConstDeclPart cdp = new ConstDeclPart(s.curLineNum());
        while(true){
            cdp.constDecl.add(ConstDecl.parse(s));
            /* Tests if curToken is valid */
            if (testIfToken(s, tokenKind)) 
                break;
            else
                continue;
        }
        leaveParser("const decl part");
        return cdp;
    }
}
