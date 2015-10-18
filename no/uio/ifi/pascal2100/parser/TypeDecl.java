
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* type name ::= <type name> 'equal' <type> '; */
class TypeDecl extends PascalDecl {
    Type type;

    TypeDecl(String id, int lNum) {
    super(id, lNum);
    }

    
    @Override public String identify() {
    return "<type decl> on line " + lineNum;
    }

    @Override void prettyPrint() {
        super.prettyPrint();
        Main.log.prettyPrint(" = ");
        type.prettyPrint();
        Main.log.prettyPrintLn(";");
    }

    static TypeDecl parse(Scanner s) {
        enterParser("type decl"); 
        TypeDecl td = new TypeDecl(TypeName.parse(s).name , s.curLineNum());

        s.skip(equalToken);

        td.type = Type.parse(s);
        s.skip(semicolonToken);
        
        leaveParser("type decl");
        return td;
    }
}
