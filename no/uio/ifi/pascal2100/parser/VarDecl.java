
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/* var delc ::= <name> ':' <type> ; */
class VarDecl extends PascalDecl {
    Type type;
    VarDecl(String name, int lNum) {
    super(name, lNum);
    }

    
    @Override public String identify() {
    return "<var decl> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static VarDecl parse(Scanner s) {
        enterParser("var decl"); 
        VarDecl vd = new VarDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        s.skip(colonToken);
        vd.type = Type.parse(s);
        s.skip(semicolonToken);

        leaveParser("var decl");
        return vd;
    }
}