package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* const decl part ::= 'const' <const decl> <const decl> ect... */
class ConstDeclPart extends PascalSyntax {
    ArrayList<ConstDecl> constDecl;
    ConstDeclPart(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<const decl part> on line " + lineNum;
    }

    @Override void prettyPrint(){
    }

    static ConstDeclPart parse(Scanner s) {
        enterParser("const decl part"); 
        s.skip(constToken);
        /* loop trough all const decl */
        ConstDeclPart cdp = new ConstDeclPart(s.curLineNum());
        while(true){
            cdp.constDecl.append(ConstDecl.parse(s));
            // TODO har ikke funnet noe å teste for her
            if(true)
                break;
        }
        leaveParser("const decl part");
        return cdp;
    }
}
